package scenes;

import java.util.List;

import application.ScreenNames;
import application.Strings;
import application.StyleClassNames;
import data.DAOStaticData;
import elements.NavigationBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import order.Pizza;
import scenemanagement.SceneManager;

public class OrderStepPizzaScene implements PizzaScene{
	private Pane root;
	private SceneManager sceneManager;
	private Scene scene;
	private HBox navigation;
	private Pane center;
	public OrderStepPizzaScene(){}
	private OrderStepPizzaScene(Builder builder){
		this.sceneManager = builder.sceneManager;
	}
	
	public void initialize(){
				
		navigation = NavigationBuilder.buildNavigation(ScreenNames.STEPPIZZA,sceneManager);
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
		try{
		List<Pizza> pizzaList = DAOStaticData.getAll();
		VBox pizzaBox = new VBox(5);
		Image image = new Image("images/pizza.png");
		ImageView iv1 = new ImageView();
        iv1.setImage(image);

		Text pizzaLabel = new Text(Strings.PIZZALABEL.getValue());
		Text descriptionLabel = new Text(Strings.DESCRIPTIONLABEL.getValue());
		Text anzahlLabel = new Text(Strings.ANZAHLLABEL.getValue());
		HBox headRow = new HBox(20,iv1,pizzaLabel,descriptionLabel,anzahlLabel);
		pizzaBox.getChildren().add(headRow);
		Button bestellButtonCenter = new Button(Strings.BESTELLEN.getValue());
		BestellHandler bestellHandler = new BestellHandler.Builder().setSceneManager(sceneManager).build();
		bestellButtonCenter.setOnAction(bestellHandler);
		bestellButtonCenter.getStyleClass().add(StyleClassNames.BESTELLBUTTON_CENTER.getValue());
		BorderPane buttonPane = new BorderPane();
		buttonPane.setRight(bestellButtonCenter);
		VBox centerBox = new VBox(10,pizzaBox,buttonPane);
		centerBox.setPrefWidth(sceneManager.getWidth());
		centerBox.getStyleClass().add(StyleClassNames.CENTERBOX.getValue());
		return centerBox;
		}catch(Exception e){
			//TODO ADD Bog with Text and Error message
			return null;
		}
	}
	
	public static class Builder{
		private SceneManager sceneManager;
		public Builder(){}
		public Builder giveSceneManager(SceneManager sceneManager) {
			this.sceneManager = sceneManager;
			return this;
		}
		public OrderStepPizzaScene build(){
			return new OrderStepPizzaScene(this);
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
