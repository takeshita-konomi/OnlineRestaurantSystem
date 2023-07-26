package menu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import bean.Menu;
import bean.Message;

/**
 * メニュー画面の入力チェック
 */
public class Error {

	private boolean iserror;
	private List<Message> errorlist;

	public List<Message> getErrorlist() {
		return errorlist;
	}

	public Error() {
		iserror = false;
		errorlist = new ArrayList<>();
	}

	public boolean iserror() {
		return iserror;
	}

	public void errorcheck(Menu ordermenu) throws ParseException {

		Message mes = new Message();

		if (!StringUtils.hasLength(ordermenu.getCount())) {
			return;
		}

		// 注文個数が半角数字以外で入力されたときにエラー
		if (!ordermenu.getCount().matches("^[0-9]+$")) {
			mes.setMessage("ランキング [" + ordermenu.getRanking() + "] 注文個数は1-9の半角数字で入力してください");
			errorlist.add(mes);
		}

		// 0から始まる数字が入力されたときにエラー(例:009など)
		else if (ordermenu.getCount().matches("^0+([0-9]+.*)")) {
			mes.setMessage("ランキング [" + ordermenu.getRanking() + "] 正しく入力してください");
			errorlist.add(mes);
		}

		if (errorlist.size() != 0) {
			iserror = true;
		}

		return;

	}

	/**
	 * 注文0件のときエラーとする
	 * 
	 * @param menu 注文メニュー一覧
	 */
	public void ordercheck(List<Menu> menu) {

		if (iserror) {
			return;
		}

		Message mes = new Message();

		// ヘッダーが必ず一行入るので1
		if (menu.size() == 1) {
			mes.setMessage("注文個数が入力されていません");
			errorlist.add(mes);
			iserror = true;
		}

	}

}