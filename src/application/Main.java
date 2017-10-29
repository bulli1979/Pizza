package application;

import javafx.application.Application;
import javafx.stage.Stage;
import pojos.OrderData;
import scenemanagement.SceneManager;

public class Main extends Application {

	private SceneManager sceneManager;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.sceneManager = new SceneManager(this, primaryStage, new OrderData());
		sceneManager.initScenes();
		try {
			
<<<<<<< HEAD
			sceneManager.setScene(SceneHolder.HOME);
=======
			sceneManager.setScene(ScreenNames.STEPPERSONALDATA);
>>>>>>> 303dcbb47f389be404f24f3c92a23773342e1a8e
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
