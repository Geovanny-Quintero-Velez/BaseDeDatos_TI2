package filters;

import java.util.Comparator;

import model.Person;

public class FilterFullName implements Comparator<Person>{
	@Override
	public int compare(Person per1, Person per2) {
		String name1=per1.getFullName();
		String name2=per2.getFullName();
		boolean flag=true;
		for(int j=0;j<name1.length()&&j<name2.length()&&flag;j++) {
			
			if(name1.charAt(j)==name2.charAt(j)) {
				
			}else if(name1.charAt(j)>name2.charAt(j)){
				return 1;
			}else {
				return -1;
			}
		}
	
		if(name1.length()>name2.length()) {
			return 1;
		}else if(name1.length()<name2.length()){
			return -1;
		}
		if(name1.equals(name2)) {
			return 0;
		}
		return 1;
	}
}