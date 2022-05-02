package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import structures.ArbolBinario;

public class Main {

	//(int) (Math.random()*(100-0) + 0
	public static void main(String[] args) {
		ArbolBinario<Integer, CualquierCosa> a= new ArbolBinario<>(new CualquierCosa());
		int max=100;
		for(int i=0; i<max; i++) {
			a.insert(i);
		}
		System.out.println("Exito");
		ArrayList<Integer> arr=a.getGreater(500);
		System.out.println(a.size());
		System.out.println(a.preOrden());
		System.out.println(a.isBalanced());
		System.out.println(a.delete(63));
		for(int i=0;i<100;i++) {
			Collections.sort(arr,new CualquierCosa());
			
		}
	}
	public ArrayList<?> getArrayList(int index){
		return null;
	}
	
	public static class CualquierCosa implements Comparator<Integer> {

		@Override
		public int compare(Integer arg0, Integer arg1) {
			return  arg0-arg1;
		}
		
	}
}
