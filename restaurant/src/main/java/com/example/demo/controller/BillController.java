package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import bean.Menu;
import bean.MenuList;

@Controller
public class BillController {

	@PostMapping("/bill")
	public String menu(Model model, @ModelAttribute MenuList orderList, String totalAmount) throws Exception {
		
		List<Menu> menulist = orderList.getMenu();
	
		// orderなので注文個数に値があるものが対象となる
		
		// CSV形式でリストを作成する
		List<String> outputDataList = new ArrayList<String>();
		
		for(Menu orderMenu : menulist) {
			StringBuffer csvFormat = new StringBuffer();
			// 注文個数に値があるものを出力対象とする
			if (StringUtils.hasLength(orderMenu.getCount())) {
				csvFormat.append(orderMenu.getRanking());
				csvFormat.append(",　");
				csvFormat.append(orderMenu.getName());
				csvFormat.append(", ");
				csvFormat.append(orderMenu.getKcal());
				csvFormat.append(", ");
				csvFormat.append(orderMenu.getPrice());
				csvFormat.append(", ");
				csvFormat.append(orderMenu.getCount());
				outputDataList.add(csvFormat.toString());
			}
		}
		// CSV出力
		model.addAttribute("orderList", outputDataList);
		
		// 合計金額
		model.addAttribute("totalAmount", totalAmount);
		
		return "bill";
	}

	/**
	 * お会計画面のメニューボタン押下時実行
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/toppagereturn")
	public String menureturn(Model model) throws Exception {
		
		
		return "redirect:/toppage";
	}

}
