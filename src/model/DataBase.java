package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import controller.ProgressBarController;
import enums.AGE_DISTRIBUTION;
import enums.COUNTRIES;
import filters.FilterCode;
import filters.FilterFullName;
import filters.FilterLastName;
import filters.FilterName;
import structures.ArbolBinario;

public class DataBase implements Serializable{
	public static final int PEOPLE_TO_GENERATE_DEFAULT = 10000;
	public static final int COUNTRIES_AMOUNT = 36;
	public static final double MIN_HEIGHT = 1.40;
	public static final double MAX_HEIGHT = 2.20;
	public static int peopleGenerated = 0;
	private static final long serialVersionUID = 1L;
	
	ArbolBinario<Person,FilterCode> filterByCode;
	ArbolBinario<Person,FilterName> filterByName;
	ArbolBinario<Person,FilterLastName> filterByLastName;
	ArbolBinario<Person,FilterFullName> filterByFullName;
	
	public DataBase() {
		filterByCode=new ArbolBinario<>(new FilterCode());
		filterByName=new ArbolBinario<>(new FilterName());
		filterByLastName=new ArbolBinario<>(new FilterLastName());
		filterByFullName=new ArbolBinario<>(new FilterFullName());
	}
	
	
	
	public ArbolBinario<Person, FilterCode> getFilterByCode() {
		return filterByCode;
	}
	
	public void delete(Person person) {
		filterByCode.delete(person);
		filterByName.delete(person);
		filterByLastName.delete(person);
		filterByFullName.delete(person);
	}
	
	public void insert(Person person) {
		filterByCode.insert(person);
		filterByName.insert(person);
		filterByLastName.insert(person);
		filterByFullName.insert(person);
	}

	public void setFilterByCode(ArbolBinario<Person, FilterCode> filterByCode) {
		this.filterByCode = filterByCode;
	}



	public ArbolBinario<Person, FilterName> getFilterByName() {
		return filterByName;
	}



	public void setFilterByName(ArbolBinario<Person, FilterName> filterByName) {
		this.filterByName = filterByName;
	}



	public ArbolBinario<Person, FilterLastName> getFilterByLastName() {
		return filterByLastName;
	}



	public void setFilterByLastName(ArbolBinario<Person, FilterLastName> filterByLastName) {
		this.filterByLastName = filterByLastName;
	}



	public ArbolBinario<Person, FilterFullName> getFilterByFullName() {
		return filterByFullName;
	}



	public void setFilterByFullName(ArbolBinario<Person, FilterFullName> filterByFullName) {
		this.filterByFullName = filterByFullName;
	}



	public ArrayList<Person> getFilteredList(int index, String param){
		
		switch(index) {
		case 1:
			return getFilterCode(param);
		case 2:
			return getFilterName(param);
		case 3:
			return getFilterLastName(param);
		case 4:
			return getFilterFullname(param);
		}
		return null;
	}
	
	public Person getPersonInList(int index, Person param){
		
		switch(index) {
		case 1:
			return getPersonCode(param);
		case 2:
			return getPersonName(param);
		case 3:
			return getPersonLastName(param);
		case 4:
			return getPersonFullname(param);
		}
		return null;
	}
	
	public ArrayList<Person> getFilterCode(String code){
		
		Person mediator=new Person();
		mediator.setCode(code);
		return filterByCode.getGreater(mediator);
	}
	
	public ArrayList<Person> getFilterName(String name){
		Person mediator=new Person();
		mediator.setName(name);
		return filterByName.getGreater(mediator);
	}
	
	public ArrayList<Person> getFilterLastName(String name){
		Person mediator=new Person();
		mediator.setLastName(name);
		return filterByLastName.getGreater(mediator);
	}
	
	public ArrayList<Person> getFilterFullname(String name){
		Person mediator=new Person();
		mediator.setFullName(name);
		return filterByFullName.getGreater(mediator);
	}
	
	public Person getPersonCode(Person code){
		
		return filterByCode.get(code);
	}
	
	public Person getPersonName(Person name){
		return filterByName.get(name);
	}
	
	public Person getPersonLastName(Person name){
		return filterByLastName.get(name);
	}
	
	public Person getPersonFullname(Person name){
		return filterByFullName.get(name);
	}
	
	
	public void generatePeople(int peopleToGenerate, ProgressBarController controller) throws IOException 
	{
		String initialTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		controller.setInitialTime(initialTime);
		
		int tenPercent = peopleToGenerate/10;
		
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
		ArrayList<Person> people = new ArrayList<>();
		String namesLine = "";
		String lastNamesLine = "";
		BufferedReader namesLector = null;
		BufferedReader lastNamesLector = null;
		String [] names=null;
		Random r = new Random();
        
		String name = "";
		String lastName = "";
		String gender = "";
		double height = 0; 
		try {
			namesLector = new BufferedReader(new FileReader("data\\babynames-clean.csv"));
			lastNamesLector = new BufferedReader(new FileReader("data\\Names_2010Census.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		names=(namesLector.readLine().split(","));
		do {	
			boyNames.add(names[0]);
			names=(namesLector.readLine().split(","));
		}while(!((names[1]).equals("girl")));
		while((namesLine = namesLector.readLine())!=null) {
			girlNames.add((namesLine.split(","))[0]);
		}
		lastNamesLector.readLine();
		while((lastNamesLine = lastNamesLector.readLine())!=null) {
			lastNames.add(lastNamesLine.split(",")[0]);
		}
		
		for(COUNTRIES country: COUNTRIES.values()) {
			
			if(peopleGenerated<= (peopleToGenerate / COUNTRIES_AMOUNT)/2) {
				gender = Person.MALE;
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._0_TO_14_.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._0_TO_14_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._0_TO_14_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(r.nextInt(boyNames.size()));
					boyNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
				}
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._15_TO_24.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._15_TO_24.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._15_TO_24.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(r.nextInt(boyNames.size()));
					boyNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
					}
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._25_TO_54_.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._25_TO_54_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._25_TO_54_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(r.nextInt(boyNames.size()));
					boyNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
					}
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._55_TO_64_.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._55_TO_64_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._55_TO_64_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(r.nextInt(boyNames.size()));
					boyNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
					}
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._65_TO_MORE_.getDistribution());j++) {
					if(boyNamesCounter == boyNames.size()) {
						boyNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._65_TO_MORE_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._65_TO_MORE_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = boyNames.get(r.nextInt(boyNames.size()));
					boyNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
					}
			}else{
				gender = Person.FEMALE;
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._0_TO_14_.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._0_TO_14_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._0_TO_14_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(r.nextInt(girlNames.size()));
					girlNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
					}
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._15_TO_24.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._15_TO_24.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._15_TO_24.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(r.nextInt(girlNames.size()));
					girlNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
				
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
					}
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._25_TO_54_.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._25_TO_54_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._25_TO_54_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(r.nextInt(girlNames.size()));
					girlNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
					}
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._55_TO_64_.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._55_TO_64_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._55_TO_64_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(r.nextInt(girlNames.size()));
					girlNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
					}
				for(int j=0; j<Math.ceil(((peopleToGenerate / COUNTRIES_AMOUNT)/2)*AGE_DISTRIBUTION._65_TO_MORE_.getDistribution());j++) {
					if(girlNamesCounter == girlNames.size()) {
						girlNamesCounter = 0;
						lastNamesCounter++;
					}
					
					startDate = actualDate.minusYears(AGE_DISTRIBUTION._65_TO_MORE_.getMax()); //start date
				    start = startDate.toEpochDay();

				    endDate = actualDate.minusYears(AGE_DISTRIBUTION._65_TO_MORE_.getMin()); //end date
				    end = endDate.toEpochDay();
				    
				    randomDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
					
					name = girlNames.get(r.nextInt(girlNames.size()));
					girlNamesCounter++;
					lastName = lastNames.get(r.nextInt(lastNames.size()));		
					height = Math.round((MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT)*Math.random())*100.0)/100.0;
					
					Person persona = new Person(name, lastName, gender, randomDate, height, country.name());
					people.add(persona);
					peopleGenerated++;
					watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
					}
			}
			
			
		}
		for(int i=0; i< peopleToGenerate-peopleGenerated;i++) {
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
			
			Person persona = new Person(name, lastName, gender, randomDate, height, COUNTRIES.UNITED_STATES.name());
			people.add(persona);
			peopleGenerated++;
			watch(controller, peopleGenerated, tenPercent, peopleToGenerate);
		}
		Hilo hilo1=new Hilo(getFilterByCode(),people);
		Hilo hilo2=new Hilo(getFilterByFullName(),people);
		Hilo hilo3=new Hilo(getFilterByLastName(),people);
		Hilo hilo4=new Hilo(getFilterByName(),people);
		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo4.start();
		
		controller.complete();
		String finalTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		controller.setFinalTime(finalTime);
	}
	
	public void watch(ProgressBarController controller, int peopleGenerated, int tenPercent, int peopleToGenerate)
	{
		for(int i = 1; i <= 10; i++)
		{
			if(peopleGenerated == tenPercent*(i))
			{
				double percent = i;
				
				controller.incrementTenPercent(percent/10);
			}
			else if(peopleGenerated == peopleToGenerate)
			{
				controller.complete();
			}
		}
	}

	
	class Hilo extends Thread{
		private ArbolBinario<Person,?> toAddElements;
		private ArrayList<Person> people;
		public Hilo(ArbolBinario<Person,?> toAddElements,ArrayList<Person> people) {
			this.toAddElements=toAddElements;
			this.people=people;
		}
		
		@Override
		public void run() {
			for(int i=0;i<people.size();i++) {
				toAddElements.insert(people.get(i));
			}
		
		}
	}
}
