package application;
	
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import controller.*;
import enums.AGE_DISTRIBUTION;
import enums.COUNTRIES;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DataBase;
import model.Person;
import structures.ArbolBinario;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application
{
	public static final int PEOPLE_TO_GENERATE_DEFAULT = 10000;
	public static final int COUNTRIES_AMOUNT = 36;
	public static final double MIN_HEIGHT = 1.40;
	public static final double MAX_HEIGHT = 2.20;
	public static int peopleGenerated = 0;
	private Stage currentStage;
	static DataBase mc;
	public Main()
	{	
		mc=new DataBase();
	}
	
	public void serialize() {
		try {
			FileOutputStream fos = new FileOutputStream("..\\file\\PeopleRecords.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(mc);
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deserialize() {
		try {
			FileInputStream fis = new FileInputStream("..\\file\\PeopleRecords.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			mc = (DataBase) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			mc=new DataBase();
			showNewMenu();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<?> getArrayList(int index){
		return null;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void showNewMenu()
	{
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Menu.fxml"));
			root = (BorderPane)loader.load();
			
			MenuController controller = loader.getController();
			controller.setMain(this);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			currentStage = stage;
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showGenerateData()
	{
		try {
			BorderPane newRoot;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/GenerateData.fxml"));
			newRoot = (BorderPane)loader.load();
			BorderPane root = (BorderPane) currentStage.getScene().getRoot();
			GenerateDatController controllerz = loader.getController();
			controllerz.setMain(this);
			root.setCenter(newRoot);
			currentStage.setHeight(250);
			currentStage.setWidth(290);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showProgressBar()
	{
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/ProgressBar.fxml"));
			root = (BorderPane)loader.load();
			
			ProgressBarController controllerz = loader.getController();
			controllerz.setMain(this);
			
			Scene scene= new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			currentStage.setScene(scene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMenu()
	{
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Menu.fxml"));
			root = (BorderPane)loader.load();
			
			MenuController controllerz = loader.getController();
			controllerz.setMain(this);
			
			Scene scene= new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			currentStage.setScene(scene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showEditePeople(Person person)
	{
		try {
			BorderPane newRoot;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/EditPeople.fxml"));
			newRoot = (BorderPane)loader.load();
			BorderPane root = (BorderPane) currentStage.getScene().getRoot();
			EditePeopleController controller = loader.getController();
			controller.setMain(this);
			controller.setPerson(person);
			currentStage.setHeight(400);
			currentStage.setWidth(310);
			root.setCenter(newRoot);
			currentStage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAddPeople()
	{
		try {
			BorderPane newRoot;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/AddPeople.fxml"));
			newRoot = (BorderPane)loader.load();
			BorderPane root = (BorderPane) currentStage.getScene().getRoot();
			AddPeopleController controllerz = loader.getController();
			controllerz.setMain(this);
			
			root.setCenter(newRoot);
			currentStage.setHeight(350);
			currentStage.setWidth(300);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchByCode() {
		try {
			BorderPane newRoot;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Search.fxml"));
			newRoot = (BorderPane)loader.load();
			BorderPane root = (BorderPane) currentStage.getScene().getRoot();
			SearchController controllerz = loader.getController();
			controllerz.setMain(this);
			controllerz.begining(1);
			root.setCenter(newRoot);
			currentStage.setHeight(250);
			currentStage.setWidth(400);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchByFullName() {
		try {
			BorderPane newRoot;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Search.fxml"));
			newRoot = (BorderPane)loader.load();
			BorderPane root = (BorderPane) currentStage.getScene().getRoot();
			SearchController controllerz = loader.getController();
			controllerz.setMain(this);
			controllerz.begining(4);
			currentStage.setHeight(250);
			currentStage.setWidth(400);
			root.setCenter(newRoot);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void delete(Person person) {
		mc.delete(person);
	}
	
	public void insert(Person person) {
		mc.insert(person);
	}
	
	public void searchByLastName() {
		try {
			BorderPane newRoot;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Search.fxml"));
			newRoot = (BorderPane)loader.load();
			BorderPane root = (BorderPane) currentStage.getScene().getRoot();
			SearchController controllerz = loader.getController();
			controllerz.setMain(this);
			controllerz.begining(3);
			root.setCenter(newRoot);
			currentStage.setHeight(250);
			currentStage.setWidth(400);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void searchByName() {
		try {
			BorderPane newRoot;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Search.fxml"));
			newRoot = (BorderPane)loader.load();
			BorderPane root = (BorderPane) currentStage.getScene().getRoot();
			SearchController controllerz = loader.getController();
			controllerz.setMain(this);
			controllerz.begining(2);
			
			root.setCenter(newRoot);
			currentStage.setHeight(250);
			currentStage.setWidth(400);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static ArrayList<Person> getArrayList(int index,String value){
		ArrayList<Person>a=mc.getFilteredList(index,value);
		return a;
	}
	
	public Person getPersonInList(int index,Person value){
		return mc.getPersonInList(index,value);
	}
	
	public ArrayList<Person> getList(int index,String value){
		return getArrayList(index,value);
	}

	public void generatePeople(int peopleToGenerate) throws IOException  {
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
		}
		Hilo hilo1=new Hilo(mc.getFilterByCode(),people);
		Hilo hilo2=new Hilo(mc.getFilterByFullName(),people);
		Hilo hilo3=new Hilo(mc.getFilterByLastName(),people);
		Hilo hilo4=new Hilo(mc.getFilterByName(),people);
		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo4.start();
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
			System.out.println(toAddElements.size());
		
		}
	}

