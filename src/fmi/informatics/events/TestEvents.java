package fmi.informatics.events;

import java.util.ArrayList;

public class TestEvents {
	
	public static void main(String[] args) {
		TV tv = new TV();
		User user = new User("Pesho", tv);
		
		tv.addObserver(user);
		tv.setInStock(true); // not allowed
		
		ArrayList<String> all = new ArrayList<>();
		all.add("aaa");
		all.add("bbb");
		all.add("ccc");
		
		for (String s : all) {
			System.out.println(s);
			// all.remove(s); // not allowed
		}
	}
}
