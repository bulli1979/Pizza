package elements;
import application.Strings;
import application.StyleClassNames;
import events.LoginHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scenemanagement.SceneHolder;
import scenemanagement.SceneManager;

public class NavigationBuilder {
	private NavigationBuilder(){}
	public static Pane buildNavigation(SceneHolder screen, SceneManager sceneManager) {
		Pane navigation = null;
		switch (screen) {
		case HOME:
			navigation = buildTop(sceneManager);
			break;
		case STEPPIZZA:
			navigation = getOrderPizzaNavigation(sceneManager,1);
			break;
		case STEPEXTRAS:
			navigation = getOrderPizzaNavigation(sceneManager,2);
			break;
		case STEPPERSONALDATA:
			navigation = getOrderPizzaNavigation(sceneManager,3);
			break;
		case STEPOVERVIEW:
			navigation = getOrderPizzaNavigation(sceneManager,4);
			break;
		default:
			System.out.println("no View found");
		}
		navigation.getStyleClass().add(StyleClassNames.NAVIGATION.getValue());
		return navigation;
	}

	private static VBox getOrderPizzaNavigation(SceneManager sceneManager,int step) {
		return new VBox(0, buildTop(sceneManager), buildOrderView(step, sceneManager));
	}

	private static HBox buildTop(SceneManager sceneManager) {
		Text headline = new Text(Strings.HEADLINE.getValue());
		VBox userBox = getLoginBox(sceneManager);
		headline.getStyleClass().add(StyleClassNames.HEADLINE.getValue());
		BorderPane pane = new BorderPane();
		pane.setPrefWidth(sceneManager.getWidth() - 20);
		pane.setCenter(headline);
		pane.setRight(userBox);
		return new HBox(15, pane);
	}

	private static VBox getLoginBox(SceneManager sceneManager) {
		if (sceneManager.getAppData().isLoggedIn()) {
			Text loginText = new Text(Strings.LOGIN.getValue() + " " + sceneManager.getAppData().getUserName());
			loginText.getStyleClass().add(StyleClassNames.LOGINTEXT.getValue());
			return new VBox(10, loginText);
		} else {
			TextField userField = new TextField();
			userField.setPromptText(Strings.USERNAME.getValue());
			PasswordField passwordField = new PasswordField();
			passwordField.setPromptText(Strings.PASSWORD.getValue());
			Button loginButton = new Button();
			LoginHandler loginHandler = new LoginHandler.Builder().setSceneManager(sceneManager).build();
			loginButton.setOnAction(loginHandler);
			loginButton.setText(Strings.LOGINBUTTONTEXT.getValue());
			return new VBox(10, userField, passwordField, loginButton);
		}

	}

	private static HBox buildOrderView(int step, SceneManager sceneManager) {
		HBox orderNavigator = new HBox();
		Button homeButton = new Button(Strings.HOME.getValue());
		homeButton.setOnAction((e) -> {
			sceneManager.setScene(SceneHolder.HOME);
		});
		homeButton.getStyleClass().add(StyleClassNames.ACTIVE.getValue());

		Button orderPizza = new Button(Strings.PIZZASELECT.getValue());
		if (step > 1) {
			orderPizza.setOnAction((e) -> {
				sceneManager.setScene(SceneHolder.STEPPIZZA);
			});
			orderPizza.getStyleClass().add(StyleClassNames.ACTIVE.getValue());
		} else {
			orderPizza.getStyleClass().add(StyleClassNames.INACTIVE.getValue());
		}

		Button orderExtras = new Button(Strings.EXTRASELECT.getValue());
		if (step > 2) {
			orderExtras.setOnAction((e) -> {
				sceneManager.setScene(SceneHolder.HOME);
			});
			orderExtras.getStyleClass().add(StyleClassNames.ACTIVE.getValue());
		} else {
			orderExtras.getStyleClass().add(StyleClassNames.INACTIVE.getValue());
		}

		Button orderPersonalData = new Button(Strings.PERSONALDATA.getValue());
		if (step >= 3) {
			orderPersonalData.setOnAction((e) -> {
				sceneManager.setScene(SceneHolder.HOME);
			});
			orderPersonalData.getStyleClass().add(StyleClassNames.ACTIVE.getValue());
		} else {
			orderPizza.getStyleClass().add(StyleClassNames.INACTIVE.getValue());
		}

		Button orderOverview = new Button(Strings.OVERVIEW.getValue());
		if (step >= 4) {
			orderOverview.getStyleClass().add(StyleClassNames.ACTIVE.getValue());
		} else {
			orderOverview.getStyleClass().add(StyleClassNames.INACTIVE.getValue());
		}
		orderNavigator.getChildren().addAll(homeButton, orderPizza, orderExtras, orderPersonalData, orderOverview);
		return orderNavigator;
	}

}
