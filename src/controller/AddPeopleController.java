package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.time.LocalDate;

import application.Main;
import javafx.event.ActionEvent;

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
		
		int gender = genderSelector.getSelectionModel().getSelectedIndex();
		int nationatily = nationatilySelector.getSelectionModel().getSelectedIndex();

		main.showMenu();
	}

	public void initialize()
	{
		genderSelector.getItems().add("Male");
		genderSelector.getItems().add("Female");

		nationatilySelector.getItems().add("Asia");
		nationatilySelector.getItems().add("Africa");
		nationatilySelector.getItems().add("Europe");
		nationatilySelector.getItems().add("North America");
		nationatilySelector.getItems().add("South America");
		nationatilySelector.getItems().add("Australia/Oceania");
		nationatilySelector.getItems().add("Antartica");
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
