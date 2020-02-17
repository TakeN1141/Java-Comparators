package fmi.informatics.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.sun.xml.internal.bind.v2.model.core.ClassInfo;
import fmi.informatics.comparators.AgeComparator;
import fmi.informatics.comparators.EgnComparator;
import fmi.informatics.comparators.HeightComparator;
import fmi.informatics.comparators.NameComparator;
import fmi.informatics.comparators.PersonComparator;
import fmi.informatics.comparators.WeightComparator;
import fmi.informatics.enums.EType;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;
import fmi.informatics.util.FileReader;

// създаваме клас PersonDataGUI
public class PersonDataGUI {
	
	public static Person[] people;
	public static Person[] peopleRefreshArray;
	public static PersonDataModel dataModelRefresh;
	JTable table;
	PersonDataModel personDataModel;

	private String repaint;

	public String getRepaint() {
		return repaint;
	}

	public static void main(String[] args) {
		// Ако извикваме четенето от файл, трябва да закоментираме метода getPeople()
		getPeople();
		
		// TODO Извикваме четенето от файл
		// people = FileReader.readPeople();
		
		// TODO Извикваме писането във файл
		initializeData();
		
		PersonDataGUI gui = new PersonDataGUI();
		gui.createAndShowGUI();
	}
	
	// TODO Добавяме писането/четенето във файл
	private static void initializeData() {
		if (!FileReader.isFileExists()) {
			FileReader.createPersonFile();
		}
		
		FileReader.writePeople(people);
	}
	
	public static void getPeople() {
		people = new Person[8];
		
		for (int i = 0; i < 4; i++) {
			Person student = Student.StudentGenerator.make();
			people[i] = student;
		}
		
		for (int i = 4; i < 8; i++) {
			Person professor = Professor.ProfessorGenerator.make();
			people[i] = professor;
		}
		peopleRefreshArray = people;
		dataModelRefresh = new PersonDataModel(peopleRefreshArray);
	}


	public void createAndShowGUI() {
		JFrame frame = new JFrame("Таблица с данни за хора");
		frame.setSize(500, 500);

		JLabel label = new JLabel("Списък с потребители", JLabel.CENTER);
		personDataModel = new PersonDataModel(people);
		table = new JTable(personDataModel);
		JTable tableBackup = new JTable(personDataModel);
		JScrollPane scrollPane = new JScrollPane(table);
		table.addMouseListener(new ClassInformation());
		// Добавяме бутон за сортиране по години с Comparable interface
		JButton buttonSortAge = new JButton("Сортирай по години");

		// Добавяме бутон за сортиране
		JButton buttonSort = new JButton("Сортирай");

		// TODO Добавяме бутон за филтриране
		JButton buttonFilter = new JButton("Филтрирай");
		JButton buttonRefresh = new JButton("Refresh");

		// TODO Добавяме панел, където ще поставим бутоните
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(buttonSortAge);
		buttonsPanel.add(buttonSort);
		buttonsPanel.add(buttonFilter);
		buttonsPanel.add(buttonRefresh);

		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());

		container.add(label, BorderLayout.NORTH);
		container.add(scrollPane, BorderLayout.CENTER);
		// TODO Добавяме панелът с бутоните в контейнера
		container.add(buttonsPanel, BorderLayout.SOUTH);

		buttonRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setModel(dataModelRefresh);
				table.repaint();
			}
		});

		// Добавяме listener към бутона за сортиране по години
		buttonSortAge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Arrays.sort(people);
				table.repaint();
			}
		});

		// TODO Променяме диалога за сортиране
		final JDialog sortDialog = new CustomDialog(getSortText(), this, EType.SORT);

		// TODO Добавяме диалог за филтрацията
		final JDialog filterDialog = new CustomDialog(getFilterText(), this, EType.FILTER);

		// Добавяме listener към бутона за сортиране
		buttonSort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortDialog.pack();
				sortDialog.setVisible(true);
			}
		});

		// TODO Добавяме listener за филтрация
		buttonFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterDialog.pack();
				filterDialog.setVisible(true);
			}
		});
		buttonRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		frame.setVisible(true);
	}
	// TODO Добавяме метод за филтриране
	public void filterTable(int intValue, JTable table, Person[] people) {

		switch (intValue) {
			case 1: 
				this.personDataModel = new PersonDataModel(filterData(people, Student.class));
				break;
			case 2: 
				this.personDataModel = new PersonDataModel(filterData(people, Professor.class));
				break;
			case 3: 
				this.personDataModel = new PersonDataModel(filterData(people, Person.class));
				break;
		}
		
		table.setModel(this.personDataModel);
		table.repaint();	
	}
	
	// TODO Добавяме помощен метод за филтриране
	private Person[] filterData(Person[] persons, Class<?> clazz) {
		ArrayList<Person> filteredData = new ArrayList<>();
		
		for (int i = 0; i < persons.length; i++) {
			
			if (clazz == Person.class) {
				// Тук обхващаме филтрирането на "Други"
				if (!(persons[i] instanceof Student) && !(persons[i] instanceof Professor)) {
					filteredData.add(persons[i]);
				}
			} else if (clazz.isInstance(persons[i])) { // Филтриране по студент или професор
				filteredData.add(persons[i]);
			}
		}
		
		// Преобразуваме списъка в масив
		Person[] filteredDataArray = new Person[filteredData.size()];
		filteredDataArray = filteredData.toArray(filteredDataArray);
		return filteredDataArray;
	}

	public void sortTable(int intValue, JTable table, Person[] people) {
		PersonComparator comparator = null;
		
		switch (intValue) {
			case 1: 
				comparator = new NameComparator(); 
				break;
			case 2: 
				comparator = new EgnComparator();
				break;
			case 3:
				comparator = new HeightComparator();
				break;
			case 4: 
				comparator = new WeightComparator();
				break;
			case 5:
				comparator = new AgeComparator();
				break;
		}

		if (comparator == null) { // Ако стойността е null - сортирай по подразбиране
			Arrays.sort(people); // Сортировка по подразбиране по години
		} else {
			Arrays.sort(people, comparator);
		}
		
		table.repaint();	
	}
	
	private static String getSortText() {
		return "Моля, въведете цифрата на колоната, по която да се сортират данните: \n" +
				" 1 - Име \n" +
				" 2 - ЕГН \n" +
				" 3 - Височина \n" +
				" 4 - Тегло \n" +
				" 5 - Години \n"; 
	}
	
	// TODO Добавяме текст, който да се визуализира в диалога за филтриране
	private static String getFilterText(){
		return "Моля, въведете цифрата на филтъра, който искате да използвате: \n" +
			   " 1 - Студенти \n" +
			   " 2 - Преподаватели \n" + 
			   " 3 - Други \n";
	}

	private class ClassInformation implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getClickCount() == 2) {
				StringBuilder sb = new StringBuilder();
				sb.append(" Name - ");
				sb.append(table.getValueAt(table.getSelectedRow(), 0).toString());
				sb.append(",\n Egn - ");
				sb.append(table.getValueAt(table.getSelectedRow(), 1).toString());
				sb.append(", \n Age - ");
				sb.append(table.getValueAt(table.getSelectedRow(), 2).toString());
				sb.append(", \n Height - ");
				sb.append(table.getValueAt(table.getSelectedRow(), 3).toString());
				sb.append(",\n Weight - ");
				sb.append(table.getValueAt(table.getSelectedRow(), 4).toString());

				JFrame frameInfo = new JFrame();
				frameInfo.setSize(500, 500);
				JPanel text = new JPanel();
				TextArea textarea = new TextArea("",20,20,TextArea.SCROLLBARS_NONE);
				textarea.setFont(new Font("Helvetica",Font.PLAIN,24));
				textarea.setPreferredSize(new Dimension(500,500));
				text.add(textarea);
				textarea.setText("");
				textarea.append(sb.toString());
				frameInfo.getContentPane();
				frameInfo.add(textarea);
				frameInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frameInfo.setVisible(true);
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}
}
