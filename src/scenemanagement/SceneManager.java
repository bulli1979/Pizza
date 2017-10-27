package scenemanagement;

import java.util.HashMap;
import java.util.Map;

import application.Main;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
	
	public SceneManager(Main application, Stage primaryStage, OrderData orderData)
	{
		this.application = application;
		this.orderData = orderData;
		this.primaryStage = primaryStage;
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		scenes = new HashMap<String,PizzaScene>();
		width = visualBounds.getWidth();
		height = visualBounds.getHeight();
		
		initScenes();
		
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}
	
	private void initScenes() {
		initHomeScene();
	}

	private void initHomeScene()
	{
		HomeScene homeScene = new HomeScene.Builder().withWidth(width).withHeight(height).data(orderData).application(application).build();
		homeScene.initialize();
		scenes.put("home",homeScene);
	}
	
	public void setScene(String sceneName){
		primaryStage.setScene(scenes.get(sceneName).getScene());
	}
	
}
