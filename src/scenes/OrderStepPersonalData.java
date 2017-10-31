package scenes;

import application.Strings;
import application.StyleClassNames;
import elements.NavigationBuilder;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import scenemanagement.SceneHolder;

public class OrderStepPersonalData extends OrderStepScene implements PizzaScene {

	@Override
	public void initialize() {

		navigation = NavigationBuilder.buildNavigation(SceneHolder.STEPPERSONALDATA, sceneManager);
		center = createCenter();
		root = new VBox(10, navigation, center);
		scene = new Scene(root, sceneManager.getAppData().getWidth(), sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");

	}

	@Override
	public void update() {
		navigation = NavigationBuilder.buildNavigation(SceneHolder.STEPPERSONALDATA, sceneManager);
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
		deliveryDataTitlePane.setText(Strings.DELIVERY.getValue());
		deliveryDataTitlePane.setCollapsible(false);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10 ,10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		ComboBox<String> deliveryTimeComboBox = insertComboBox(grid, Strings.DELIVERYTIME.getValue(), 0);
		deliveryTimeComboBox.getItems().addAll("45 Minuten", "1 Stunde", "1.5 Stunden");
				
		deliveryDataTitlePane.setContent(grid);
		
		return deliveryDataTitlePane;
	}

	private TitledPane GetPaymentDataPane() {
		TitledPane paymentDataTitlePane = new TitledPane();
		GridPane.setConstraints(paymentDataTitlePane, 1, 1);		
		paymentDataTitlePane.setText(Strings.PAYMENT.getValue());
		paymentDataTitlePane.setCollapsible(false);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10 ,10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		final ToggleGroup group = new ToggleGroup();
		
		insertRadioButton(grid, group, Strings.CASH_PAYMENT_AT_DELIVERY.getValue(), 0, true);
		//insertRadioButton(grid, group, "VISA", 1);
		//insertRadioButton(grid, group, "Mastercard", 2);
		//insertRadioButton(grid, group, "PayPal", 3);
		
		paymentDataTitlePane.setContent(grid);
		
		return paymentDataTitlePane;
	}

	private TitledPane GetPersonalDataPane() {
		TitledPane personalDataTitlePane = new TitledPane();
		GridPane.setConstraints(personalDataTitlePane, 0, 0);		
		personalDataTitlePane.setText(Strings.PERSONAL_DATA.getValue());
		personalDataTitlePane.setCollapsible(false);
		personalDataTitlePane.setContent(new Button());
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10 ,10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		ComboBox<String> anredeComboBox = insertComboBox(grid, Strings.SALUTATION.getValue(), 0);
		anredeComboBox.getItems().addAll(Strings.MR.getValue(), Strings.MRS.getValue());
		
		insertTextField(grid, Strings.NAME.getValue(), 1);
		insertTextField(grid, Strings.FIRST_NAME.getValue(), 2);
		insertTextField(grid, Strings.COMPANY.getValue(), 3);
		insertTextField(grid, Strings.E_MAIL.getValue(), 4);
		insertTextField(grid, Strings.TELEFON.getValue(), 5);
		insertTextField(grid, Strings.MOBILE_PHONE.getValue(), 6);
		insertTextField(grid, Strings.ADDRESS.getValue(), 7);
		insertTextField(grid, Strings.ZIP_CODE.getValue(), 8);
		insertTextField(grid, Strings.CITY.getValue(), 9);
		
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
}
