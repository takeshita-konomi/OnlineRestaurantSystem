package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BillController {

	@GetMapping("/bill")
	public String menu(Model model) throws Exception {

		return "bill";
	}

	/**
	 * お会計画面のメニューボタン押下時実行
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/toppagereturn")
	public String menureturn(Model model) throws Exception {

		return "redirect:/toppage";
	}

}
