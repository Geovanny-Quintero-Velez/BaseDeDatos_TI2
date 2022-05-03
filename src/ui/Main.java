package ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import enums.*;
import model.Person;

public class Main {
	
	public static final int PEOPLE_TO_GENERATE = 1000000;
	public static final int COUNTRIES_AMOUNT = 36;
	public static final double MIN_HEIGHT = 1.40;
	public static final double MAX_HEIGHT = 2.20;
	public static int peopleGenerated = 0;
	

	public static void main(String[] args) {
		
	}
	
	public static void generatePeople() throws IOException  {
		LocalDate actualDate = LocalDate.now();
		LocalDate startDate = null;
		LocalDate endDate = null;
		LocalDate randomDate = null;
		long start = 0;
		long end = 0;
		int boyNamesCounter = 0;
		int girlNamesCounter = 0;
		int lastNamesCounter = 0;
		ArrayList<String> boyNames = new ArrayList<>();
		ArrayList<String> girlNames = new ArrayList<>();
		ArrayList<String> lastNames = new ArrayList<>();
		String namesLine = "";
		String lastNamesLine = "";
		BufferedReader namesLector = null;
		BufferedReader lastNamesLector = null;
		try {
			namesLector = new BufferedReader(new FileReader("..\\..\\data\\babynames-clean.csv"));
			lastNamesLector = new BufferedReader(new FileReader("..\\..\\data\\Names_2010Census.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		do {	
			boyNames.add((namesLector.readLine().split(","))[0]);
		}while(!(boyNames.get(boyNames.size()-1).equals("girl")));
		while((namesLine = namesLector.readLine())!=null) {
			girlNames.add((namesLine.split(","))[0]);
		}
		while((lastNamesLine = lastNamesLector.readLine())!=null) {
			lastNames.add((lastNamesLine.split(","))[0]);
		}
		
		for(COUNTRIES country: COUNTRIES.values()) {
			
			String name = "";
			String lastName = "";
			String gender = "";
			double height = 0;
			
			if(peopleGenerated<= (PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2) {
				gender = Person.MALE;
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._0_TO_14_.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._0_TO_14_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._0_TO_14_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(boyNamesCounter);
					boyNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
			
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
				}
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._15_TO_24.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._15_TO_24.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._15_TO_24.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(boyNamesCounter);
					boyNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
					}
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._25_TO_54_.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._25_TO_54_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._25_TO_54_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(boyNamesCounter);
					boyNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
					}
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._55_TO_64_.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._55_TO_64_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._55_TO_64_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(boyNamesCounter);
					boyNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
					}
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._65_TO_MORE_.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._65_TO_MORE_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._65_TO_MORE_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(boyNamesCounter);
					boyNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
					}
			}else{
				gender = Person.FEMALE;
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._0_TO_14_.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._0_TO_14_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._0_TO_14_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(girlNamesCounter);
					girlNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
					}
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._15_TO_24.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._15_TO_24.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._15_TO_24.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(girlNamesCounter);
					girlNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
				
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
					}
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._25_TO_54_.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._25_TO_54_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._25_TO_54_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(girlNamesCounter);
					girlNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
					}
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._55_TO_64_.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._55_TO_64_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._55_TO_64_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(girlNamesCounter);
					girlNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
					}
				for(int j=0; j<Math.floor(((PEOPLE_TO_GENERATE / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._65_TO_MORE_.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._65_TO_MORE_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._65_TO_MORE_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(girlNamesCounter);
					girlNamesCounter++;
					lastName = lastNames.get(lastNamesCounter);		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					peopleGenerated++;
					}
			}
		}	
	}	
}
