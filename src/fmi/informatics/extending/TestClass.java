package fmi.informatics.extending;

import java.util.ArrayList;
import java.util.List;

import fmi.informatics.composition.RichStudent2;

public class TestClass {

	public static void main(String[] args) {
		testGenerator();
		testAnonymousClasses();
		testRTTI();
	}
	
	// Демонстрация на генератора на студенти
	public static void testGenerator() {
		List<Student> students = new ArrayList<>();
		
		for(int i = 0; i <= 6; i++) {
			Student student = Student.StudentGenerator.make();
			students.add(student);
		}

		for(Student student: students) {
			System.out.println(student);
		}
	}
	
	// Пример за анонимен клас
	// Използвайки примера с клас RichStudent2 създаваме анонимен клас със същата функционалност. 
	// Забележете, че метода goBar() не принтира името на класа понеже той е анонимен.
	public static void testAnonymousClasses() {
		Student student = new Student("Pesho", 123, "PU", "5698", 2569) {
			@Override
			public String getTypicalDrink(){
				return "50 years Black Johny";
			}
		};
		student.goBar();
		
		RichStudent2 richStudent = new RichStudent2("Pesho", 1234, "PU", "5698", 2569);
		richStudent.goBar();
	}
	
	// Пример за RTTI
	// Използваме класовете Person, Student и Professor за демонстрация и оператора instanceof
	@SuppressWarnings("rawtypes")
	public static void testRTTI() {
		Person person = Student.StudentGenerator.make();
		
		// Пример за класически RTTI с instanceof
		if(person instanceof Student) {
			String name = person.getName();
			String speciality = ((Student) person).getSpeciality();
			System.out.println("I'm " + name + " and I'm studying " + speciality);
		}
		
		// Пример за RTTI без instanceof
		// Използването на instanceof e по-добър и елегантен подход
		Class studentClass = Student.class;
		if(person.getClass().isAssignableFrom(studentClass)) {
			String speciality = ((Student) person).getSpeciality();
			System.out.println("I'm studying " + speciality);
		}
		
		if(person.getClass().equals(Student.class)) {
			String speciality = ((Student) person).getSpeciality();
			System.out.println("I'm studying " + speciality);
		}
	}
}