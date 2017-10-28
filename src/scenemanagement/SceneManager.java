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
	public SceneManager(Main application, Stage primaryStage, OrderData orderData)
	{
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
		initScenes();
		primaryStage.show();
		primaryStage.setFullScreen(true);
	}
	
	private void initScenes() {
		initHomeScene();
	}

	private void initHomeScene()
	{
		HomeScene homeScene = new HomeScene.Builder().withAppData(appData).data(orderData).application(application).build();
		homeScene.initialize();
		scenes.put("home",homeScene);
	}
	
	public void setScene(String sceneName){
		primaryStage.setScene(scenes.get(sceneName).getScene());
	}
	
}
