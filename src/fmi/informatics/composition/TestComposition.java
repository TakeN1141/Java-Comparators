package fmi.informatics.composition;

public class TestComposition {
	
	public static void main(String[] args) {
		
		RichStudent student = new RichStudent("Pesho", 123, "PU", "12459566", 23569887);
		student.goBar();
		
		RichStudent2 student2 = new RichStudent2("Ivan", 321, "PU", "123569", 25649);
		student2.goBar();
	}
}
