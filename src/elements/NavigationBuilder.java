package elements;
import application.ScreenNames;
import application.Strings;
import application.StyleClassNames;
import events.LoginHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scenemanagement.SceneManager;

public class NavigationBuilder {
	

	public static HBox buildNavigation(ScreenNames screen,SceneManager sceneManager){
		HBox navigation = null;
		switch(screen){
		case HOME :
			navigation = getHomeNavigation(sceneManager);
			break;
		case STEPPIZZA : 
			navigation = getOrderPizzaNavigation(sceneManager);
			break;
		default : System.out.println("mist");
		}
		navigation.getStyleClass().add(StyleClassNames.NAVIGATION.getValue());
		return navigation;
	}
	
	private static HBox getHomeNavigation(SceneManager sceneManager){
		Text headline = new Text(Strings.HEADLINE.getValue());
		VBox userBox = getLoginBox(sceneManager);
		headline.getStyleClass().add(StyleClassNames.HEADLINE.getValue());
		BorderPane pane = new BorderPane();
		pane.setPrefWidth(sceneManager.getWidth()-20);
		pane.setCenter(headline);
		pane.setRight(userBox);
		return new HBox(15,pane);
	}
	
	private static HBox getOrderPizzaNavigation(SceneManager sceneManager){
		Text headline = new Text(Strings.HEADLINE.getValue());
		VBox userBox = getLoginBox(sceneManager);
		headline.getStyleClass().add(StyleClassNames.HEADLINE.getValue());
		BorderPane pane = new BorderPane();
		pane.setPrefWidth(sceneManager.getWidth()-20);
		pane.setCenter(headline);
		pane.setRight(userBox);
		return new HBox(15,pane);
	}
	
	private static VBox getLoginBox(SceneManager sceneManager){
		if(sceneManager.getAppData().isLoggedIn()){
			Text loginText = new Text(Strings.LOGIN.getValue() + " " + sceneManager.getAppData().getUserName());
			loginText.getStyleClass().add(StyleClassNames.LOGINTEXT.getValue());
			return new VBox(10,loginText);
		}else{
			TextField userField = new TextField();
			userField.setPromptText(Strings.USERNAME.getValue());
			PasswordField passwordField = new PasswordField();
			passwordField.setPromptText(Strings.PASSWORD.getValue());
			Button loginButton = new Button();
			LoginHandler loginHandler = new LoginHandler.Builder().setSceneManager(sceneManager).build();
			loginButton.setOnAction(loginHandler);
			loginButton.setText(Strings.LOGINBUTTONTEXT.getValue());
			return new VBox(10,userField,passwordField,loginButton);
		}
		
	}
	
	
}
