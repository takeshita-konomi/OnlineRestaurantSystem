package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * トップページ画面の初期表示
 */
@Controller
public class ToppageController {

	/**
	 * menuメソッド 
	 * トップページ画面を表示する
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/toppage")
	public String menu(Model model) throws Exception {

		// トップページ画面を返している
		return "toppage";
	}

	/**
	 * menuinメソッド 
	 * トップページ画面からメニュー画面に遷移する
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/menuin")
	public String menuin(Model model) throws Exception {

		// メニュー画面のContollerにリダイレクトする
		return "redirect:/menu";
	}

}
