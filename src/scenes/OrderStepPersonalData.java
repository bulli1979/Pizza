package scenes;

import application.ScreenNames;
import application.Strings;
import application.StyleClassNames;
import elements.NavigationBuilder;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scenemanagement.SceneManager;

public class OrderStepPersonalData implements PizzaScene {
	private Pane root;
	private SceneManager sceneManager;
	private Scene scene;
	private HBox navigation;
	private Pane center;

	public OrderStepPersonalData() {
	}

	private OrderStepPersonalData(Builder builder) {
		this.sceneManager = builder.sceneManager;
	}

	@Override
	public void initialize() {

		navigation = NavigationBuilder.buildNavigation(ScreenNames.HOME, sceneManager);
		center = createCenter();
		root = new VBox(10, navigation, center);
		scene = new Scene(root, sceneManager.getAppData().getWidth(), sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");

	}

	@Override
	public void update() {
		root = new VBox(10, navigation, center);
		scene = new Scene(root, sceneManager.getAppData().getWidth(), sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
	}

	private VBox createCenter(){

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10 ,10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		grid.getChildren().addAll(GetPersonalDataPane(), GetPaymentDataPane(), GetDeliveryDataPane());
		
		VBox centerBox = new VBox(10);
		centerBox.getChildren().add(grid);
		centerBox.setPrefWidth(sceneManager.getWidth());
		centerBox.getStyleClass().add(StyleClassNames.CENTERBOX.getValue());
		return centerBox;
	}

	private TitledPane GetDeliveryDataPane() {
		TitledPane deliveryDataTitlePane = new TitledPane();
		GridPane.setConstraints(deliveryDataTitlePane, 1, 1);	
		deliveryDataTitlePane.setText("Lieferung");
		deliveryDataTitlePane.setCollapsible(false);
		deliveryDataTitlePane.setContent(new Button());
		return deliveryDataTitlePane;
	}

	private TitledPane GetPaymentDataPane() {
		TitledPane paymentDataTitlePane = new TitledPane();
		GridPane.setConstraints(paymentDataTitlePane, 1, 0);		
		paymentDataTitlePane.setText("Zahlung");
		paymentDataTitlePane.setCollapsible(false);

		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10 ,10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		ComboBox<String> lieferzeitComboBox = insertComboBox(grid, "Lieferzeit", 0);
		lieferzeitComboBox.getItems().addAll("45 min", "1 stunde", "1.5 stunden");
				
		paymentDataTitlePane.setContent(grid);
		
		return paymentDataTitlePane;
	}

	private TitledPane GetPersonalDataPane() {
		TitledPane personalDataTitlePane = new TitledPane();
		GridPane.setConstraints(personalDataTitlePane, 0, 0);		
		personalDataTitlePane.setText("Persönliche Daten");
		personalDataTitlePane.setCollapsible(false);
		personalDataTitlePane.setContent(new Button());
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10 ,10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		ComboBox<String> anredeComboBox = insertComboBox(grid, "Anrede", 0);
		anredeComboBox.getItems().addAll("Herr", "Frau");
		
		insertTextField(grid, "Name", 1);
		insertTextField(grid, "Vorname", 2);
		insertTextField(grid, "Firma", 3);
		insertTextField(grid, "E-Mail", 4);
		insertTextField(grid, "Telefon", 5);
		insertTextField(grid, "Mobile", 6);
		insertTextField(grid, "Strasse", 7);
		insertTextField(grid, "PLZ", 8);
		insertTextField(grid, "Ort", 9);
		
		personalDataTitlePane.setContent(grid);
		
		return personalDataTitlePane;
	}

	private ComboBox<String> insertComboBox(GridPane grid, String name, int row) {
		Label label = new Label(name);
		GridPane.setConstraints(label, 1, 0);
		ComboBox<String> comboBox = new ComboBox<String>();
		GridPane.setConstraints(comboBox, 2, 0);
		grid.getChildren().addAll(label, comboBox);
		return comboBox;
	}

	private void insertTextField(GridPane grid, String name, int row) {
		Label label = new Label(name);
		GridPane.setConstraints(label, 1, row);
		TextField textField = new TextField();
		GridPane.setConstraints(textField, 2, row);
		grid.getChildren().addAll(label, textField);
	}

	public static class Builder {
		private SceneManager sceneManager;

		public Builder() {
		}

		public Builder giveSceneManager(SceneManager sceneManager) {
			this.sceneManager = sceneManager;
			return this;
		}

		public OrderStepPersonalData build() {
			return new OrderStepPersonalData(this);
		}

	}

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public HBox getNavigation() {
		return navigation;
	}

	@Override
	public void setNavigation(HBox navigation) {
		this.navigation = navigation;
	}

}
