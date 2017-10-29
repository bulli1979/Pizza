package scenes;

import application.ScreenNames;
import application.Strings;
import application.StyleClassNames;
import elements.NavigationBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scenemanagement.SceneManager;

public class OrderStepSelectExtra implements PizzaScene{
	private Pane root;
	private SceneManager sceneManager;
	private Scene scene;
	private HBox navigation;
	private Pane center;
	public OrderStepSelectExtra(){}
	private OrderStepSelectExtra(Builder builder){
		this.sceneManager = builder.sceneManager;
	}
	
	public void initialize(){
				
		navigation = NavigationBuilder.buildNavigation(ScreenNames.HOME,sceneManager);
		center = createCenter();
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
		
	}
	
	public void update(){	
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
	}
	
	private VBox createCenter(){
		BorderPane textPane = new BorderPane();
		Text text = new Text(Strings.LONG_HOME_TEXT.getValue());
		text.getStyleClass().add(StyleClassNames.CENTERTEXT.getValue());
		textPane.setCenter(text);
		Button bestellButtonCenter = new Button(Strings.BESTELLEN.getValue());
		BestellHandler bestellHandler = new BestellHandler.Builder().setSceneManager(sceneManager).build();
		bestellButtonCenter.setOnAction(bestellHandler);
		bestellButtonCenter.getStyleClass().add(StyleClassNames.BESTELLBUTTON_CENTER.getValue());
		BorderPane buttonPane = new BorderPane();
		buttonPane.setRight(bestellButtonCenter);
		VBox centerBox = new VBox(10,textPane,buttonPane);
		centerBox.setPrefWidth(sceneManager.getWidth());
		centerBox.getStyleClass().add(StyleClassNames.CENTERBOX.getValue());
		return centerBox;
	}
	
	public static class Builder{
		private SceneManager sceneManager;
		public Builder(){}
		public Builder giveSceneManager(SceneManager sceneManager) {
			this.sceneManager = sceneManager;
			return this;
		}
		public OrderStepSelectExtra build(){
			return new OrderStepSelectExtra(this);
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
