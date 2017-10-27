package scenes;

import application.Main;
import elements.NavigationBuilder;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import order.OrderData;

public class HomeScene implements PizzaScene{
	
	private double width;
	private double height;
	private Pane root;
	private OrderData orderData;
	private Main main;
	private Scene scene;
	public HomeScene(){}
	private HomeScene(Builder builder){
		this.width = builder.width;
		this.height = builder.height;
		this.orderData = builder.orderData;
		this.main = builder.main;
	}
	
	
	public void initialize(){
		
		//TODO own Class
		
		HBox navigation = NavigationBuilder.buildNavigation("home");
		
		Text text = new Text("blablablub");
		VBox content = new VBox(10,navigation,text);
		root = content;
		scene = new Scene(root,width,height);
		scene.getStylesheets().add("application/application.css");
		
		
	}
	
	public void update(){
		
	}
	
	
	public static class Builder{
		private double width;
		private double height;
		private OrderData orderData;
		private Main main;
		public Builder(){}
		public Builder withWidth(double d){
			this.width = d;
			return this;
		}
		public Builder withHeight(double height){
			this.height = height;
			return this;
		}
		
		
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
		
	}


	@Override
	public Scene getScene() {
		return scene;
	}
}
