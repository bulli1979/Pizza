package scenemanagement;

import pojos.ListSize;

public class ScreenSizeCalculator {
	
	public static ListSize getScreenSizeForList(double width){
		ListSize screenSize = new ListSize();
		double realsize = width-170;
		double onePart = realsize/10;
		screenSize.setColumnOne(onePart);
		screenSize.setColumnTwo(onePart);
		screenSize.setColumnThree(onePart*5);
		screenSize.setColumnFour(onePart);
		screenSize.setColumnFive(onePart*2);
		return screenSize;
	}
}
