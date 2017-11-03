package application;

import javafx.application.Application;
import javafx.stage.Stage;
import pojos.OrderData;
import scenemanagement.SceneHolder;
import scenemanagement.SceneManager;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private SceneManager sceneManager;


	@Override
	public void start(Stage primaryStage) {

		this.sceneManager = new SceneManager(this, primaryStage, new OrderData());
		sceneManager.initScenes();
		try {
			sceneManager.setScene(SceneHolder.HOME);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
