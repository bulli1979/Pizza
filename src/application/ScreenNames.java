package application;

public enum ScreenNames {
	HOME("home"),
	STEPONE("step1")
	;
	
	private final String value;
	private ScreenNames(final String value){
		this.value = value;
	}
	
    public String getValue() {
        return value;
    } 
}
