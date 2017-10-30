package scenemanagement;

import application.ApplicationData;
import application.Main;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pojos.ListSize;
import pojos.OrderData;

public class SceneManager {
	private Stage primaryStage;
	private double width;
	private double height;
	private Main application;
	private OrderData orderData;
	private ApplicationData appData;
	private ListSize listSize;
	public SceneManager(Main application, Stage primaryStage, OrderData orderData){
		this.application = application;
		this.orderData = orderData;
		this.primaryStage = primaryStage;
		this.appData = new ApplicationData();
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		width = visualBounds.getWidth();
		listSize = ScreenSizeCalculator.getScreenSizeForList(width);
		height = visualBounds.getHeight();
		appData.setHeight(height);
		appData.setWidth(width);
		appData.setCurrentScene(SceneHolder.HOME);
	}
	
	public void initScenes() {
		SceneHolder.HOME.initialize(this);
		SceneHolder.STEPPIZZA.initialize(this);
		SceneHolder.STEPEXTRAS.initialize(this);
		SceneHolder.STEPEXTRACHANGE.initialize(this);
		SceneHolder.STEPPERSONALDATA.initialize(this);
		SceneHolder.STEPOVERVIEW.initialize(this);
		SceneHolder.STEPTHANKYOU.initialize(this);
	}

	
	
	public void setScene(SceneHolder screen){
		appData.setCurrentScene(screen);
		primaryStage.setScene(screen.getPizzaScene().getScene());
		primaryStage.show();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Main getApplication() {
		return application;
	}

	public void setApplication(Main application) {
		this.application = application;
	}

	public OrderData getOrderData() {
		return orderData;
	}

	public void setOrderData(OrderData orderData) {
		this.orderData = orderData;
	}

	public ApplicationData getAppData() {
		return appData;
	}

	public void setAppData(ApplicationData appData) {
		this.appData = appData;
	}
	public ListSize getListSize() {
		return listSize;
	}
}
