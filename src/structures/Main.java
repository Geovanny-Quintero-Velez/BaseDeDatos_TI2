package structures;

import java.util.ArrayList;
import java.util.Comparator;

import model.Person;

public class Main {
	public static void main(String[]args) {
		ArbolBinario<Integer,My>a=new ArbolBinario<>(new My());
		int max=1_000;
		for(int i=0;i<max;i++) {
			a.insert(i);
		}
		System.out.println(a.isBalanced());
		System.out.println(a.size());
		System.out.println(a.search(324567));
		System.out.println(a.get(98765));
		ArrayList<Integer> greater=a.getGreater(600);
		for(Integer i:greater) {
			System.out.println(i);
		}
		System.out.println(greater.size());
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
