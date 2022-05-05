package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import model.Person;

import java.time.LocalDate;

import application.Main;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

public class AddPeopleController
{
	private Main main;
	private LocalDate birthDate;
	
	@FXML
	private Button createButton;
	@FXML
	private DatePicker dateSelector;
	@FXML
	private TextField nameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private ComboBox<String> genderSelector;
	@FXML
	private ComboBox<String> nationatilySelector;
	@FXML
	private TextField heigthField;

	@FXML
	public void Create()
	{
		birthDate = dateSelector.getValue();
		
		String name = nameField.getText();
		String lastName = lastNameField.getText();
		String height = heigthField.getText();
		double heightN=Double.parseDouble(height);
		String gender = genderSelector.getValue();
		
		String nationatily = nationatilySelector.getValue();
		Person person=new Person(name,lastName, gender, birthDate,heightN,nationatily);
		main.insert(person);
		main.showMenu();
	}

	public void initialize()
	{
		genderSelector.getItems().add("Male");
		genderSelector.getItems().add("Female");

		nationatilySelector.getItems().add("UNITED STATES");
		nationatilySelector.getItems().add("CANADA");
		nationatilySelector.getItems().add("MEXICO");
		nationatilySelector.getItems().add("BELICE");
		nationatilySelector.getItems().add("COSTA RICA");
		nationatilySelector.getItems().add("EL SALVADOR");
		nationatilySelector.getItems().add("GUATEMALA");
		nationatilySelector.getItems().add("HONDURAS");
		nationatilySelector.getItems().add("NICARAGUA");
		nationatilySelector.getItems().add("PANAMA");
		nationatilySelector.getItems().add("ARGENTINA");
		nationatilySelector.getItems().add("BOLIVIA");
		nationatilySelector.getItems().add("BRASIL");
		nationatilySelector.getItems().add("CHILE");
		nationatilySelector.getItems().add("COLOMBIA");
		nationatilySelector.getItems().add("ECUADOR");
		nationatilySelector.getItems().add("PARAGUAY");
		nationatilySelector.getItems().add("SURINAM");
		nationatilySelector.getItems().add("PERU");
		nationatilySelector.getItems().add("TRINIDAD Y TOBAGO");
		nationatilySelector.getItems().add("URUGUAY");
		nationatilySelector.getItems().add("VENEZUELA");
		nationatilySelector.getItems().add("ANTIGUA Y BARBUDA");
		nationatilySelector.getItems().add("BAHAMAS");
		nationatilySelector.getItems().add("BARBADOS");
		nationatilySelector.getItems().add("CUBA");
		nationatilySelector.getItems().add("DOMINICA");
		nationatilySelector.getItems().add("GRANADA");
		nationatilySelector.getItems().add("GUYANA");
		nationatilySelector.getItems().add("HAITI");
		nationatilySelector.getItems().add("JAMAICA");
		nationatilySelector.getItems().add("REPUBLICA DOMINICANA");
		nationatilySelector.getItems().add("SAN CRISTOBAL Y NIEVES");
		nationatilySelector.getItems().add("SAN VICENTE Y LAS GRANADINAS");
		nationatilySelector.getItems().add("SANTA LUCIA");
		nationatilySelector.getItems().add("PUERTO RICO");
	}
	
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public LocalDate getbirthDate() {
		return birthDate;
	}

	public void setbirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}
