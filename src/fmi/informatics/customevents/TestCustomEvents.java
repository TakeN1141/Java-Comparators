package fmi.informatics.customevents;

public class TestCustomEvents {

	public static void main(String[] args) {
		
		Initiator initiator = new Initiator();
		Responder responder = new Responder("Responder1");
		Responder responder2 = new Responder("Responder2");
		
		initiator.addListener(responder);
		initiator.addListener(responder2);
		initiator.sayHello();
	}
}
