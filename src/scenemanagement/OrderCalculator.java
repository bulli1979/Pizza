package scenemanagement;

import java.util.Iterator;

import pojos.Extra;
import pojos.OrderData;
import pojos.Pizza;

public class OrderCalculator {

	public static void calculate(SceneManager sceneManager, Pizza pizza, int anzahl) {
		OrderData orderData = sceneManager.getOrderData();
		int count = 0;
		Iterator<Pizza> it = orderData.getPizzas().iterator();
		while (it.hasNext()) {
			Pizza pizzaObj = it.next();
			if (pizzaObj.getId() == pizza.getId()) {
				if(count>=anzahl){
					it.remove();
				}
				count++;
			}
		}
		if(anzahl>count){
			int newPizzas = anzahl - count;
			for(int i = 0; i < newPizzas;i++){
				try {
					orderData.getPizzas().add(pizza.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		calculatePrice(sceneManager);
	}
	
	public static int getCountForPizza(SceneManager sceneManager, Pizza pizza){
		int count = 0;
		for(Pizza pizzaObj : sceneManager.getOrderData().getPizzas()){
			if(pizzaObj.getId() == pizza.getId()){
				count++;
			}
		}
		return count;
	}

	
	public static void calculatePrice(SceneManager sceneManager){
		double price = 0;
		for(Pizza pizza : sceneManager.getOrderData().getPizzas()){
			price += pizza.getPrice();
			for(Extra extra : pizza.getExtras()){
				price += extra.getPrice();
			}
		}
		sceneManager.getOrderData().setPrice(price);
	}
	
}
