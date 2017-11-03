package scenes;

import java.util.List;
import application.Strings;
import application.StyleClassNames;
import elements.NavigationBuilder;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pojos.Extra;
import pojos.Pizza;
import scenemanagement.OrderCalculator;
import scenemanagement.SceneHolder;

public class OrderStepExtraScene extends OrderStepScene implements PizzaScene {
	
	public void initialize() {

		navigation = NavigationBuilder.buildNavigation(SceneHolder.STEPEXTRAS, sceneManager);
		center = createCenter();
		root = new VBox(10, navigation, center);
		scene = new Scene(root, sceneManager.getAppData().getWidth(), sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");

	}

	public void update() {
		root = new VBox(10, navigation, center);
		center = createCenter();
		navigation = NavigationBuilder.buildNavigation(SceneHolder.STEPEXTRAS, sceneManager);
		scene = new Scene(root, sceneManager.getAppData().getWidth(), sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
		
	}

	private Pane createCenter() {
		try {
			List<Pizza> pizzaList = sceneManager.getOrderData().getPizzas();
			VBox pizzaBox = new VBox(5);
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

			Button forwardButton = new Button(Strings.FORWARD.getValue());
			forwardButton.setOnAction(event -> sceneManager.setScene(SceneHolder.STEPPERSONALDATA));
			forwardButton.getStyleClass().add(StyleClassNames.BESTELLBUTTON_CENTER.getValue());

			BorderPane pricePane = createTotal();

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

	private Node buildRow(Image image, Pizza pizza, int index) {
		ImageView imageView = createImage(image);
		Label pizzaLabel = new Label(pizza.getName());
		setColLabelStyles(sceneManager.getListSize().getColumnTwo(), pizzaLabel);

		Text descriptionLabel = new Text(pizza.getDescription());
		descriptionLabel.maxWidth(sceneManager.getListSize().getColumnThree());
		descriptionLabel.setWrappingWidth(sceneManager.getListSize().getColumnThree());
		HBox descBox = new HBox(descriptionLabel);
		descBox.setPrefWidth(sceneManager.getListSize().getColumnThree());

		
		
		VBox extraBox = new VBox(2);
		
		double price = pizza.getPrice();
		int extraIndex = 0;
		for(Extra extra : pizza.getExtras()){
			final int eIndex = extraIndex;
			price += extra.getPrice();
			Button delete = new Button("X");
			delete.setOnAction( event ->{
				removeExtra(pizza.getExtras(),eIndex);
				OrderCalculator.calculatePrice(sceneManager);
				SceneHolder.STEPEXTRAS.getPizzaScene().update();
				sceneManager.setScene(SceneHolder.STEPEXTRAS);
			});
			
			HBox extraRow = new HBox(3,new Text(extra.getName()),delete);
			extraBox.getChildren().add(extraRow);
			extraIndex++;
		}
		Button addExtra = new Button(Strings.ADD_EXTRA.getValue());
		addExtra.setOnAction( event -> {
			sceneManager.getOrderData().setSelectExtraPizza(pizza);
			SceneHolder.STEPEXTRACHANGE.getPizzaScene().update();
			sceneManager.setScene(SceneHolder.STEPEXTRACHANGE);
			
		});
		extraBox.getChildren().add(addExtra);
		extraBox.setPrefWidth(sceneManager.getListSize().getColumnFive());
		Label priceLabel = new Label(decimalFormat.format(price));
		setColLabelStyles(sceneManager.getListSize().getColumnFour(), priceLabel);		
		
		HBox row = new HBox(20, imageView, pizzaLabel, descBox, priceLabel, extraBox);
		row.setAlignment(Pos.CENTER_LEFT);
		if (index % 2 == 0) {
			row.getStyleClass().add(StyleClassNames.LISTEVEN.getValue());
		}
		return row;
	}

	private void removeExtra(List<Extra> extras, int index) {
		extras.remove(index);
	}

}
