package fmi.informatics.customevents;

import java.util.ArrayList;
import java.util.List;

public class Initiator {
	
	// create a list of observers
	private List<HelloListener> listeners = new ArrayList<>();
	
	public void addListener(HelloListener listener) {
		listeners.add(listener);
	}

	public void notifyResponders() {
		for(HelloListener listener : listeners) {
			listener.respondGreeting();
		}
	}
	
	// create event
	public void sayHello() {
		System.out.println("Hello!");
		notifyResponders();
	}
}