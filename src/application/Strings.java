package application;

public enum Strings {
	HEADLINE("Pizza Bestellservice Ruch Eberlein");
	private final String value;
	
	private Strings(final String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
}
