package bean;


public class Menu implements java.io.Serializable{
	//InvalidClassException(エラー)が出ないようにするために宣言している
	private static final long serialVersionUID = 1L;
	
	
	private String ranking;
	private String name;
	private String kcal;
	private String price;
	private String count;

	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKcal() {
		return kcal;
	}
	public void setKcal(String kcal) {
		this.kcal = kcal;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
