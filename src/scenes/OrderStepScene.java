package scenes;

import java.text.DecimalFormat;

import application.StyleClassNames;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pojos.Pizza;
import scenemanagement.OrderCalculator;
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
		label.setPrefWidth(width);
		label.setMinWidth(width);
		label.getStyleClass().add(StyleClassNames.LISTCOLSTYLE.getValue());
	}
	
	protected void setHeadLabelStyles(double width, Label label) {
		label.setPrefWidth(width);
		label.setMinWidth(width);
		label.setMaxWidth(width);
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
	
	protected ImageView createImage(Image image) {
		ImageView imageView = new ImageView();
		imageView.setFitWidth(sceneManager.getListSize().getColumnOne());
		imageView.setImage(image);
		imageView.setFitHeight(calculateImageHeight(sceneManager.getListSize().getColumnOne(), image.getWidth(), image.getHeight()));
		return imageView;
	}
	
	private double calculateImageHeight(double objWidth,double imageWidth,double imageHeight){
		return imageHeight / (imageWidth / objWidth); 
	}

	protected VBox createArrows(Pizza pizza, TextField anzahlField) {
		VBox arrows = new VBox(5);
		arrows.setPadding(new Insets(20, 0, 0, 0));
		Image arrowUp = new Image("images/arrowUp.png");
		ImageView up = new ImageView(arrowUp);
		HBox upHolder = new HBox(up);
		up.setFitWidth(arrowUp.getWidth()/4);
		up.setFitHeight(arrowUp.getHeight()/4);

		upHolder.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
			int anzahl = Integer.parseInt(anzahlField.getText());
			anzahl++;
			anzahlField.setText(String.valueOf(anzahl));
			OrderCalculator.calculate(sceneManager, pizza, anzahl);
		});
		
		Image arrowDown = new Image("images/arrowDown.png");
		ImageView down = new ImageView(arrowDown);
		HBox downHolder = new HBox(down);

		down.setFitWidth(arrowDown.getWidth()/4);
		down.setFitHeight(arrowDown.getHeight()/4);

		downHolder.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
			int anzahl = Integer.parseInt(anzahlField.getText());
			if(anzahl >0){
				anzahl--;
				anzahlField.setText(String.valueOf(anzahl));
				OrderCalculator.calculate(sceneManager, pizza, anzahl);
			}
		});
		arrows.getChildren().addAll(upHolder,downHolder);
		return arrows;
	}
	
}
