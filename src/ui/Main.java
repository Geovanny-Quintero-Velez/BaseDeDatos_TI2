package ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static final int PEOPLE_TO_GENERATE = 1000000;
	public static final int COUNTRIES_AMOUNT = 35;
	public static int peopleGenerated;

	public static void main(String[] args) {
		for(int i=0;i<1000000;i++) {
			System.out.println("i = "+i);
		}
	}
	
	public static void generatePeople() throws IOException  {
		ArrayList<String> boyNames = new ArrayList<>();
		ArrayList<String> girlNames = new ArrayList<>();
		String namesLine = "";
		String lastNamesLine = "";
		String[] names = null;
		String[] lastNames;
		BufferedReader namesLector = null;
		BufferedReader lastNamesLector;
		try {
			namesLector = new BufferedReader(new FileReader("..\\..\\data\\babynames-clean.csv"));
			lastNamesLector = new BufferedReader(new FileReader("..\\..\\data\\Names_2010Census.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		do {
			names = (namesLector.readLine()).split(",");	
			boyNames.add(names[0]);
		}while(!(names[(names.length) -1].equals("girl")));
		while((namesLine = namesLector.readLine())!=null) {
			names = namesLine.split(",");
			girlNames.add(names[0]);
		}
		
		
	}
}
