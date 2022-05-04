package controller;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Person;
public class EditePeopleController {
	Person person;
	@FXML
	TextField name;
	@FXML
	TextField lastName;
	
	@FXML
	ComboBox<String> nationatilySelector;
	@FXML
	ComboBox<String> gender;
	@FXML
	TextField height;
	@FXML
	Label code;
	
	Main main;
	
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	public void setPerson(Person person) {
		gender.getItems().add("Male");
		gender.getItems().add("Female");
		
		nationatilySelector.getItems().add("Asia");
		nationatilySelector.getItems().add("Africa");
		nationatilySelector.getItems().add("Europe");
		nationatilySelector.getItems().add("North America");
		nationatilySelector.getItems().add("South America");
		nationatilySelector.getItems().add("Australia/Oceania");
		nationatilySelector.getItems().add("Antartica");
		this.person=person;
		name.setText(person.getName());
		lastName.setText(person.getLastName());
		height.setText(person.getHeight()+"");
		code.setText(person.getCode());
		
	}
	
	
}
