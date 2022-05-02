package filters;

import java.util.Comparator;

import model.Person;

public class FilterFullName implements Comparator<Person>{
	@Override
	public int compare(Person per1, Person per2) {
		String name1=per1.getFullName();
		String name2=per2.getFullName();
		return name1.compareTo(name2)
		;
	}
}