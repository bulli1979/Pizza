package scenes;

import elements.NavigationBuilder;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scenemanagement.SceneManager;

public class HomeScene implements PizzaScene{
	private Pane root;
	private SceneManager sceneManager;
	private Scene scene;
	private HBox navigation;
	private VBox center;
	public HomeScene(){}
	private HomeScene(Builder builder){
		this.sceneManager = builder.sceneManager;
	}
	
	
	public void initialize(){
				
		navigation = NavigationBuilder.buildNavigation("home",sceneManager);
		
		Text text = new Text("blablablub");
		center = new VBox(10,text);
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
		
		
	}
	
	public void update(){	
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
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
	@Override
	public HBox getNavigation() {
		return navigation;
	}
	
	@Override
	public void setNavigation(HBox navigation) {		
		this.navigation = navigation;
	}
	
	
}
