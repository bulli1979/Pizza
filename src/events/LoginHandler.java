package events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import scenemanagement.SceneManager;

public class LoginHandler implements EventHandler<ActionEvent> {

	private SceneManager sceneManager;
	
	private LoginHandler(Builder builder) {
		this.sceneManager = builder.sceneManager;
	}


	@Override
	public void handle(ActionEvent event) {
		sceneManager.getAppData().setLoggedIn(true);
		sceneManager.setScene(sceneManager.getAppData().getCurrentScene());
	}

	public static class Builder{
		SceneManager sceneManager;
		public Builder(){}
		public LoginHandler build(){
			return new LoginHandler(this);
		}
		public Builder setSceneManager(SceneManager sceneManager) {
			this.sceneManager = sceneManager;
			return null;
		}
	}
	
}