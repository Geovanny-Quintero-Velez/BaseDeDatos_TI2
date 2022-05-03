package filters;

import java.util.Comparator;

import model.Person;

public class FilterLastName implements Comparator<Person>{
	@Override
	public int compare(Person per1, Person per2) {
		String lastName1=per1.getLastName();
		String lastName2=per2.getLastName();
		return lastName1.compareTo(lastName2)
		;
	}
}
