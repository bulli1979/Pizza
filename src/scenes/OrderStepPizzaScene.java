package scenes;

import java.text.DecimalFormat;
import java.util.List;

import application.SceneHolder;
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
import pojos.Pizza;
import scenemanagement.OrderCalculator;
import scenemanagement.SceneManager;

public class OrderStepPizzaScene implements PizzaScene {
	private Pane root;
	private SceneManager sceneManager;
	private Scene scene;
	private Pane navigation;
	private Pane center;
	private Label priceCalculateLabel;
	String pattern = "###,##0.00";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);

	public OrderStepPizzaScene() {
	}

	private OrderStepPizzaScene(Builder builder) {
		this.sceneManager = builder.sceneManager;
	}

	public void initialize() {

		navigation = NavigationBuilder.buildNavigation(SceneHolder.STEPPIZZA, sceneManager);
		center = createCenter();
		root = new VBox(10, navigation, center);
		scene = new Scene(root, sceneManager.getAppData().getWidth(), sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");

	}

	public void update() {
		root = new VBox(10, navigation, center);
		scene = new Scene(root, sceneManager.getAppData().getWidth(), sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
	}

	private VBox createCenter() {
		try {
			List<Pizza> pizzaList = DAOStaticData.getAll();
			
			VBox pizzaBox = new VBox(5);
			Image image = new Image("images/pizza.png");
			ImageView imageView = new ImageView();
			imageView.setFitWidth(sceneManager.getListSize().getColumnOne());
			imageView.setImage(image);
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
				databox.getChildren().add(buildRow(image,pizza,index));
				index++;
			}
			ScrollPane sp = new ScrollPane(databox);

			pizzaBox.getChildren().add(sp);

			Button personalDataButton = new Button(Strings.NOEXTRAS.getValue());
			personalDataButton.onMouseClickedProperty().addListener((e)-> {
				sceneManager.setScene(SceneHolder.STEPPERSONALDATA);
			});
			personalDataButton.getStyleClass().add(StyleClassNames.BESTELLBUTTON_CENTER.getValue());
			
			Button forwardButton = new Button(Strings.FORWARD.getValue());
			forwardButton.onMouseClickedProperty().addListener((e)-> {
				sceneManager.setScene(SceneHolder.STEPEXTRAS);
			});
			forwardButton.getStyleClass().add(StyleClassNames.BESTELLBUTTON_CENTER.getValue());

			BorderPane pricePane = new BorderPane();
			setHeadLabelStyles(sceneManager.getListSize().getColumnFour(), pizzaLabel);
			priceCalculateLabel = new Label(decimalFormat.format(sceneManager.getOrderData().getPrice())+" CHF");
			
			priceCalculateLabel.setPrefWidth(sceneManager.getListSize().getColumnFive());
			pricePane.setRight(priceCalculateLabel);
			
			BorderPane buttonPane = new BorderPane();
			buttonPane.setRight(new HBox(5,personalDataButton,forwardButton));
			
			VBox centerBox = new VBox(10,pizzaBox,pricePane, buttonPane);
			centerBox.setPrefWidth(sceneManager.getWidth());
			centerBox.getStyleClass().add(StyleClassNames.CENTERBOX.getValue());
			return centerBox;
		} catch (Exception e) {
			// TODO ADD Bog with Text and Error message
			System.out.println(e);
			return null;
		}
	}

	private HBox buildRow(Image image,Pizza pizza,int index) {
		
		ImageView imageView = new ImageView();
		imageView.setFitWidth(sceneManager.getListSize().getColumnOne());
		imageView.setImage(image);
		
		Label pizzaLabel = new Label(pizza.getName());
		setColLabelStyles(sceneManager.getListSize().getColumnTwo(), pizzaLabel);
		
		Label descriptionLabel = new Label(pizza.getDescription());
		setColLabelStyles(sceneManager.getListSize().getColumnThree(), descriptionLabel);

		Label priceLabel = new Label(decimalFormat.format(pizza.getPrice()));
		setColLabelStyles(sceneManager.getListSize().getColumnFour(), priceLabel);

		TextField anzahlField = new TextField();
		anzahlField.setPrefWidth(sceneManager.getListSize().getColumnFive());
		anzahlField.setText(String.valueOf(OrderCalculator.getCountForPizza(sceneManager, pizza)));
		anzahlField.setId(String.valueOf(pizza.getId()));
		anzahlField.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				try{
					int val = Integer.valueOf(newValue);
					OrderCalculator.calculate(sceneManager, pizza, val);
					priceCalculateLabel.setText(decimalFormat.format(sceneManager.getOrderData().getPrice()));
				}catch(Exception e){
					anzahlField.setText("");
				}
			}
		});
		anzahlField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(!newValue){
				if(anzahlField.getText().equals("")){
					anzahlField.setText("0");
					OrderCalculator.calculate(sceneManager, pizza, 0);
					priceCalculateLabel.setText(decimalFormat.format(sceneManager.getOrderData().getPrice()));
				}
			}
		});
		
		HBox row = new HBox(20, imageView, pizzaLabel, descriptionLabel, priceLabel, anzahlField);
		row.setAlignment(Pos.CENTER_LEFT);
		if(index % 2 == 0){
			row.getStyleClass().add(StyleClassNames.LISTEVEN.getValue());
		}
		return row;
	}

	private void setColLabelStyles(double width, Label label) {
		label.setAlignment(Pos.CENTER);
		label.setPrefWidth(width);
		label.getStyleClass().add(StyleClassNames.LISTCOLSTYLE.getValue());
	}
	
	private void setHeadLabelStyles(double width, Label label) {
		label.setAlignment(Pos.CENTER);
		label.setPrefWidth(width);
		label.getStyleClass().add(StyleClassNames.LISTHEADSTYLE.getValue());
	}

	public static class Builder {
		private SceneManager sceneManager;

		public Builder() {
		}

		public Builder giveSceneManager(SceneManager sceneManager) {
			this.sceneManager = sceneManager;
			return this;
		}

		public OrderStepPizzaScene build() {
			return new OrderStepPizzaScene(this);
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
	public void setNavigation(Pane navigation) {
		this.navigation = navigation;
	}

}
