package scenes;

import application.ApplicationData;
import application.Main;
import elements.NavigationBuilder;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import order.OrderData;

public class HomeScene implements PizzaScene{
	private Pane root;
	private OrderData orderData;
	private Main main;
	private Scene scene;
	private ApplicationData appData;
	public HomeScene(){}
	private HomeScene(Builder builder){
		this.appData = builder.appData;
		this.orderData = builder.orderData;
		this.main = builder.main;
	}
	
	
	public void initialize(){
		
		//TODO own Class
		
		HBox navigation = NavigationBuilder.buildNavigation("home",appData.getWidth());
		
		Text text = new Text("blablablub");
		VBox content = new VBox(10,navigation,text);
		root = content;
		scene = new Scene(root,appData.getWidth(),appData.getHeight());
		scene.getStylesheets().add("application/application.css");
		
		
	}
	
	public void update(){
		
	}
	
	
	public static class Builder{
		private ApplicationData appData;
		private OrderData orderData;
		private Main main;
		public Builder(){}
		public Builder data(OrderData orderData) {
			this.orderData = orderData;
			return this;
		}
		public HomeScene build(){
			return new HomeScene(this);
		}
		public Builder application(Main main) {
			this.main = main;
			return this;
		}
		public Builder withAppData(ApplicationData appData) {
			this.appData = appData;
			return this;
		}
		
	}


	@Override
	public Scene getScene() {
		return scene;
	}
}
