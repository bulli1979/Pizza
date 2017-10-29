package scenes;

import application.ScreenNames;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import scenemanagement.SceneManager;

public class BestellHandler implements EventHandler<ActionEvent> {
	private SceneManager sceneManager;
	private BestellHandler(Builder builder) {
		this.sceneManager = builder.sceneManager;
	}


	@Override
	public void handle(ActionEvent event) {
		sceneManager.setScene(ScreenNames.STEPPIZZA);
	}

	public static class Builder{
		SceneManager sceneManager;
		public Builder(){}
		public BestellHandler build(){
			return new BestellHandler(this);
		}
		public Builder setSceneManager(SceneManager sceneManager) {
			this.sceneManager = sceneManager;
			return this;
		}
	}
}
