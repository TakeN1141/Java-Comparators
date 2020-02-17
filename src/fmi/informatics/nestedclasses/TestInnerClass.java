package fmi.informatics.nestedclasses;

public class TestInnerClass {

	public static void main(String[] args) {
		
		MysteryAnimal mysteryAnimal = new MysteryAnimal(AnimalName.Bella);
		mysteryAnimal.showSound();
		
		MysteryAnimal mysteryAnimal2 = new MysteryAnimal(AnimalName.Chloe);
		mysteryAnimal2.showSound();
		
		MysteryAnimal mysteryAnimal3 = new MysteryAnimal(AnimalName.Betty);
		mysteryAnimal3.showSound();
	}
}
