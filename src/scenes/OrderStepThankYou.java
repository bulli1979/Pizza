package scenes;

import application.Strings;
import application.StyleClassNames;
import elements.NavigationBuilder;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scenemanagement.SceneHolder;

public class OrderStepThankYou extends OrderStepScene implements PizzaScene{

	private VBox createCenter(){
		BorderPane textPane = new BorderPane();
		Text text = new Text(Strings.THANK_YOU.getValue());
		text.getStyleClass().add(StyleClassNames.CENTERTEXT.getValue());
		textPane.setCenter(text);
		BorderPane buttonPane = new BorderPane();
		VBox centerBox = new VBox(10,textPane,buttonPane);
		centerBox.setPrefWidth(sceneManager.getWidth());
		centerBox.getStyleClass().add(StyleClassNames.CENTERBOX.getValue());
		return centerBox;
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
		root = new VBox(10,navigation,center);
		scene = new Scene(root,sceneManager.getAppData().getWidth(),sceneManager.getAppData().getHeight());
		scene.getStylesheets().add("application/application.css");
	}
}
