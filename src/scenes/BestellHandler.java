package scenes;

import application.SceneHolder;
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
		switch (sceneManager.getAppData().getCurrentScene()) {
		case HOME:
			sceneManager.setScene(SceneHolder.STEPPIZZA);
			break;
		case STEPPIZZA:
			sceneManager.setScene(SceneHolder.STEPEXTRAS);
			break;
		case STEPEXTRAS:
			sceneManager.setScene(SceneHolder.STEPPERSONALDATA);
			break;
		case STEPPERSONALDATA:
			sceneManager.setScene(SceneHolder.STEPOVERVIEW);
			break;
		case STEPOVERVIEW:
			sceneManager.setScene(SceneHolder.STEPTHANKYOU);
			break;
		default:
			sceneManager.setScene(SceneHolder.HOME);
		}

	}

	public static class Builder {
		SceneManager sceneManager;

		public Builder() {
		}

		public BestellHandler build() {
			return new BestellHandler(this);
		}

		public Builder setSceneManager(SceneManager sceneManager) {
			this.sceneManager = sceneManager;
			return this;
		}
	}
}
