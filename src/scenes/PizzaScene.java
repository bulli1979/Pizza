package scenes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface PizzaScene {
	public void update();
	public void initialize();
	public Scene getScene();
	public void setNavigation(Pane navigation);
	public Pane getNavigation();
}
