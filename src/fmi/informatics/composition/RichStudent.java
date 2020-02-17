package fmi.informatics.composition;

import java.util.Calendar;

import fmi.informatics.extending.Student;

// Пример за композиция
// RichStudent композира в себе си клас Student и предоставя сходни методи с изключение на goBar()
public class RichStudent {
	
	private Student student;
	
	public RichStudent(String name, int egn, String university, String speciality, int facNumber) {
		student = new Student(name, egn, university, speciality, facNumber);
	}
	
	public void run() { 
		student.run(); 
	}
	
	public void study() { 
		student.study(); 
	}
	
	public void takeExam() { 
		student.takeExam(); 
	}
	
	public void getUpEarly(Calendar hour) { 
		student.getUpEarly(hour);
	}

	public void goBar() {
		System.out.println("The " + this.getClass().getSimpleName() + " " + 
				student.getName() + " drinking ");
		System.out.println("50 years Black Johny");
	}
	
	@Override
	public String toString() { 
		return student.toString(); 
	}
	
	// Getters and Setters
	public String getUniversity() { 
		return student.getUniversity(); 
	}
	
	public void setUniversity(String university) { 
		student.setUniversity(university);
	}
	
	public String getSpeciality() { 
		return student.getSpeciality();
	}
	
	public void setSpeciality(String speciality) { 
		student.setSpeciality(speciality);
	}
}
