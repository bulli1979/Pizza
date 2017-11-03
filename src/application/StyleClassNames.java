package application;

public enum StyleClassNames {
	HEADLINE("headline"),
	NAVIGATION("navigation"), 
	LOGINTEXT("logintext"), 
	BESTELLBUTTON_CENTER("bestellbuttonCenter"), 
	CENTERBOX("cennterBox"),
	CENTERTEXT("centerText"), 
	TABLE("table"), 
	LISTHEADSTYLE("listHeadStyle"), 
	LISTCOLSTYLE("listColStyle"), 
	LISTEVEN("even"), 
	ACTIVE("activeButton"),
	INACTIVE("inactiveButton"), 
	VALIDATIONERROR("validationError");
	private final String value;
	
	private StyleClassNames(final String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
