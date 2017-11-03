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
import pojos.Extra;
import scenemanagement.OrderCalculator;
import scenemanagement.SceneHolder;

public class OrderStepSelectExtra extends OrderStepScene implements PizzaScene{
	private List<Extra> extraList;
	public void initialize(){
				
		navigation = NavigationBuilder.buildNavigation(SceneHolder.HOME,sceneManager);
		center = createCenter();
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
		
	}
	
	public void update(){	
		
		navigation = NavigationBuilder.buildNavigation(SceneHolder.HOME,sceneManager);
		center = createCenter();
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
	}
	
	private Pane createCenter() {
		try {
			if(extraList==null){
				extraList = DAOStaticData.getAllExtras();
			}
			VBox extraBox = new VBox(5);
			
			Image image = new Image("images/kaese.png");
			ImageView imageView = createImage(image);
			
			Label extraLabel = new Label(Strings.EXTRALABEL.getValue());
			setHeadLabelStyles(sceneManager.getListSize().getColumnTwo(), extraLabel);

			Label descriptionLabel = new Label(Strings.DESCRIPTIONLABEL.getValue());
			setHeadLabelStyles(sceneManager.getListSize().getColumnThree(), descriptionLabel);

			Label priceLabel = new Label(Strings.PRICELABEL.getValue());
			setHeadLabelStyles(sceneManager.getListSize().getColumnFour(), priceLabel);

			Label anzahlLabel = new Label(Strings.ANZAHLLABEL.getValue());
			setHeadLabelStyles(sceneManager.getListSize().getColumnFive(), anzahlLabel);

			HBox headRow = new HBox(20, imageView, extraLabel, descriptionLabel, priceLabel, anzahlLabel);
			headRow.setAlignment(Pos.CENTER_LEFT);
			headRow.getStyleClass().add(StyleClassNames.LISTEVEN.getValue());
			extraBox.getChildren().add(headRow);
			priceCalculateLabel = new Label(decimalFormat.format(sceneManager.getOrderData().getPrice()));
			priceCalculateLabel.setPrefWidth(sceneManager.getListSize().getColumnFive());
			
			VBox databox = new VBox(5);
			int index = 1;
			for (Extra extra : extraList) {
				databox.getChildren().add(buildRow(image, extra, index));
				index++;
			}
			ScrollPane sp = new ScrollPane(databox);
			extraBox.getChildren().add(sp);

			Button forwardButton = new Button(Strings.FINISH.getValue());
			forwardButton.setOnAction(event -> {
				SceneHolder.STEPEXTRAS.getPizzaScene().update();
				sceneManager.getOrderData().setSelectExtraPizza(null);
				SceneHolder.STEPEXTRAS.getPizzaScene().update();
				sceneManager.setScene(SceneHolder.STEPEXTRAS);
			});
			forwardButton.getStyleClass().add(StyleClassNames.BESTELLBUTTON_CENTER.getValue());

			BorderPane pricePane = new BorderPane();
			pricePane.setPrefWidth(sceneManager.getListSize().getColumnFour());
			
			pricePane.setRight(priceCalculateLabel);

			BorderPane buttonPane = new BorderPane();
			buttonPane.setRight(new HBox(5, forwardButton));

			VBox centerBox = new VBox(10, extraBox, pricePane, buttonPane);
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

		HBox anzahlBox = new HBox(5);
		anzahlBox.setPrefWidth(sceneManager.getListSize().getColumnFive());
		TextField anzahlField = createAnzahlField(extra);
		VBox arrows = createArrows(extra, anzahlField);
		anzahlBox.getChildren().addAll(anzahlField,arrows);
		anzahlBox.setAlignment(Pos.CENTER_LEFT);
		
		HBox row = new HBox(20, imageView, pizzaLabel, descriptionLabel, priceLabel, anzahlBox);
		row.setAlignment(Pos.CENTER_LEFT);
		if (index % 2 == 0) {
			row.getStyleClass().add(StyleClassNames.LISTEVEN.getValue());
		}
		return row;
	}
	private TextField createAnzahlField(Extra extra) {
		TextField anzahlField = new TextField();
		anzahlField.setText(String.valueOf(OrderCalculator.getCountForExtra(sceneManager, extra)));
		anzahlField.setId(String.valueOf(extra.getId()));
		anzahlField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				try {
					int val = Integer.parseInt(newValue);
					OrderCalculator.calculateExtra(sceneManager, extra, val);
					priceCalculateLabel.setText(decimalFormat.format(sceneManager.getOrderData().getPrice()));
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
			}
		});
		return anzahlField;
	}
	
	private VBox createArrows(Extra extra, TextField anzahlField) {
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
			OrderCalculator.calculateExtra(sceneManager, extra, anzahl);
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
				OrderCalculator.calculateExtra(sceneManager, extra, anzahl);
				priceCalculateLabel.setText(decimalFormat.format(sceneManager.getOrderData().getPrice()));
			}
		});
		arrows.getChildren().addAll(upHolder,downHolder);
		return arrows;
	}
}
