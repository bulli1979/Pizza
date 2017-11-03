package scenes;

import java.text.DecimalFormat;

import application.Strings;
import application.StyleClassNames;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import scenemanagement.SceneManager;

public abstract class OrderStepScene {
	
	protected Pane root;
	protected SceneManager sceneManager;
	protected Scene scene;
	protected Pane navigation;
	protected Pane center;
	protected Label priceCalculateLabel;
	protected String pattern = "###,##0.00 CHF";
	protected String patternTotal = "Total: ###,##0.00 CHF";
	protected DecimalFormat decimalFormat = new DecimalFormat(pattern);
	protected DecimalFormat decimalFormatTotal = new DecimalFormat(patternTotal);

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

	protected BorderPane createTotal() {
		BorderPane pricePane = new BorderPane();
		pricePane.setPrefWidth(sceneManager.getListSize().getColumnFour());
		priceCalculateLabel = new Label(decimalFormatTotal.format(sceneManager.getOrderData().getPrice()));
		priceCalculateLabel.setPrefWidth(sceneManager.getListSize().getColumnFive());
		priceCalculateLabel.setFont(Font.font (Strings.VERDANA.getValue(), 32));
		pricePane.setRight(priceCalculateLabel);
		return pricePane;
	}
	
}
