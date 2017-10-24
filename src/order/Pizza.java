package order;

import java.util.List;

public class Pizza {
	private double price;
	private String name;
	private List<Extras> extras;
	private int id;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Extras> getExtras() {
		return extras;
	}
	public void setExtras(List<Extras> extras) {
		this.extras = extras;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
