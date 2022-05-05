package structures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;


class ArbolBinarioAVTest {
	ArbolBinarioAV<Integer,MyComp> arbol;
	int max;
	public void scenario1() {
		arbol=new ArbolBinarioAV<>(new MyComp());
	}
	
	public void scenario2() {
		arbol=new ArbolBinarioAV<>(new MyComp());
		max=100;
		for(int i=0;i<max;i++) {
			arbol.insert(i);
		}
	}
	
	public void scenario3() {
		arbol=new ArbolBinarioAV<>(new MyComp());
		
		arbol.insert(10);
		arbol.insert(-2);
		
		arbol.insert(-1);
		arbol.insert(20);
		
		arbol.insert(15);
		arbol.insert(7);
		
		arbol.insert(12);
		arbol.insert(11);
		
	}
	
	@Test
	void testGet() {
		scenario1();
		assertTrue(arbol.get(0)==null);
		scenario3();
		assertTrue(arbol.get(0)==null);
		assertTrue(arbol.get(10)==10);
	}

	@Test
	void testHeight () {
		scenario1();
		assertTrue(arbol.height()==0);
		scenario3();
		assertTrue(arbol.height()==5);
	}

	@Test
	void testSearch() {
		scenario3();
		assertTrue(arbol.search(11));
		assertFalse(arbol.search(-11));
	}

	@Test
	void testSize() {
		scenario1();
		assertTrue(arbol.size()==0);
		scenario3();
		assertTrue(arbol.size()==8);
		scenario2();
		assertTrue(arbol.size()==100);
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
		assertTrue(arbol.delete(56));
		assertEquals(arbol.size(),max-1);
	}
	
	public class MyComp implements Comparator<Integer>{

		@Override
		public int compare(Integer arg0, Integer arg1) {
			// TODO Auto-generated method stub
			return arg0-arg1;
		}
		
	}

}
