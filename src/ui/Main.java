package ui;

import java.util.Comparator;

import structures.ArbolBinario;

public class Main {

	//(int) (Math.random()*(100-0) + 0
	public static void main(String[] args) {
		ArbolBinario<Integer, CualquierCosa> a= new ArbolBinario<>(new CualquierCosa());
		int max=100000;
		int [] arr=new int[max];
		for(int i=0; i<max; i++) {
			arr[i]=(int) (Math.random()*(max-0) + 0);
			a.insert(arr[i]);
		}
		System.out.println("Exito");
		System.out.println(a.size());
		System.out.println(a.preOrden());
		System.out.println(a.isBalanced());
	}
	
	public static class CualquierCosa implements Comparator<Integer> {

		@Override
		public int compare(Integer arg0, Integer arg1) {
			return  arg1-arg0;
		}
		
	}
}
