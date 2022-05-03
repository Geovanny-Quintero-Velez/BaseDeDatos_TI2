package ui;

import java.util.Comparator;

import structures.ArbolBinario;

public class Main {

	//(int) (Math.random()*(100-0) + 0
	public static void main(String[] args) {
		ArbolBinario<Integer, CualquierCosa> arbolito= new ArbolBinario<>(new CualquierCosa());
		for(int i=1; i<100; i++) {
			arbolito.insert(3) ;
			arbolito.insert(9) ;
			arbolito.insert(2) ;
			arbolito.insert(1) ;
			arbolito.insert(0) ;
		}
		System.out.println(arbolito.inorderReverse());
		System.out.println(arbolito.isBalanced());
		System.out.println(arbolito.factor());
	}
	
	public static class CualquierCosa implements Comparator<Integer> {

		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg0 - arg1;
		}
		
	}
}
