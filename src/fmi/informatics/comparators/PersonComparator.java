package fmi.informatics.comparators;

import java.util.Comparator;

import javax.swing.SortOrder;

import fmi.informatics.extending.Person;

public abstract class PersonComparator implements Comparator<Person> {

	protected int sortOrder = 1; // Стойност по подразбиране, сортиране по възходящ ред

	public void setSortOrder(SortOrder order) {
		if (order.equals(SortOrder.DESCENDING)) {
			this.sortOrder = -1;
		} else {
			this.sortOrder = 1;
		}
	}
}
