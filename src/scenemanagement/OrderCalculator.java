package scenemanagement;

import java.util.Iterator;

import pojos.Extra;
import pojos.OrderData;
import pojos.Pizza;

public class OrderCalculator {

	private OrderCalculator(){}
	
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
				orderData.getPizzas().add(pizza.clonePizza());
			}
		}
		calculatePrice(sceneManager);
	}
	
	public static void calculateExtra(SceneManager sceneManager, Extra extra, int anzahl) {
		OrderData orderData = sceneManager.getOrderData();
		int count = 0;
		Iterator<Extra> it = orderData.getSelectExtraPizza().getExtras().iterator();
		while (it.hasNext()) {
			Extra extraObj = it.next();
			if (extraObj.getId() == extra.getId()) {
				if(count>=anzahl){
					it.remove();
				}
				count++;
			}
		}
		if(anzahl>count){
			int newExtras = anzahl - count;
			for(int i = 0; i < newExtras;i++){
				orderData.getSelectExtraPizza().getExtras().add(extra);
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

	public static int getCountForExtra(SceneManager sceneManager,Extra extra) {
		int count = 0;
		Pizza selExtraPizza = sceneManager.getOrderData().getSelectExtraPizza();
		if(selExtraPizza != null){
			for(Extra extraObj : sceneManager.getOrderData().getSelectExtraPizza().getExtras()){
				if(extraObj.getId() == extra.getId()){
					count++;
				}
			}
		}
		return count;
	}
	
}
