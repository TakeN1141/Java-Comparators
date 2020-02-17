package fmi.informatics.comparators;

import fmi.informatics.extending.Person;

public class AgeComparator extends PersonComparator {

	@Override
	public int compare(Person person1, Person person2) {
		
		if (person1.getAge() < person2.getAge()) {
			return -1 * sortOrder;
		} else if (person1.getAge() > person2.getAge()) {
			return 1 * sortOrder;
		} else {
			return 0;
		}
	}
}
