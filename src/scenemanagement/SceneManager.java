package scenemanagement;

import java.util.HashMap;
import java.util.Map;

import application.ApplicationData;
import application.Main;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import order.OrderData;
import scenes.HomeScene;
import scenes.PizzaScene;

public class SceneManager {
	private Map<String, PizzaScene> scenes;
	private Stage primaryStage;
	private double width;
	private double height;
	private Main application;
	private OrderData orderData;
	private ApplicationData appData;
	public SceneManager(Main application, Stage primaryStage, OrderData orderData){
		this.application = application;
		this.orderData = orderData;
		this.primaryStage = primaryStage;
		this.appData = new ApplicationData();
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		scenes = new HashMap<String,PizzaScene>();
		width = visualBounds.getWidth();
		height = visualBounds.getHeight();
		appData.setHeight(height);
		appData.setWidth(width);
		appData.setCurrentScene("home");
	}
	
	public void initScenes() {
		initHomeScene();
	}

	private void initHomeScene(){
		HomeScene homeScene = new HomeScene.Builder().giveSceneManager(this).build();
		homeScene.initialize();
		scenes.put("home",homeScene);
	}
	
	public void setScene(String sceneName){
		appData.setCurrentScene(sceneName);
		primaryStage.setScene(scenes.get(sceneName).getScene());
		primaryStage.show();
	}

	public Map<String, PizzaScene> getScenes() {
		return scenes;
	}

	public void setScenes(Map<String, PizzaScene> scenes) {
		this.scenes = scenes;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Main getApplication() {
		return application;
	}

	public void setApplication(Main application) {
		this.application = application;
	}

	public OrderData getOrderData() {
		return orderData;
	}

	public void setOrderData(OrderData orderData) {
		this.orderData = orderData;
	}

	public ApplicationData getAppData() {
		return appData;
	}

	public void setAppData(ApplicationData appData) {
		this.appData = appData;
	}
	
	
	
}
