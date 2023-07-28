package menu;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;

import bean.Menu;

/**
 * メニュー情報をCSVファイルから読み込むクラス
 * 
 */
public class Menuload {

	/** MENU CSVの定数（フルパス） */
	private static final String MENU_CSV_FILE_NAME = "/menucsv/menu.csv";

	/**
	 * menucsvメソッド 
	 * menu.csvを読み込む
	 * @return
	 */
	public List<Menu> menucsv() {
		// Menu型Listのmenuのインスタンスをつくる
		List<Menu> menulist = new ArrayList<>();

		// テキストファイルを読み込むためにBufferedReader型のbrを宣言する
		BufferedReader br = null;

		try {
			// メニューのcsvファイルの連続するデータを順に必要な分だけ読み込む
			InputStream is = new ClassPathResource(MENU_CSV_FILE_NAME).getInputStream();
			// 読み込んだものをUTF-8の文字コードで読み込んでbrに代入する
			br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

			// 読み込み行をnullに設定する
			String line = null;

			// lineがnullではない間は処理を繰り返す(readLineは文字が終了するとnullを返してlineに代入する)
			while ((line = br.readLine()) != null) {
				//文字列内のカンマで区切られないようにするためにダブルコーテーションの直前だけで区切るように設定している
				Pattern cPattern = Pattern.compile(",(?=(([^\"]*\"){2})*[^\"]*$)");
				// カンマで区切って配列に入れる
				String[] columns = cPattern.split(line, -1);
				
				// メニューのインスタンスをつくる
				Menu menu = new Menu();
				// 配列の1番目をランキングに代入する
				menu.setRanking(columns[0]);
				// 配列の2番目をメニュー名に代入する
				menu.setName(columns[1]);
				// 配列の3番目はダブルコーテーションを消してカロリーに代入する
				menu.setKcal(columns[2].replace("\"", ""));
				// 配列の4番目はダブルコーテーションを消して￥マークを￥に変換（全角に変換）して金額に代入する
				menu.setPrice(columns[3].replace("\"", "").replace("\\", "￥"));
				// 配列の5番目を注文個数に代入する
				menu.setCount(columns[4]);
				// メニューを表示させるためのmenulistに代入する
				menulist.add(menu);
			}

		} catch (Exception e) {
			//例外が発生した時に例外が発生したメソッドと例外が発生するまでに経てきたメソッドがわかるようにする
			e.printStackTrace();
			//コンソールにメッセージを表示する
			System.out.println(e.getMessage());

		} finally {
			try {
				//brがnullじゃないときBufferedReaderを閉じる
				if (br != null) {
					br.close();
				}
			} catch (Exception e2) {
				//コンソールにメッセージを表示する
				System.out.println(e2.getMessage());
			}

		}
		//メニューの一覧が入っているmenulistを返す
		return menulist;

	}
}
