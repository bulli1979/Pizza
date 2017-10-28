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
import scenemanagement.SceneManager;
import scenes.HomeScene.Builder;

public class HomeScene implements PizzaScene{
	private Pane root;
	private SceneManager sceneManager;
	private Scene scene;
	public HomeScene(){}
	private HomeScene(Builder builder){
		this.sceneManager = builder.sceneManager;
	}
	
	
	public void initialize(){
		
		
		HBox navigation = NavigationBuilder.buildNavigation("home",sceneManager);
		
		Text text = new Text("blablablub");
		VBox content = new VBox(10,navigation,text);
		root = content;
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
		
		
	}
	
	public void update(){
		
	}
	
	
	public static class Builder{
		private SceneManager sceneManager;
		public Builder(){}
		public Builder giveSceneManager(SceneManager sceneManager) {
			this.sceneManager = sceneManager;
			return this;
		}
		public HomeScene build(){
			return new HomeScene(this);
		}
		
	}


	@Override
	public Scene getScene() {
		return scene;
	}
}
