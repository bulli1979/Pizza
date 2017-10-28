package application;

public enum Strings {
	HEADLINE("Pizza Bestellservice Ruch Eberlein"), 
	LOGIN("Eingeloggt als"), 
	LOGINBUTTONTEXT("einloggen"), 
	USERNAME("Benutzername"), 
	PASSWORD("Passwort"),
	LONG_HOME_TEXT("Willkommen bei unseren Pizza Service. Geniessen sie das Leben mit einer Pizza und guten Wein.\n Ab einem Bestellwert von CH30 bekommen sie eine Flasche feinsten Rotwein von uns geschenkt."), 
	BESTELLEN("jetzt Bestellen");
	private final String value;
	
	private Strings(final String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
}
