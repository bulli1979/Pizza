package application;

public enum StyleClassNames {
	HEADLINE("headline"),
	NAVIGATION("navigation"), 
	LOGINTEXT("logintext");
	private final String value;
	
	private StyleClassNames(final String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
