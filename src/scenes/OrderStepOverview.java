package scenes;

import application.Strings;
import application.StyleClassNames;
import elements.NavigationBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scenemanagement.SceneHolder;

public class OrderStepOverview extends OrderStepScene implements PizzaScene{
	
	public void initialize(){
				
		navigation = NavigationBuilder.buildNavigation(SceneHolder.HOME,sceneManager);
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
}
