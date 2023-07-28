package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.util.StringUtils;

import bean.Menu;
import bean.MenuList;
import menu.Menuload;
import menu.Errorcheck;

/**
 * MenuControllerクラス 
 * メニュー画面の初期表示
 */

@Controller
public class MenuController {

	/**
	 * menuメソッド 
	 * メニュー画面のメニュー一覧を表示する
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/menu")
	public String menu(Model model) throws Exception {

		// メニューの表示をするためにmenuloadのインスタンスをつくる
		Menuload menuload = new Menuload();

		// Menu型Listのmenulistにmenuloadクラスのmenucsvメソッドの結果を入れる
		List<Menu> menuList = menuload.menucsv();

		// MenuListのインスタンスをつくる
		MenuList menu = new MenuList();
		// MenuList型menuのsetMenuメソッドにmenuListを代入する
		menu.setMenu(menuList);

		// menuをmodelオブジェクトのmenuListという属性に入れる
		model.addAttribute("menuList", menu);

		// メニュー画面を返す
		return "menu";
	}

	/**
	 * orderメソッド 
	 * 注文一覧を表示する
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/order")
	public String order(Model model, @ModelAttribute MenuList menuList) throws Exception {

		// menuListをmodelオブジェクトのmenuListという属性に入れる
		model.addAttribute("menuList", menuList);

		// 画面に表示されているメニューの一覧を取得してmenuに入れる
		List<Menu> menu = menuList.getMenu();

		// 注文があったメニューのリストをいれる入れ物をつくる
		List<Menu> order = new ArrayList<>();

		// int型のloopcounterの初期化する
		int loopCounter = 0;

		// Errorcheckクラスのインスタンスをつくる(このクラスを使うために実体をつくる)
		Errorcheck ordererror = new Errorcheck();

		// menuの回数分処理をループする
		for (Menu orderMenu : menu) {

			// loopcounterが0のときloopcounterに足す
			if (loopCounter == 0) {
				loopCounter++;

				// orderにヘッダーの情報も必要なのでMenu型Listのorderに入れる
				order.add(orderMenu);
				// 次の繰り返し処理に移る
				continue;
			}

			// errorcheckメソッドを呼び出す
			ordererror.errorcheck(orderMenu);

			// ordererrorクラスのiserrorメソッドがfalseかつ注文個数が入力されているときに注文するメニューの情報をorderに入れる
			if (!ordererror.iserror() && orderMenu.getCount().length() != 0) {
				order.add(orderMenu);
			}
		}

		// 注文個数が0個のときエラーの判定をする
		ordererror.ordercheck(order);

		// エラーの判定
		if (ordererror.iserror()) {
			menuList.setMessage(ordererror.getErrorlist());
			
			//エラーメッセージを注文画面に表示する
			return "menu";
		}

		// 個数が入力されているところは値をorderに入れる
		//　orderをmodelオブジェクトに追加
		model.addAttribute("order", order);

		// 合計金額の計算をする
		// 1.合計金額の変数を１つ用意する
		int sumtotal = 0;
		// loop counterを0に初期化する
		loopCounter = 0;
		// 2. orderに入っている分を回す
		for (Menu ordermenu : order) {
			// 3. 回すときにsumメソッドをCALLする
			// 4. loopcounterが0以外かつ注文個数がnull,空文字でないときにsumの戻りを１．の変数に足し算する
			if (loopCounter != 0 && StringUtils.hasLength(ordermenu.getCount())) {
				sumtotal += sum(ordermenu.getPrice(), ordermenu.getCount());
			}
			// loopcounterに1を足す
			loopCounter++;
		}
		// 5. modelの合計金額欄に1.の変数をいれる
		model.addAttribute("total", sumtotal);

		// メニュー画面を返す
		return "menu";
	}

	/**
	 * sumメソッド 
	 * 各商品の合計金額を計算する
	 * @param price 料金
	 * @param count 個数
	 * @return 料金×個数
	 */
	private int sum(String price, String count) {
		// 金額と個数を計算するためにString型からint型に変換する
		int priceprice = Integer.parseInt(price.replace("￥", "").replace(",", ""));
		int countcount = Integer.parseInt(count);

		// 金額と注文を掛け算して返す
		return priceprice * countcount;
	}

	/**
	 * billinメソッド 
	 * お会計画面に遷移する
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/billin")
	public String billin(Model model) throws Exception {

		// お会計画面のControllerにリダイレクトする
		return "redirect:/bill";
	}

}
