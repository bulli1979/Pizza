package scenes;

import application.Main;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import order.OrderData;
import scenes.HomeScene.Builder;

public class HomeScene implements PizzaScene{
	
	private double width;
	private double height;
	private BorderPane root;
	private OrderData orderData;
	private Main main;
	private Scene scene;
	public HomeScene(){}
	private HomeScene(Builder builder){
		this.width = builder.width;
		this.height = builder.height;
		this.root = builder.root;
		this.orderData = builder.orderData;
		this.main = builder.main;
	}
	
	public void initialize(){
		scene = new Scene(root,width,height);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		//TODO Coolstuff here
		
	}
	
	public void update(){
		
	}
	
	
	public static class Builder{
		private double width;
		private double height;
		private BorderPane root;
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
		
		public Builder setRoot(BorderPane root){
			this.root = root;
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
