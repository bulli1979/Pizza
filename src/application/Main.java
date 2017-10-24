package application;
	
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import order.OrderData;
import scenes.HomeScene;
import scenes.PizzaScene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	Map<String, PizzaScene> scenes;
	Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
			OrderData orderData = new OrderData();
			BorderPane root = new BorderPane();
			scenes = new HashMap<String,PizzaScene>();
			double width = visualBounds.getWidth();
			double height = visualBounds.getHeight();
			HomeScene homeScene = new HomeScene.Builder().setRoot(root).withWidth(width).withHeight(height).data(orderData).application(this).build();
			homeScene.initialize();
			scenes.put("home",homeScene);
			
			primaryStage.setFullScreen(true);
			setScene("home");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setScene(String sceneName){
		primaryStage.setScene(scenes.get(sceneName).getScene());
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
