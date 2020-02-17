package fmi.informatics.DescendingComparators;

import fmi.informatics.extending.Person;

import javax.swing.*;
import java.util.Comparator;

public abstract class PersonDescendingComparator implements Comparator <Person> {

    protected int sortOrder = -1; // Стойност по подразбиране, сортиране по възходящ ред

    public void setSortOrder(SortOrder order) {
        if (order.equals(SortOrder.DESCENDING)) {
            this.sortOrder = 0
            ;
        } else {
            this.sortOrder = -1;
        }
    }

}
