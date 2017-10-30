package scenes;

import java.text.DecimalFormat;

import application.StyleClassNames;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import scenemanagement.SceneManager;

public abstract class OrderStepScene {
	
	protected Pane root;
	protected SceneManager sceneManager;
	protected Scene scene;
	protected Pane navigation;
	protected Pane center;
	protected Label priceCalculateLabel;
	protected String pattern = "###,##0.00";
	protected DecimalFormat decimalFormat = new DecimalFormat(pattern);
	
	protected void setColLabelStyles(double width, Label label) {
		label.setAlignment(Pos.CENTER);
		label.setPrefWidth(width);
		label.getStyleClass().add(StyleClassNames.LISTCOLSTYLE.getValue());
	}
	
	protected void setHeadLabelStyles(double width, Label label) {
		label.setAlignment(Pos.CENTER);
		label.setPrefWidth(width);
		label.getStyleClass().add(StyleClassNames.LISTHEADSTYLE.getValue());
	}

	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	public Scene getScene() {
		return scene;
	}

	public Pane getNavigation() {
		return navigation;
	}

	public void setNavigation(Pane navigation) {
		this.navigation = navigation;
	}
	
}
