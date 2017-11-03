package application;

public enum Strings {
	HEADLINE("Pizza Bestellservice Ruch Eberlein"), 
	LOGIN("Eingeloggt als"), 
	LOGINBUTTONTEXT("einloggen"), 
	USERNAME("Benutzername"), 
	PASSWORD("Passwort"),
	LONG_HOME_TEXT("Willkommen bei unseren Pizza Service. Geniessen sie das Leben mit einer Pizza und guten Wein.\n Ab einem Bestellwert von CH30 bekommen sie eine Flasche feinsten Rotwein von uns geschenkt."), 
	BESTELLEN("jetzt Bestellen"), 
	PIZZALABEL("Pizza"), 
	DESCRIPTIONLABEL("Beschreibung"), 
	ANZAHLLABEL("Anzahl"), 
	PRICELABEL("Preis"), 
	FORWARD("Weiter"), 
	HOME("HOME"), 
	PIZZASELECT("Pizzas w�hlen"), 
	EXTRASELECT("Extras hinzuf�gen"), 
	PERSONAL_DATA("Bestellangaben"), 
	OVERVIEW("Abschluss"), 
	NOEXTRAS("ohne Extras weiter"), 
	ERROR("Es ist ein Fehler aufgetreten. Bitte kontaktieren sie den Administrator."), 
	DELETE("l�schen"),
	DELIVERY("Lieferung"),
	DELIVERYTIME("Lieferzeit in Minuten"),
	PAYMENT("Payment"),
	CASH_PAYMENT_AT_DELIVERY("Bar / mit Karte bei Lieferung"),
	NAME("Name"),
	FIRST_NAME("Vorname"),
	COMPANY("Firma"),
	E_MAIL("E-Mail"),
	TELEFON("Telefon"),
	MOBILE_PHONE("Mobiltelefon"),
	ADDRESS("Strasse"),
	ZIP_CODE("PLZ"),
	CITY("Ort"),
	SALUTATION("Anrede"),
	MR("Herr"),
	MRS("Frau"), 
	ADD_EXTRA("Extras hinzuf�gen"), EXTRALABEL("Extra"), FINISH("Extras �bernehmen"), THANK_YOU("Vielen Dank f�r Ihre Bestellung!"), VERDANA("verdana"), NOPIZZA("Keine Pizza ausgew�hlt."), TOTAL("Total:"), 
	TIMELABEL("Ihre vorrausichtliche Lieferzeit: "), LINEBREAK("\r\n");

	private final String value;

	private Strings(final String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

}
