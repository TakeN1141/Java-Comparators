package fmi.informatics.events;

// създаваме интерфейс Observable (наблюдаван)
public interface Observable {
	
	void addObserver(Observer o);

	void removeObserver(Observer o);

	void notifyObserver();
}
