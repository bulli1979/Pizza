package elements;

import application.ScreenNames;
import application.Strings;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class NavigationBuilder {
	private static final String HEADLINE  = "headline";
	private static final String NAVIGATION  = "navigation";

	public static HBox buildNavigation(String screen){
		HBox navigation = null;
		Text headline = new Text(Strings.HEADLINE.getValue());
		headline.getStyleClass().add(HEADLINE);
		if(screen.equals(ScreenNames.HOME.getValue())){
			navigation = new HBox(15,headline);
		}
		navigation.getStyleClass().add(NAVIGATION);
		return navigation;
	}
}
