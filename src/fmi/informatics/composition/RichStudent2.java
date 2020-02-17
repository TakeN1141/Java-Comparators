package fmi.informatics.composition;

import fmi.informatics.extending.Student;

// Пример за наследяване
// RichStudent2 реализира логиката от RichStudent чрез наследяване на Student
public class RichStudent2 extends Student {

	public RichStudent2(String name, int egn, String university, String speciality, int facNumber) {
		super(name, egn, university, speciality, facNumber);
	}
	
	@Override
	protected String getTypicalDrink() {
		return "50 years Black Johny";
	}
}
