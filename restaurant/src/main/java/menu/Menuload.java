package menu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;

import bean.Menu;

/**
 * メニュー情報をCSVファイルから読み込むクラス
 * 
 */
public class Menuload {

	/** MENU CSVの定数（フルパス）*/
	private static final String MENU_CSV_FILE_NAME = "/menucsv/menu.csv";

	public List<Menu> menucsv() {

		List<Menu> menulist = new ArrayList<>();
		
		BufferedReader br = null;

		try {
			
			InputStream is = new ClassPathResource(MENU_CSV_FILE_NAME).getInputStream();
			br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

			String line = null;
			while ((line = br.readLine()) != null) {

				Pattern cPattern = Pattern.compile(",(?=(([^\"]*\"){2})*[^\"]*$)");
				String[] columns = cPattern.split(line, -1);

				Menu menu = new Menu();

				menu.setRanking(columns[0]);
				menu.setName(columns[1]);
				menu.setKcal(columns[2].replace("\"", ""));
				// ￥マークを￥に変換（全角に変換）
				menu.setPrice(columns[3].replace("\"", "").replace("\\", "￥"));
				menu.setCount(columns[4]);

				menulist.add(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return menulist;
	}
}
