package controller;
import java.time.LocalDate;

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
	DatePicker birth;
	
	@FXML
	ComboBox<String> nationatilySelector;
	@FXML
	ComboBox<String> gender;
	@FXML
	TextField height;
	@FXML
	Label code;
	@FXML
	Label nationality;
	@FXML
	Label date;
	@FXML
	Label genderL;
	
	Main main;
	
	@FXML
	public void edite() {
		main.delete(person);
		LocalDate birthDate=birth.getValue();
		if(birthDate!=null) {
			person.setBirthDate(birthDate);
		}
		person.setName(name.getText());
		person.setLastName(lastName.getText());
		person.setFullName(name.getText()+" "+lastName.getText());
		person.setHeight(Double.parseDouble(height.getText()));
		
		String nationality=nationatilySelector.getValue();
		
		if(nationality!=null) {
			person.setNationality(nationality);
		}
		String gen=gender.getValue() ;
		if(gen!=null) {
			person.setGender(gen);
		}
		
		
		
		main.insert(person);
		main.showMenu();
	}
	
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
		nationatilySelector.setAccessibleText(person.getNationality());
		nationality.setText(person.getNationality());
		date.setText(person.getBirthDate().toString());
		genderL.setText(person.isGender());
		name.setText(person.getName());
		lastName.setText(person.getLastName());
		height.setText(person.getHeight()+"");
		code.setText(person.getCode());
		nationatilySelector.setAccessibleText(person.getNationality());
		gender.setAccessibleText(person.isGender());
	}
	@FXML
	public void delete() {
		main.delete(person);
		main.showMenu();
	}
	
	
	
	
}
