package application;

public class ApplicationData {
	private double width;
	private double height;
	private String userName;
	private boolean loggedIn = false;
	private String currentScene;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String getCurrentScene() {
		return currentScene;
	}
	public void setCurrentScene(String currentScene) {
		this.currentScene = currentScene;
	}
	
}
