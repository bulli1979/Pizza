package scenes;

import application.SceneHolder;
import application.Strings;
import application.StyleClassNames;
import elements.NavigationBuilder;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
<<<<<<< HEAD
=======
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
>>>>>>> 303dcbb47f389be404f24f3c92a23773342e1a8e
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scenemanagement.SceneManager;

public class OrderStepPersonalData implements PizzaScene {
	private Pane root;
	private SceneManager sceneManager;
	private Scene scene;
	private Pane navigation;
	private Pane center;

	public OrderStepPersonalData() {
	}

	private OrderStepPersonalData(Builder builder) {
		this.sceneManager = builder.sceneManager;
	}
<<<<<<< HEAD
	
	public void initialize(){
				
		navigation = NavigationBuilder.buildNavigation(SceneHolder.HOME,sceneManager);
=======

	@Override
	public void initialize() {

		navigation = NavigationBuilder.buildNavigation(ScreenNames.HOME, sceneManager);
>>>>>>> 303dcbb47f389be404f24f3c92a23773342e1a8e
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
		grid.setVgap(14);
		grid.setHgap(14);
		
		VBox vboxColumn1 = new VBox();
		vboxColumn1.getChildren().add(GetPersonalDataPane());
		GridPane.setConstraints(vboxColumn1, 0, 0);
		
		VBox vboxColumn2 = new VBox();
		vboxColumn2.getChildren().addAll(GetPaymentDataPane() , GetDeliveryDataPane());
		vboxColumn2.setSpacing(14);
		GridPane.setConstraints(vboxColumn2, 1, 0);
		
		grid.getChildren().addAll(vboxColumn1, vboxColumn2);
		
		VBox centerBox = new VBox(10);
		centerBox.getChildren().add(grid);
		centerBox.setPrefWidth(sceneManager.getWidth());
		centerBox.getStyleClass().add(StyleClassNames.CENTERBOX.getValue());
		return centerBox;
	}

	private TitledPane GetDeliveryDataPane() {
		TitledPane deliveryDataTitlePane = new TitledPane();
		GridPane.setConstraints(deliveryDataTitlePane, 1, 0);	
		deliveryDataTitlePane.setText("Lieferung");
		deliveryDataTitlePane.setCollapsible(false);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10 ,10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		ComboBox<String> deliveryTimeComboBox = insertComboBox(grid, "Lieferzeit", 0);
		deliveryTimeComboBox.getItems().addAll("45 min", "1 stunde", "1.5 stunden");
				
		deliveryDataTitlePane.setContent(grid);
		
		return deliveryDataTitlePane;
	}

	private TitledPane GetPaymentDataPane() {
		TitledPane paymentDataTitlePane = new TitledPane();
		GridPane.setConstraints(paymentDataTitlePane, 1, 1);		
		paymentDataTitlePane.setText("Zahlung");
		paymentDataTitlePane.setCollapsible(false);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10 ,10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		final ToggleGroup group = new ToggleGroup();
		
		insertRadioButton(grid, group, "Bar / mit Karte bei Lieferung", 0, true);
		//insertRadioButton(grid, group, "VISA", 1);
		//insertRadioButton(grid, group, "Mastercard", 2);
		//insertRadioButton(grid, group, "PayPal", 3);
		
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
	
	private void insertRadioButton(GridPane grid, ToggleGroup group, String name, int row, boolean checked) {
		Label label = new Label(name);
		GridPane.setConstraints(label, 1, row);
		RadioButton radioButton = new RadioButton();
		radioButton.setToggleGroup(group);
		radioButton.setSelected(checked);
		GridPane.setConstraints(radioButton, 2, row);
		grid.getChildren().addAll(label, radioButton);
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
	public Pane getNavigation() {
		return navigation;
	}

	@Override
<<<<<<< HEAD
	public void setNavigation(Pane navigation) {		
=======
	public void setNavigation(HBox navigation) {
>>>>>>> 303dcbb47f389be404f24f3c92a23773342e1a8e
		this.navigation = navigation;
	}

}
