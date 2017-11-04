package scenes;

import java.util.List;

import application.Strings;
import application.StyleClassNames;
import data.DAOStaticData;
import elements.NavigationBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pojos.Pizza;
import scenemanagement.OrderCalculator;
import scenemanagement.SceneHolder;

public class OrderStepPizzaScene extends OrderStepScene implements PizzaScene {
	private List<Pizza> pizzaList;
	public void initialize() {

		navigation = NavigationBuilder.buildNavigation(SceneHolder.STEPPIZZA, sceneManager);
		center = createCenter();
		root = new VBox(10, navigation, center);
		scene = new Scene(root, sceneManager.getAppData().getWidth(), sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");

	}

	public void update() {
		navigation = NavigationBuilder.buildNavigation(SceneHolder.STEPPIZZA, sceneManager);
		root = new VBox(10, navigation, center);
		scene = new Scene(root, sceneManager.getAppData().getWidth(), sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
	}

	private Pane createCenter() {
		try {
			if(pizzaList == null){
				pizzaList = DAOStaticData.getAll();
			}
			VBox pizzaBox = new VBox(0);
			
			Image image = new Image("images/pizza.png");
			ImageView imageView = createImage(image);
			
			Label pizzaLabel = new Label(Strings.PIZZALABEL.getValue());
			setHeadLabelStyles(sceneManager.getListSize().getColumnTwo(), pizzaLabel);
			
			Label descriptionLabel = new Label(Strings.DESCRIPTIONLABEL.getValue());
			setHeadLabelStyles(sceneManager.getListSize().getColumnThree(), descriptionLabel);

			Label priceLabel = new Label(Strings.PRICELABEL.getValue());
			setHeadLabelStyles(sceneManager.getListSize().getColumnFour(), priceLabel);

			Label anzahlLabel = new Label(Strings.ANZAHLLABEL.getValue());
			setHeadLabelStyles(sceneManager.getListSize().getColumnFive(), anzahlLabel);

			HBox headRow = new HBox(20, imageView, pizzaLabel, descriptionLabel, priceLabel, anzahlLabel);
			headRow.setAlignment(Pos.CENTER_LEFT);
			headRow.getStyleClass().add(StyleClassNames.LISTEVEN.getValue());
			pizzaBox.getChildren().add(headRow);

			VBox databox = new VBox(5);
			int index = 1;
			for (Pizza pizza : pizzaList) {
				databox.getChildren().add(buildRow(image, pizza, index));
				index++;
			}
			ScrollPane sp = new ScrollPane(databox);
			pizzaBox.getChildren().add(sp);

			Button personalDataButton = new Button(Strings.NOEXTRAS.getValue());
			personalDataButton.setOnAction(event -> 
				sceneManager.setScene(SceneHolder.STEPPERSONALDATA)
			);
			personalDataButton.getStyleClass().add(StyleClassNames.BESTELLBUTTON_CENTER.getValue());

			Button forwardButton = new Button(Strings.FORWARD.getValue());
			forwardButton.setOnAction(event -> {
				SceneHolder.STEPEXTRAS.getPizzaScene().update();
				sceneManager.setScene(SceneHolder.STEPEXTRAS);
			});
			forwardButton.getStyleClass().add(StyleClassNames.BESTELLBUTTON_CENTER.getValue());

			
			BorderPane pricePane = createTotal();

			BorderPane buttonPane = new BorderPane();
			buttonPane.setRight(new HBox(5, personalDataButton, forwardButton));
			
			VBox centerBox = new VBox(10, pizzaBox, pricePane, buttonPane);
			centerBox.setPrefWidth(sceneManager.getWidth());
			centerBox.getStyleClass().add(StyleClassNames.CENTERBOX.getValue());
			return centerBox;
		} catch (Exception e) {
			return new HBox(0, new Text(Strings.ERROR.getValue()));
		}
	}


	

	private HBox buildRow(Image image, Pizza pizza, int index) {

		ImageView imageView = createImage(image);
		Label pizzaLabel = new Label(pizza.getName());
		setColLabelStyles(sceneManager.getListSize().getColumnTwo(), pizzaLabel);

		Text descriptionLabel = new Text(pizza.getDescription());
		descriptionLabel.maxWidth(sceneManager.getListSize().getColumnThree());
		descriptionLabel.setWrappingWidth(sceneManager.getListSize().getColumnThree());
		HBox descBox = new HBox(descriptionLabel);
		descBox.setPrefWidth(sceneManager.getListSize().getColumnThree());

		
		Label priceLabel = new Label(decimalFormat.format(pizza.getPrice()));
		setColLabelStyles(sceneManager.getListSize().getColumnFour(), priceLabel);

		HBox amountBox = new HBox(5);
		amountBox.setPrefWidth(sceneManager.getListSize().getColumnFive());
		TextField amountField = createAmountField(pizza);
		VBox arrows = createArrows(pizza, amountField);
		amountBox.getChildren().addAll(amountField,arrows);
		amountBox.setAlignment(Pos.CENTER_LEFT);
		HBox row = new HBox(20, imageView, pizzaLabel, descBox, priceLabel, amountBox);
		row.setAlignment(Pos.CENTER_LEFT);
		if (index % 2 == 0) {
			row.getStyleClass().add(StyleClassNames.LISTEVEN.getValue());
		}
		return row;
	}

	
	private TextField createAmountField(Pizza pizza) {
		TextField amountField = new TextField();
		amountField.setText(String.valueOf(OrderCalculator.getCountForPizza(sceneManager, pizza)));
		amountField.setId(String.valueOf(pizza.getId()));
		amountField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				try {
					int val = Integer.parseInt(newValue);
					OrderCalculator.calculate(sceneManager, pizza, val);
					priceCalculateLabel.setText(decimalFormat.format(sceneManager.getOrderData().getPrice()));
				} catch (Exception e) {
					amountField.setText("");
				}
			}
		});
		amountField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && amountField.getText().equals("")) {
				amountField.setText("0");
				OrderCalculator.calculate(sceneManager, pizza, 0);
				priceCalculateLabel.setText(decimalFormat.format(sceneManager.getOrderData().getPrice()));
			}
		});
		return amountField;
	}

	private VBox createArrows(Pizza pizza, TextField anzahlField) {
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
