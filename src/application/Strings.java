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
	PIZZASELECT("Pizzas wählen"), 
	EXTRASELECT("Extras hinzufügen"), 
	PERSONAL_DATA("Bestellangaben"), 
	OVERVIEW("Abschluss"), 
	NOEXTRAS("ohne Extras weiter"), 
	ERROR("Es ist ein Fehler aufgetreten. Bitte kontaktieren sie den Administrator."), 
	DELETE("löschen"),
	DELIVERY("Lieferung"),
	DELIVERYTIME("Lieferzeit"),
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
	ADD_EXTRA("Extras hinzufügen"), EXTRALABEL("Extra"), FINISH("Extras übernehmen"), THANK_YOU("Vielen Dank für Ihre Bestellung!");

	private final String value;

	private Strings(final String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

}
