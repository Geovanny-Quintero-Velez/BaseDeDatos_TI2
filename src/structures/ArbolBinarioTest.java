package structures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.Test;

class ArbolBinarioTest {
	ArbolBinario<Integer,MyComp> arbol;
	int max;
	public void scenario1() {
		arbol=new ArbolBinario<>(new MyComp());
	}
	
	public void scenario2() {
		arbol=new ArbolBinario<>(new MyComp());
		max=1000;
		for(int i=0;i<max;i++) {
			arbol.insert(i);
		}
	}
	public void scenario3() {
		arbol=new ArbolBinario<>(new MyComp());
		max=100;
		Random r=new Random();
		for(int i=0;i<max;i++) {
			arbol.insert(r.nextInt(max));
		}
	}
	
	
	@Test
	void testInsertE() {
		scenario1();
		arbol.insert(1);
		assertEquals(arbol.get(1),1);
		assertEquals(arbol.size(),1);
		assertEquals(arbol.factor(),0);
		scenario2();
		assertEquals(arbol.get(99),99);
		assertEquals(arbol.size(),max);
		assertTrue(arbol.factor()<2&&arbol.factor()>-2);
		scenario3();
		assertTrue(arbol.factor()<2&&arbol.factor()>-2);
	}

	@Test
	void testSearch() {
		scenario2();
		assertTrue(arbol.search(100));
		assertFalse(arbol.search(1000));
		scenario1();
		assertFalse(arbol.search(1));
		
	}

	@Test
	void testDelete() {
		scenario1();
		assertFalse(arbol.delete(1));
		arbol.insert(1);
		arbol.insert(2);
		assertEquals(arbol.size(),2);
		assertTrue(arbol.delete(1));
		assertEquals(arbol.size(),1);
		assertTrue(arbol.search(2));
		assertFalse(arbol.search(1));
		scenario2();
		assertEquals(arbol.size(),max);
		assertTrue(arbol.delete(230));
		assertEquals(arbol.size(),max-1);
		assertTrue(arbol.isBalanced());
		
	}
	
	public class MyComp implements Comparator<Integer>{

		@Override
		public int compare(Integer arg0, Integer arg1) {
			// TODO Auto-generated method stub
			return arg0-arg1;
		}
		
	}

}
