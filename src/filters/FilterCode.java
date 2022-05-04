package filters;

import java.util.Comparator;

import model.Person;

public class FilterCode implements Comparator<Person>{
	@Override
	public int compare(Person per1, Person per2) {
		String code1=per1.getCode();
		String code2=per2.getCode();
		
		boolean flag=true;
		for(int j=0;j<code1.length()&&j<code2.length()&&flag;j++) {
			if(code1.charAt(j)==code2.charAt(j)) {
			}else if(code1.charAt(j)>code2.charAt(j)){
				return 1;
			}else {
				return -1;
			}
		}
		if(code1.length()>code2.length()) {
			return 1;
		}else if(code1.length()<code2.length()){
			return -1;
		}
		
		if(code1.equals(code2)) {
			return 0;
		}
		return 1;
	}
}
