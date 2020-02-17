package fmi.informatics.nestedclasses;

public class MysteryAnimal {

	private AnimalName name;
	private String sound = "default sound";
	
	private class Cow {
		public Cow() {
			sound = "moo"; 
		}
	}
	
	private class Cat {
		public Cat() { 
			MysteryAnimal.this.sound = "meow";  // алтернативен, явен начин за достъп
		}
	}
	
	private class Dog {
		public Dog() { 
			sound = "woof";
		}
	}
	
	public MysteryAnimal(AnimalName name) {
		this.name = name;
		
		switch (name) {
			case Bella:
				new Cow();
				break;
			
			case Chloe:
				this.new Cat(); // алтернативен, явен начин за създаване
				break;
			
			case Molly:
				this.new Dog();
				break;
				
			default:
				System.out.println("Sorry, we haven't that kind of animal");
				break;
		}
	}
		
	public void showSound() {
		System.out.println(this.name.name() + " makes " + this.sound);
	}
}
