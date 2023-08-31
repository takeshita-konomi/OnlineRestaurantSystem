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
import bean.Message;

/**
 * お会計画面の初期表示
 */
@Controller
public class BillController {
	
	
		
	/**
	 * billメソッド
	 * お会計画面の注文履歴と合計金額の表示する
	 * メニュー画面の「お会計」ボタン押下時
	 * @param model
	 * @param orderList
	 * @param totalAmount
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/bill")
	public String bill(Model model, @ModelAttribute MenuList orderlist, String totalamount) throws Exception {
		
		
		// 注文があったメニューを取得してmenulistに入れる
		List<Menu> menulist = orderlist.getMenu();

		// orderなので注文個数に値があるものが対象となる
		// CSV形式でリストを作成する
		List<String> outputdatalist = new ArrayList<String>();

		// menulistの回数分処理をループする
		for (Menu ordermenu : menulist) {

			// StringBuffer(文字列の値が不変であると分かっているときに使用する)クラスのインスタンスをつくる
			StringBuffer csvformat = new StringBuffer();
			// 注文個数に値があるものを出力対象とする
			if (StringUtils.hasLength(ordermenu.getCount())) {
				// ｃｓｖFormatという変数にランキングの値を追加している
				csvformat.append(ordermenu.getRanking());
				// カンマで区切るのでカンマも追加する
				csvformat.append(",　");
				// ｃｓｖFormatという変数にメニューの値を追加している
				csvformat.append(ordermenu.getName());
				// カンマで区切るのでカンマも追加する
				csvformat.append(", ");
				// ｃｓｖFormatという変数にカロリーの値を追加している
				csvformat.append(ordermenu.getKcal());
				// カンマで区切るのでカンマも追加する
				csvformat.append(", ");
				// ｃｓｖFormatという変数に金額の値を追加している
				csvformat.append(ordermenu.getPrice());
				// カンマで区切るのでカンマも追加する
				csvformat.append(", ");
				// ｃｓｖFormatという変数に注文個数の値を追加している
				csvformat.append(ordermenu.getCount());
				// ｃｓｖFormatをString型にしてからcsv形式で表示するためのoutputDataListというリストに追加している
				outputdatalist.add(csvformat.toString());
			}
		}
		// CSV形式で出力をする
		//　CSV形式の注文一覧をmodelオブジェクトのorderListという属性に入れる
		model.addAttribute("orderlist", outputdatalist);

		// 合計金額を出力する
		//　合計金額をmodelオブジェクトのtotalAmountという属性に入れる
		model.addAttribute("totalamount", totalamount);

		// お会計画面を返す
		return "bill";
	}

	/**
	 * menureturnメソッド 
	 * お会計画面からトップページ画面に遷移する
	 * 「トップに戻る」ボタン押下時実行
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/toppagereturn")
	public String menureturn(Model model) throws Exception {

		// トップページ画面のControllerにリダイレクトする
		return "redirect:toppage";
	}

}
