package scenemanagement;

import pojos.ListSize;

public class ScreenSizeCalculator {
	
	public static ListSize getScreenSizeForList(double width){
		ListSize screenSize = new ListSize();
		double realsize = width-170;
		double onePart = realsize/10;
		screenSize.setColumnOne(onePart);
		screenSize.setColumnTwo(onePart*3);
		screenSize.setColumnThree(onePart*4);
		screenSize.setColumnFour(onePart);
		screenSize.setColumnFive(onePart);
		return screenSize;
	}
}
