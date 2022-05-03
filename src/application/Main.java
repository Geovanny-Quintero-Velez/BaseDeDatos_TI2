package application;
	
import java.io.IOException;
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
	DataBase mc;
	public Main()
	{	mc=new DataBase();
		System.out.println("hola");
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			showGenerateData();
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
	
	public void showGenerateData()
	{
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/GenerateData.fxml"));
			root = (BorderPane)loader.load();
			
			GenerateDatController controller = loader.getController();
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
	
	public void showAddPeople()
	{
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/AddPeople.fxml"));
			root = (BorderPane)loader.load();
			
			AddPeopleController controllerz = loader.getController();
			controllerz.setMain(this);
			
			Scene scene= new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			currentStage.setScene(scene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchByCode() {
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Search.fxml"));
			root = (BorderPane)loader.load();
			
			SearchController controllerz = loader.getController();
			controllerz.setMain(this);
			controllerz.begining(1);
			Scene scene= new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			currentStage.setScene(scene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchByFullName() {
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Search.fxml"));
			root = (BorderPane)loader.load();
			
			SearchController controllerz = loader.getController();
			controllerz.setMain(this);
			controllerz.begining(4);
			Scene scene= new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			currentStage.setScene(scene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void searchByLastName() {
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Search.fxml"));
			root = (BorderPane)loader.load();
			
			SearchController controllerz = loader.getController();
			controllerz.setMain(this);
			controllerz.begining(3);
			Scene scene= new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			currentStage.setScene(scene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void searchByName() {
		try {
			BorderPane root;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Search.fxml"));
			root = (BorderPane)loader.load();
			
			SearchController controllerz = loader.getController();
			controllerz.setMain(this);
			controllerz.begining(2);
			Scene scene= new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			currentStage.setScene(scene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public ArrayList<Person> getArrayList(int index,String value){
		ArrayList<Person>a=mc.getFilteredList(index,value);
		return a;
	}
}
