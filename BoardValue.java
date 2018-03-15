
public enum BoardValue {
	HIT("Previously Attacked"), 
	MISS("Previously Attacked!"),
	EMPTY("Miss!"),
	SHIP("Hit!");
	
	private String message;
	BoardValue(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
