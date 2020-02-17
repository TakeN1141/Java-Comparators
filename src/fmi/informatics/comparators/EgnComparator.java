package fmi.informatics.comparators;

import fmi.informatics.extending.Person;

public class EgnComparator extends PersonComparator {

	@Override
	public int compare(Person person1, Person person2) {
		Integer egn1 = Integer.valueOf(person1.getEgn());
		Integer egn2 = Integer.valueOf(person2.getEgn());
		
		if (egn1.equals(egn2)) {
			return 0;
		} else {
			return (egn1.compareTo(egn2)) * sortOrder;
		}
	}
}
