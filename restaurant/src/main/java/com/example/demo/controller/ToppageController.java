package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * MenuControllerクラス 
 * メニュー画面の初期表示
 */

@Controller
public class ToppageController {


	@GetMapping("/toppage")
	public String menu(
			Model model
			) throws Exception {
	
		
		  return "toppage";
	  }

	
	
	/**
	 * トップ画面のメニューボタン押下時実行
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	  @PostMapping("/menuin") 
	  public String register( 
			  Model model 
			  )throws Exception{  
	 
		  	return "redirect:/menu"; 
	  	}
	 
	 
	  
	
}
