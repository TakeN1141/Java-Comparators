package fmi.informatics.extending;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class Professor extends Person {

	private String title;
	
	public Professor() {
		
	}
	
	public Professor(String title, String name, boolean isMale, 
					 int age, int height, int weight) {
		super(name, isMale, age, height, weight);
		this.title = title;
	}
	
	public Professor(String name, String title, int egn, int age, int height, int weight) {
		super(name, egn, age, height, weight);
		this.title = title;
	}
	
	@Override
	public String toString() {
		return String.format("The professor %s is with title %s", 
							 this.getName(), this.getTitle());
	}

	// Имплементация на абстрактен метод
	@Override
	protected String getTypicalDrink() {
		return "50 years Black Johny";
	}

	// Пренаписване на метод от супер клас
	@Override
	public void goBar() {
		super.goBar();
		System.out.println("After second drink the Professor is happy");
	}

	// Имплементация на методите от двата интерфейса (без getShower(), който е
	// имплементиран в абстрактният клас. Ако е необходимо getShower() може да се пренапише)
	@Override
	public void getUpEarly(Calendar hour) {
		System.out.println("The professor usually gets up at" + hour);
	}

	@Override
	public void run(int minutes) {
		// TODO Auto-generated method stub
	}

	@Override
	public void study() {
		// TODO Auto-generated method stub
	}

	@Override
	public void think() {
		// TODO Auto-generated method stub
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
	}

	// Getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	// Създаваме клас ProfessorGenerator
	public static class ProfessorGenerator {
		
		private static String[] names = {"Petyr", "Ivan", "Mariya", "Lilly", "Georgi", "Petya"};
		private static String[] titles = {"Professor", "PhD", "Asociate assisstant"};
		
		public static Professor make() {
			int arrayIndex = ThreadLocalRandom.current().nextInt(0, names.length);
			String name = names[arrayIndex];
			
			arrayIndex = ThreadLocalRandom.current().nextInt(0, titles.length);
			String title = titles[arrayIndex];
			
			int egn = ThreadLocalRandom.current().nextInt(111111, 999999);
			
			int age = ThreadLocalRandom.current().nextInt(18, 80);
			
			int height =  ThreadLocalRandom.current().nextInt(150, 220);
			
			int weight = ThreadLocalRandom.current().nextInt(50, 220);
			
			return new Professor(name, title, egn, age, height, weight);
		}
	}
}
