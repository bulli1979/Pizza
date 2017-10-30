package scenes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import scenemanagement.SceneManager;

public interface PizzaScene {
	public void update();
	public void initialize();
	public Scene getScene();
	public void setNavigation(Pane navigation);
	public Pane getNavigation();
	public void setSceneManager(SceneManager sceneManager);
}
