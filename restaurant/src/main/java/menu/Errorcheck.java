package menu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import bean.Menu;
import bean.Message;

/**
 * メニュー画面の入力チェックをするクラス
 */
public class Errorcheck {

	// boolean型の変数iserrorの宣言する
	private boolean iserror;

	// Message型Listのerrorlistの宣言する
	private List<Message> errorlist;

	/**
	 * getErrorlistメソッド
	 * 
	 * @return
	 */
	public List<Message> getErrorlist() {
		// errorlistを返す
		return errorlist;
	}

	// エラーチェックの準備をする
	public Errorcheck() {
		// 最初はエラーがないのでiserrorをfalseにする
		iserror = false;
		// errorlistのインスタンスをつくる
		errorlist = new ArrayList<>();
	}

	/**
	 * isErrorメソッド
	 * 
	 * @return
	 */
	public boolean iserror() {
		// iserrorを返す
		return iserror;
	}

	/**
	 * errorCheckメソッド 入力チェックをする
	 * 
	 * @param ordermenu
	 * @throws ParseException
	 */
	public void errorcheck(Menu ordermenu) throws ParseException {

		// メッセージを入れるためにインスタンスをつくる
		Message mes = new Message();

		// 注文個数の値が入っているところの判定する(haslength→null,空文字以外)
		if (!StringUtils.hasLength(ordermenu.getCount())) {
			// 戻り値なし
			return;
		}

		// 注文個数が半角数字以外で入力されたときにエラーにする
		if (!ordermenu.getCount().matches("[0-9]+")) {
			// setMessageメソッドにエラーメッセージを代入する
			mes.setMessage("「" + ordermenu.getName() + "」の注文個数は1-9の半角数字で入力してください");
			// errorlistに追加する
			errorlist.add(mes);
		}
	
		// 注文個数が6桁以上で入力されたときにエラーにする
		else if (!ordermenu.getCount().matches("[0-9]{1,5}+")) {
			// setMessageメソッドにエラーメッセージを代入する
			mes.setMessage("「" + ordermenu.getName() + "」の注文個数は6桁以上入力できません");
			// errorlistに追加する
			errorlist.add(mes);
		}

		// 0から始まる数字が入力されたときにエラーにする(例:009など)
		else if (ordermenu.getCount().matches("^0+([0-9]+)")) {
			// setMessageメソッドにエラーメッセージを代入する
			mes.setMessage("「" + ordermenu.getName() + "」を正しく入力してください");
			// errorlistに追加する
			errorlist.add(mes);
		}

		// 注文個数が0のときにエラーにする
		else if (ordermenu.getCount().matches("0")) {
			// setMessageメソッドにエラーメッセージを代入する
			mes.setMessage("「" + ordermenu.getName() + "」は注文個数が0です");
			// errorlistに追加する
			errorlist.add(mes);
		}

		// エラーのリストに中身が入っているときiserrorをtrueにする
		if (errorlist.size() != 0) {
			iserror = true;
		}

		// 戻り値なし
		return;

	}

	
	/**
	 * ordercheckメソッド 注文0件のときエラーとする
	 * 
	 * @param menu 注文メニュー一覧
	 */
	public void ordercheck(List<Menu> menu) {

		// iserrorがtrueのときこのメソッドの処理を終わらせる
		if (iserror) {
			// 戻り値なし
			return;
		}

		// メッセージを入れるためにインスタンスをつくる
		Message mes = new Message();

		// ヘッダーが必ず一行入るのでsizeを1にしている
		// Menu型Listのmenuの中身が1のとき注文個数が入力されていないのでメッセージを出す
		if (menu.size() == 1) {
			// setMessageメソッドにエラーメッセージを代入する
			mes.setMessage("注文個数が入力されていません");
			// errorlistに追加する
			errorlist.add(mes);
			// iserrorをtrueにする
			iserror = true;
		}

	}

}