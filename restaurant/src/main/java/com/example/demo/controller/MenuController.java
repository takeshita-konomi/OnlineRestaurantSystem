package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import bean.Menu;
import menu.Menuload;


/**
 * MenuControllerクラス 
 * メニュー画面の初期表示
 */

@Controller
public class MenuController {


	@GetMapping("/menu")
	public String menu(
			Model model
			) throws Exception {
	
		
		Menuload menuload = new Menuload();

		List<Menu> menu = menuload.menucsv();
		
		
		model.addAttribute("menu", menu);
		
		
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
	  public String order( 
			  Model model,@ModelAttribute Menu menu
			  )throws Exception{  
		  	
		  	//メニューの表示
		  	Menuload menuload = new Menuload();
			List<Menu> menulist = menuload.menucsv();
			
			//入力された個数の取得
			String count = menu.getCount();
			
			//countは1つの文字列としてくるので個数(count)をカンマで分割して配列に代入
			String[] countcount = count.split(",");
			
			//配列の要素数をintに代入
			int countcountcount = countcount.length;
			
			List<Menu> order = new ArrayList<>();
			//menuの回数分処理をループする
			for(int i = 0; i < countcountcount; i++) {
				
			//menulistから項目を取得する(配列の[i]がきたときにmenulistの[i]行目に入れたい)
			Menu ordermenu = menulist.get(i);

			if(countcount[i] != null && countcount[i] != "") {
				//menuの注文個数にcountの値をいれる
				ordermenu.setCount(countcount[i]);
				order.add(ordermenu);
				}
			
			//ヘッダーの表示
			if(i == 0) {
				order.add(ordermenu);
			}
				
			}
			
			model.addAttribute("menu", menulist);
			
			
						
			//個数が入力されているところは値をorderに入れる
			model.addAttribute("order",order);
			
		
			//合計金額の計算
			
	 
		  	return "menu"; 
	  	}
	 
 /**
	 * メニュー画面の注文ボタン押下時実行
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	  @PostMapping("/billin") 
	  public String billin( 
			  Model model 
			  )throws Exception{  
	 
		  	return "redirect:/bill"; 
		  	}
	 
	  
	
}
