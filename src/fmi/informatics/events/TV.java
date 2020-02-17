package fmi.informatics.events;

import java.util.ArrayList;
import java.util.List;

public class TV implements Observable {
	
	private List<Observer> people = new ArrayList<>();
	private boolean inStock;

	@Override
	public void addObserver(Observer o) {
		people.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		people.remove(o);
	}

	@Override
	public void notifyObserver() {
		for(Observer person : people) {
			person.update(); // not allowed to remove elements from the list at this time
		}
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
		if (inStock) notifyObserver();
	}
}