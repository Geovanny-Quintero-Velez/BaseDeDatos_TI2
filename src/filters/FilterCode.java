package filters;

import java.util.Comparator;

import model.Person;

public class FilterCode implements Comparator<Person>{
	@Override
	public int compare(Person per1, Person per2) {
		String code1=per1.getCode();
		String code2=per2.getCode();
		return code1.compareTo(code2)
		;
	}
}
