package pojos;

import java.util.ArrayList;
import java.util.List;

public class Pizza{
	private double price;
	private String name;
	private String description;
	private List<Extra> extras;
	private int id;
	
	public Pizza(){
		extras = new ArrayList<Extra>();
	}
	
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
	public List<Extra> getExtras() {
		return extras;
	}
	public void setExtras(List<Extra> extras) {
		this.extras = extras;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Pizza clonePizza(){
        Pizza pizza = new Pizza();
        pizza.setDescription(this.description);
        pizza.setName(this.name);
        pizza.setPrice(this.price);
        pizza.setExtras(new ArrayList<Extra>());
        pizza.setId(this.id);
        return pizza;
    }
	
}
