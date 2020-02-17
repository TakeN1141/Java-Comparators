package fmi.informatics.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;
import fmi.informatics.gui.PersonDataGUI;

// Четене и писане във файл
public class FileReader extends PersonDataGUI {

	private static final String PERSON_FILE_EXTENSION = ".file";
	private static final String PERSON_FILE_NAME = "people";
	private static final String FILE_PATH = "src/fmi/informatics/files/";
	private static final String FULL_PATH = FILE_PATH + PERSON_FILE_NAME + PERSON_FILE_EXTENSION;
	private String fileReader;

	private static ArrayList<Person> peopleList = new ArrayList<>();
	
	public static void createPersonFile() {
		File file = new File(FULL_PATH);
		file.getParentFile().mkdirs();
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.err.println("Файлът не може да бъде създаден!");
			e.printStackTrace();
		}
	}
	
	public static boolean isFileExists() {
		File file = new File(FULL_PATH);
		return file.exists();
	}

	@SuppressWarnings("resource")
	public static Person[] readPeople() {
		try {
			FileInputStream fileStream = new FileInputStream(FULL_PATH);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
			String stringLine;
			
			while((stringLine = bufferedReader.readLine()) != null) {
				String[] data = stringLine.split("\t");
				addPerson(data);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Person[] people = new Person[peopleList.size()];
		return peopleList.toArray(people);
	}
	
	private static void addPerson(String[] data) {
		int i = ThreadLocalRandom.current().nextInt(0, 1000);
		
		// Име, години, ЕГН, тегло, височина
		if (i%2 == 0) {
			Person student = new Student(
					data[0], Integer.parseInt(data[2]), "PU", "Informatics", 12324, 
					Integer.parseInt(data[1]), Integer.parseInt(data[4]), 
					Integer.parseInt(data[3]));
			
			peopleList.add(student);
		} else {
			Person professor = new Professor(
					data[0], "Assisstant", Integer.parseInt(data[2]),
					Integer.parseInt(data[1]), Integer.parseInt(data[4]),
					Integer.parseInt(data[3]));

			peopleList.add(professor);
		}
	}

	public static void writePeople(Person[] people) {
		Writer writer = null;
		
		try {
			// Искаме append функция, затова стойността в конструктора е true
			writer = new BufferedWriter(new FileWriter(FULL_PATH, true));
			
			for (Person person : people) {
				writer.append(person.getName() + "\t");
				writer.append(person.getAge() + "\t");
				writer.append(person.getEgn() + "\t");
				writer.append(person.getWeight() + "\t");
				writer.append(person.getHeight() + "\t");
				writer.append("\n");
				
				System.out.printf("%s е успешно добавен във файла! \n", person.getName());
			}
		} catch (IOException e) {
			System.err.println("Записът не може да бъде добавен във файла!");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.out.println("Записвачът не може да бъде затворен правилно!");
					e.printStackTrace();
				}
			}
		}
	}
}
