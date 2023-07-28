package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import bean.Menu;
import bean.MenuList;

/**
 * お会計画面の初期表示
 */
@Controller
public class BillController {
	
	/**
	 * billメソッド
	 * お会計画面の注文履歴と合計金額の表示する
	 * @param model
	 * @param orderList
	 * @param totalAmount
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/bill")
	public String bill(Model model, @ModelAttribute MenuList orderList, String totalAmount) throws Exception {
		// 注文があったメニューを取得してmenulistに入れる
		List<Menu> menulist = orderList.getMenu();

		// orderなので注文個数に値があるものが対象となる
		// CSV形式でリストを作成する
		List<String> outputDataList = new ArrayList<String>();

		// menulistの回数分処理をループする
		for (Menu orderMenu : menulist) {

			// StringBuffer(文字列の値が不変であると分かっているときに使用する)クラスのインスタンスをつくる
			StringBuffer csvFormat = new StringBuffer();
			// 注文個数に値があるものを出力対象とする
			if (StringUtils.hasLength(orderMenu.getCount())) {
				// ｃｓｖFormatという変数にランキングの値を追加している
				csvFormat.append(orderMenu.getRanking());
				// カンマで区切るのでカンマも追加する
				csvFormat.append(",　");
				// ｃｓｖFormatという変数にメニューの値を追加している
				csvFormat.append(orderMenu.getName());
				// カンマで区切るのでカンマも追加する
				csvFormat.append(", ");
				// ｃｓｖFormatという変数にカロリーの値を追加している
				csvFormat.append(orderMenu.getKcal());
				// カンマで区切るのでカンマも追加する
				csvFormat.append(", ");
				// ｃｓｖFormatという変数に金額の値を追加している
				csvFormat.append(orderMenu.getPrice());
				// カンマで区切るのでカンマも追加する
				csvFormat.append(", ");
				// ｃｓｖFormatという変数に注文個数の値を追加している
				csvFormat.append(orderMenu.getCount());
				// ｃｓｖFormatをString型にしてからcsv形式で表示するためのoutputDataListというリストに追加している
				outputDataList.add(csvFormat.toString());
			}
		}
		// CSV出力をする
		//　csv形式の注文一覧をmodelオブジェクトのorderListという属性に入れる
		model.addAttribute("orderList", outputDataList);

		// 合計金額を出力する
		//　合計金額をmodelオブジェクトのtotalAmountという属性に入れる
		model.addAttribute("totalAmount", totalAmount);

		// お会計画面を返す
		return "bill";
	}

	/**
	 * menureturnメソッド 
	 * お会計画面からトップページ画面に遷移する
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/toppagereturn")
	public String menureturn(Model model) throws Exception {

		// トップページ画面のControllerにリダイレクトする
		return "redirect:/toppage";
	}

}
