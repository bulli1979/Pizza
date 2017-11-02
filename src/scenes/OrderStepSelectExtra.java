package scenes;

import application.Strings;
import application.StyleClassNames;
import data.DAOStaticData;
import elements.NavigationBuilder;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pojos.Extra;
import scenemanagement.OrderCalculator;
import scenemanagement.SceneHolder;

public class OrderStepSelectExtra extends OrderStepScene implements PizzaScene{
	
	public void initialize(){
				
		navigation = NavigationBuilder.buildNavigation(SceneHolder.HOME,sceneManager);
		center = createCenter();
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
		
	}
	
	public void update(){	
		root = new VBox(10,navigation,center);
		navigation = NavigationBuilder.buildNavigation(SceneHolder.HOME,sceneManager);
		center = createCenter();
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
	}
	
	private Pane createCenter() {
		try {
			VBox pizzaBox = new VBox(5);
			Image image = new Image("images/kaese.png");
			ImageView imageView = createImage(image);
			
			Label pizzaLabel = new Label(Strings.EXTRALABEL.getValue());
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
			for (Extra extra : DAOStaticData.getAllExtras()) {
				databox.getChildren().add(buildRow(image, extra, index));
				index++;
			}
			ScrollPane sp = new ScrollPane(databox);

			pizzaBox.getChildren().add(sp);

		
			Button forwardButton = new Button(Strings.FINISH.getValue());
			forwardButton.setOnAction(event -> {
				SceneHolder.STEPEXTRAS.getPizzaScene().update();
				sceneManager.getOrderData().setSelectExtraPizza(null);
				SceneHolder.STEPEXTRAS.getPizzaScene().update();
				sceneManager.setScene(SceneHolder.STEPEXTRAS);
			});
			forwardButton.getStyleClass().add(StyleClassNames.BESTELLBUTTON_CENTER.getValue());

			BorderPane pricePane = new BorderPane();
			setHeadLabelStyles(sceneManager.getListSize().getColumnFour(), pizzaLabel);
			priceCalculateLabel = new Label(decimalFormat.format(sceneManager.getOrderData().getPrice()) + " CHF");

			priceCalculateLabel.setPrefWidth(sceneManager.getListSize().getColumnFive());
			pricePane.setRight(priceCalculateLabel);

			BorderPane buttonPane = new BorderPane();
			buttonPane.setRight(new HBox(5, forwardButton));

			VBox centerBox = new VBox(10, pizzaBox, pricePane, buttonPane);
			centerBox.setPrefWidth(sceneManager.getWidth());
			centerBox.getStyleClass().add(StyleClassNames.CENTERBOX.getValue());
			return centerBox;
		} catch (Exception e) {
			return new HBox(0, new Text(Strings.ERROR.getValue()));
		}
	}

	private HBox buildRow(Image image, Extra extra, int index) {

		ImageView imageView = createImage(image);

		Label pizzaLabel = new Label(extra.getName());
		setColLabelStyles(sceneManager.getListSize().getColumnTwo(), pizzaLabel);

		Label descriptionLabel = new Label(extra.getDescription());
		setColLabelStyles(sceneManager.getListSize().getColumnThree(), descriptionLabel);

		Label priceLabel = new Label(decimalFormat.format(extra.getPrice()));
		setColLabelStyles(sceneManager.getListSize().getColumnFour(), priceLabel);

		TextField anzahlField = new TextField();
		anzahlField.setPrefWidth(sceneManager.getListSize().getColumnFive());
		anzahlField.setText(String.valueOf(OrderCalculator.getCountForExtra(sceneManager,extra)));
		anzahlField.setId(String.valueOf(extra.getId()));
		anzahlField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				try {
					int val = Integer.parseInt(newValue);
					OrderCalculator.calculateExtra(sceneManager, extra, val);
					double price = sceneManager.getOrderData().getPrice();
					priceCalculateLabel.setText(decimalFormat.format(price));
					priceCalculateLabel.requestLayout();
				} catch (Exception e) {
					anzahlField.setText("");
				}
			}
		});
		anzahlField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && anzahlField.getText().equals("")) {
				anzahlField.setText("0");
				OrderCalculator.calculateExtra(sceneManager, extra, 0);
				priceCalculateLabel.setText(decimalFormat.format(sceneManager.getOrderData().getPrice()));
				priceCalculateLabel.requestLayout();
			}
		});

		HBox row = new HBox(20, imageView, pizzaLabel, descriptionLabel, priceLabel, anzahlField);
		row.setAlignment(Pos.CENTER_LEFT);
		if (index % 2 == 0) {
			row.getStyleClass().add(StyleClassNames.LISTEVEN.getValue());
		}
		return row;
	}
}
