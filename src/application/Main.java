package application;
	

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;


import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DataBase;
import model.Person;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application
{
	
	private Stage currentStage;
	private Stage configureStage;
	public int maxSearchs=100;

	public void setMaxSearchs(int maxSearchs) {
		this.maxSearchs = maxSearchs;
	}

	public int minSearchs=20;
	

	public void setMinSearchs(int minSearchs) {
		this.minSearchs = minSearchs;
	}

	DataBase mc;
	public Main()
	{	
		mc=new DataBase();
	}
	
	public void serialize() {
		try {
			FileOutputStream fos = new FileOutputStream("src\\file\\PeopleRecords.txt");
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
			FileInputStream fis = new FileInputStream("src\\file\\PeopleRecords.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			mc = (DataBase) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
		
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			deserialize();//
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
	
	public void options(SearchController thisSeachs) {
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/configurateSearch.fxml"));
			root = (BorderPane)loader.load();
			
			configureSearchController controller = loader.getController();
			controller.configure(maxSearchs, minSearchs, thisSeachs,this);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setHeight(180);
			stage.setWidth(280);
			stage.show();
			configureStage=stage;
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConfigureSearch() {
		configureStage.close();
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
	
	public void showProgressBar(int cantOfRegisters)
	{
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/ProgressBar.fxml"));
			root = (BorderPane)loader.load();
			
			ProgressBarController controller = loader.getController();
			controller.setMain(this);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			currentStage = stage;
			
			tryShowProgress(cantOfRegisters, controller);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tryShowProgress(int cantOfRegisters, ProgressBarController controller) throws IOException
	{
		generatePeople(cantOfRegisters, controller);
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
			controllerz.begining(1,maxSearchs,minSearchs);
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
			controllerz.begining(4,maxSearchs,minSearchs);
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
			controllerz.begining(3,maxSearchs,minSearchs);
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
			controllerz.begining(2,maxSearchs,minSearchs);
			root.setCenter(newRoot);
			currentStage.setHeight(250);
			currentStage.setWidth(400);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public ArrayList<Person> getArrayList(int index,String value){
		ArrayList<Person>a=mc.getFilteredList(index,value);
		return a;
	}
	
	public Person getPersonInList(int index,Person value){
		return mc.getPersonInList(index,value);
	}
	
	public ArrayList<Person> getList(int index,String value){
		return getArrayList(index,value);
	}
	
	public void generatePeople(int amount,  ProgressBarController controller)throws IOException {
		mc.generatePeople(amount, controller);
	}
	
}	
	
	
	
	

