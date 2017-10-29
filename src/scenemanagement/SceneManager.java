package scenemanagement;

import java.util.HashMap;
import java.util.Map;

import application.ApplicationData;
import application.Main;
import application.ScreenNames;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import order.OrderData;
import scenes.HomeScene;
import scenes.OrderStepPersonalData;
import scenes.OrderStepPizzaScene;
import scenes.PizzaScene;

public class SceneManager {
	private Map<ScreenNames, PizzaScene> scenes;
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
		scenes = new HashMap<ScreenNames,PizzaScene>();
		width = visualBounds.getWidth();
		height = visualBounds.getHeight();
		appData.setHeight(height);
		appData.setWidth(width);
		appData.setCurrentScene(ScreenNames.HOME);
	}
	
	public void initScenes() {
		initHomeScene();
		initOrderPizzaScene();
		initPersonalDataScene();
	}

	private void initHomeScene(){
		HomeScene scene = new HomeScene.Builder().giveSceneManager(this).build();
		scene.initialize();
		scenes.put(ScreenNames.HOME,scene);
	}
	private void initOrderPizzaScene(){
		OrderStepPizzaScene scene = new OrderStepPizzaScene.Builder().giveSceneManager(this).build();
		scene.initialize();
		scenes.put(ScreenNames.STEPPIZZA,scene);
	}
	
	private void initPersonalDataScene(){
		OrderStepPersonalData scene = new OrderStepPersonalData.Builder().giveSceneManager(this).build();
		scene.initialize();
		scenes.put(ScreenNames.STEPPERSONALDATA,scene);
	}
	
	public void setScene(ScreenNames screen){
		appData.setCurrentScene(screen);
		primaryStage.setScene(scenes.get(screen).getScene());
		primaryStage.show();
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

	public Map<ScreenNames, PizzaScene> getScenes() {
		return scenes;
	}
	
	
	
}
