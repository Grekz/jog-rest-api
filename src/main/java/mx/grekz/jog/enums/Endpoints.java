package mx.grekz.jog.enums;

public enum Endpoints {
	JOGS("/v1/jogs"),
	USERS("/v1/users");
	
	private final String text;
	
	private Endpoints(final String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
}
