package scenes;

import java.text.DecimalFormat;

import application.Strings;
import elements.NavigationBuilder;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pojos.Extra;
import pojos.OrderData;
import pojos.Pizza;
import scenemanagement.SceneHolder;

public class OrderStepOverview extends OrderStepScene implements PizzaScene{

	private VBox createCenter(){
		VBox centerBox = new VBox();

		System.out.print(sceneManager.getOrderData().getPizzas().size());
		ScrollPane scroll = new ScrollPane();

		VBox box = new VBox(15);
		box.getChildren().addAll(getPizzaOverview(), getPersonalDataOverview(), getTotal());
		scroll.setContent(box);

		centerBox.getChildren().add(scroll);
		return centerBox;
	}

	private Node getPersonalDataOverview() {
		OrderData orderData = sceneManager.getOrderData();

		VBox box = new VBox(15);

		Label title = new Label();
		title.setFont(Font.font ("Verdana", 32));
		title.setText(Strings.PERSONAL_DATA.getValue());

		Label personDetails = new Label();
		personDetails.setText(personDetails.getText() + orderData.getFirstName() + " " + orderData.getName() + "\r\n");
		personDetails.setText(personDetails.getText() + orderData.getStreet() + "\r\n");
		personDetails.setText(personDetails.getText() + orderData.getZip() + " " + orderData.getCity() + "\r\n");
		personDetails.setText(personDetails.getText() + orderData.getTelephone() + "\r\n");

		box.getChildren().addAll(title, personDetails);
		return box;
	}

	private VBox getPizzaOverview() {
		VBox box = new VBox(15);

		Label title = new Label();
		title.setFont(Font.font ("Verdana", 32));
		title.setText(Strings.PIZZALABEL.getValue());

		Label pizzaDetails = new Label();

		for (Pizza pizza : sceneManager.getOrderData().getPizzas()) 
		{
			pizzaDetails.setText(pizzaDetails.getText() + "🍕  PIZZA " + pizza.getName() + "\r\n");

			for (Extra extra : pizza.getExtras())
			{
				pizzaDetails.setText(pizzaDetails.getText() + "• " + extra.getDescription() + "\r\n");
			}
		}

		if (pizzaDetails.getText() == "")
		{
			pizzaDetails.setText("Keine Pizza ausgewählt.");
		}

		box.getChildren().addAll(title, pizzaDetails);

		return box;
	}

	private Node getTotal() {
		VBox box = new VBox(15);

		Label title = new Label();
		title.setFont(Font.font ("Verdana", 32));
		title.setText("Total");

		Label total = new Label();
		DecimalFormat df = new DecimalFormat("#.00"); 
		total.setText("CHF " + df.format(sceneManager.getOrderData().getPrice()));

		box.getChildren().addAll(title, total);
		return box;
	}

	@Override
	public void initialize(){

		navigation = NavigationBuilder.buildNavigation(SceneHolder.HOME,sceneManager);
		center = createCenter();
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");

	}

	@Override
	public void update(){	
		navigation = NavigationBuilder.buildNavigation(SceneHolder.HOME,sceneManager);
		center = createCenter();
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
	}
}
