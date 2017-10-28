package application;

public enum Strings {
	HEADLINE("Pizza Bestellservice Ruch Eberlein"), 
	LOGIN("Eingeloggt als"), 
	LOGINBUTTONTEXT("einloggen"), USERNAME("Benutzername"), PASSWORD("Passwort");
	private final String value;
	
	private Strings(final String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
}
