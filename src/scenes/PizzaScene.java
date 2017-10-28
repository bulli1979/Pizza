package scenes;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public interface PizzaScene {
	public void update();
	public void initialize();
	public Scene getScene();
	public void setNavigation(HBox navigation);
	public HBox getNavigation();
}
