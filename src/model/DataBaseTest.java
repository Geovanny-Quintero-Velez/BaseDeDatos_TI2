package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import controller.ProgressBarController;

class DataBaseTest {
	DataBase db;
	
	private ProgressBarController controller;
	
	public void scneario1() {
		db=new DataBase();
	}
	@Test
	void testGeneratePeople() {
		scneario1();
		assertTrue(db.getFilterByCode().size()==0);
		assertTrue(db.getFilterByName().size()==0);
		assertTrue(db.getFilterByLastName().size()==0);
		assertTrue(db.getFilterByFullName().size()==0);
		try {
			db.generatePeople(1000, controller);
		} catch (IOException e) {
			fail();
		}
		for(int i=0;i<1000;i++) {
			for(int j=0;j<10000;j++) {
				//Tiempo de espera a los hilos
			}
		}
		assertTrue(db.getFilterByCode().size()>0);
		assertTrue(db.getFilterByName().size()>0);
		assertTrue(db.getFilterByLastName().size()>0);
		assertTrue(db.getFilterByFullName().size()>0);
		
	}

}
