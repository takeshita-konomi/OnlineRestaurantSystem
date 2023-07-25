package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import bean.Menu;
import bean.MenuList;
import menu.Menuload;

/**
 * MenuControllerクラス メニュー画面の初期表示
 */

@Controller
public class MenuController {

	@GetMapping("/menu")
	public String menu(Model model) throws Exception {

		// メニューの表示
		Menuload menuload = new Menuload();

		List<Menu> menuList = menuload.menucsv();

		MenuList menu = new MenuList();
		menu.setMenu(menuList);

		model.addAttribute("menuList", menu);

		return "menu";
	}

	/**
	 * メニュー画面の注文ボタン押下時実行
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/order")
	public String order(Model model, @ModelAttribute MenuList menuList) throws Exception {

		model.addAttribute("menuList", menuList);

		// 画面に表示されているメニューの一覧
		List<Menu> menu = menuList.getMenu();

		// 注文があったメニューのリストをいれる入れ物
		List<Menu> order = new ArrayList<>();

		int loopCounter = 0;
		// menuの回数分処理をループする
		for (Menu orderMenu : menu) {

			if (loopCounter == 0) {
				loopCounter++;
				
				//orderにヘッダーの情報も必要です。
				order.add(orderMenu);
				
				continue;
			}

			if (StringUtils.hasLength(orderMenu.getCount())) {
				// 注文個数の値が入っているところの判定
				System.out.println(orderMenu.getCount());
				order.add(orderMenu);

				loopCounter++;
			}

		}

		// 個数が入力されているところは値をorderに入れる
		model.addAttribute("order", order);

		// 合計金額の計算
		// 1.合計金額の変数を１つ用意します。
		int sumtotal = 0;
		// loop counterを0に初期化
		loopCounter = 0;
		// 2. orderに入っている分を回します。
		for (Menu ordermenu : order) {
			// 3. 回すときにsumメソッドをCALLします。
			// 4. sumの戻りを１．の変数に足し算していく
			if (loopCounter != 0 && StringUtils.hasLength(ordermenu.getCount())) {
				sumtotal += sum(ordermenu.getPrice(), ordermenu.getCount());
			}
			loopCounter++;
		}
		// 5. modelの合計金額欄に1.の変数をいれる
		model.addAttribute("total", sumtotal);

		return "menu";
	}

	/**
	 * 各商品の合計金額を計算する
	 * 
	 * @param price 料金
	 * @param count 個数
	 * @return 料金×個数
	 */
	private int sum(String price, String count) {
		// 金額と個数を計算するためにString型からint型に変換
		int priceprice = Integer.parseInt(price.replace("￥", ""));
		int countcount = Integer.parseInt(count);

		return priceprice * countcount;
	}

	/**
	 * メニュー画面の注文ボタン押下時実行
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/billin")
	public String billin(Model model) throws Exception {
		/*
		 * //生産画面に表示する List<Menu> orderbill = new ArrayList<>();
		 * 
		 * String bill = String.join(",", ordermenu); orderbill.add(orderlist);
		 */
		return "redirect:/bill";
	}

}
