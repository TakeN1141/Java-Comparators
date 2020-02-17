package fmi.informatics.customevents;

public class Responder implements HelloListener {
	
	private String responderName;
	
	public Responder(String responderName) {
		this.setResponderName(responderName);
	}

	@Override
	public void respondGreeting() {
		System.out.println("Hi, " + this.responderName + "!");
	}

	public String getResponderName() {
		return responderName;
	}

	public void setResponderName(String responderName) {
		this.responderName = responderName;
	}
}
