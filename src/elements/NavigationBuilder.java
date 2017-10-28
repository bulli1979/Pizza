package elements;

import application.ScreenNames;
import application.Strings;
import application.StyleClassNames;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class NavigationBuilder {
	

	public static HBox buildNavigation(String screen,double applicationWidth){
		HBox navigation = null;
		
		if(screen.equals(ScreenNames.HOME.getValue())){
			navigation = getHomeNavigation(applicationWidth);
		}
		navigation.getStyleClass().add(StyleClassNames.NAVIGATION.getValue());
		return navigation;
	}
	
	private static HBox getHomeNavigation(double applicationWidth){
		Text headline = new Text(Strings.HEADLINE.getValue());
		Text loginText = new Text(Strings.LOGIN.getValue());
		loginText.getStyleClass().add(StyleClassNames.LOGINTEXT.getValue());
		headline.getStyleClass().add(StyleClassNames.HEADLINE.getValue());
		BorderPane pane = new BorderPane();
		pane.setMinWidth(applicationWidth);
		pane.setCenter(headline);
		pane.setRight(loginText);
		return new HBox(15,pane);
	}
}
