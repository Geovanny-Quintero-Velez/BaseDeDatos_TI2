package structures;

import java.util.Comparator;

public class Main {
	public static void main(String[]args) {
		ArbolBinario<Integer,My>a=new ArbolBinario<>(new My());
		int max=1_000_000;
		for(int i=0;i<max;i++) {
			a.insert(i*-1);
		}
		System.out.println(a.isBalanced());
		System.out.println(a.size());
		System.out.println(a.search(324567));
		System.out.println(a.get(98765));
		System.out.println(a.getRoot());
	}
	
	static class My implements Comparator<Integer>{

		@Override
		public int compare(Integer arg0, Integer arg1) {
			// TODO Auto-generated method stub
			return arg0-arg1;
		}
		
	}
}
