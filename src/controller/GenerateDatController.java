package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;

public class GenerateDatController {
	
	private Main main;
	
	@FXML
	private Button generateDataButton;
	@FXML
	private Button exitButton;
	@FXML
	private TextField numOfRegistersField;

	@FXML
	public void generateData()
	{
		if(numOfRegistersField.getText().equals(""))
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Nothing in the field");
			alert.setContentText("Please write a number.");				
			
			alert.showAndWait();
		}
		else
		{	
			String cantOfRegisters = numOfRegistersField.getText();
			try {
				main.generatePeople();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main.showMenu();
		}
	}
	
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
}
