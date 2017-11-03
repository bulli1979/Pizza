package scenes;

import java.text.SimpleDateFormat;
import java.util.Date;

import application.Strings;
import elements.NavigationBuilder;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pojos.Extra;
import pojos.OrderData;
import pojos.Pizza;
import scenemanagement.SceneHolder;

public class OrderStepOverview extends OrderStepScene implements PizzaScene{

	private VBox createCenter(){
		VBox centerBox = new VBox();
		centerBox.setPadding(new Insets(20,20,20,20));

		ScrollPane scroll = new ScrollPane();

		VBox box = new VBox(15);
		box.setPadding(new Insets(20,20,20,20));
		box.getChildren().addAll(getPizzaOverview(), getPersonalDataOverview(), getTotal(), createTimePane());
		scroll.setContent(box);

		centerBox.getChildren().add(scroll);
		return centerBox;
	}
	private Pane createTimePane() {
		
		Label title = new Label();
		title.setFont(Font.font (Strings.VERDANA.getValue(), 32));
		title.setText(Strings.DELIVERY.getValue());
		
		String deliveryTimeString = sceneManager.getOrderData().getDeliveryTime();
		Text timeText = new Text("");
		if(deliveryTimeString != null){
			long delTime = Integer.parseInt(sceneManager.getOrderData().getDeliveryTime())*(long)60000;
			Date finalTime = new Date(delTime + (new Date().getTime()));
			SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			timeText.setText(Strings.TIMELABEL.getValue()+formater.format(finalTime));
		}
		return new VBox(15,title,timeText);
	}
	private Node getPersonalDataOverview() {
		OrderData orderData = sceneManager.getOrderData();

		VBox box = new VBox(15);
		Label title = new Label();
		title.setFont(Font.font (Strings.VERDANA.getValue(), 32));
		title.setText(Strings.PERSONAL_DATA.getValue());

		Label personDetails = new Label();
		personDetails.setText(personDetails.getText() + orderData.getFirstName() + " " + orderData.getName() + Strings.LINEBREAK.getValue());
		personDetails.setText(personDetails.getText() + orderData.getStreet() + Strings.LINEBREAK.getValue());
		personDetails.setText(personDetails.getText() + orderData.getZip() + " " + orderData.getCity() + Strings.LINEBREAK.getValue());
		personDetails.setText(personDetails.getText() + orderData.getTelephone() + Strings.LINEBREAK.getValue());

		box.getChildren().addAll(title, personDetails);
		return box;
	}

	private VBox getPizzaOverview() {
		VBox box = new VBox(15);

		Label title = new Label();
		title.setFont(Font.font (Strings.VERDANA.getValue(), 32));
		title.setText(Strings.PIZZALABEL.getValue());

		Label pizzaDetails = new Label();

		for (Pizza pizza : sceneManager.getOrderData().getPizzas()) 
		{
			pizzaDetails.setText(pizzaDetails.getText() + "🍕  PIZZA " + pizza.getName() + Strings.LINEBREAK.getValue());

			for (Extra extra : pizza.getExtras())
			{
				pizzaDetails.setText(pizzaDetails.getText() + "• " + extra.getDescription() + Strings.LINEBREAK.getValue());
			}
		}

		if (pizzaDetails.getText() == ""){
			pizzaDetails.setText(Strings.NOPIZZA.getValue());
		}

		box.getChildren().addAll(title, pizzaDetails);

		return box;
	}

	private Node getTotal() {
		VBox box = new VBox(15);

		Label title = new Label();
		title.setFont(Font.font (Strings.VERDANA.getValue(), 32));
		title.setText(Strings.TOTAL.getValue());

		Label total = new Label();
		total.setText(decimalFormat.format(sceneManager.getOrderData().getPrice())+ Strings.LINEBREAK.getValue());

		box.getChildren().addAll(title, total);
		return box;
	}

	@Override
	public void initialize(){

		navigation = NavigationBuilder.buildNavigation(SceneHolder.STEPOVERVIEW,sceneManager);
		center = createCenter();
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");

	}

	@Override
	public void update(){	
		navigation = NavigationBuilder.buildNavigation(SceneHolder.STEPOVERVIEW,sceneManager);
		center = createCenter();
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
	}
}
