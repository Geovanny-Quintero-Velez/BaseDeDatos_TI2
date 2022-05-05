package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;


import application.Main;

public class GenerateDatController {
	
	private Main main;
	
	@FXML
	private Button generateDataButton;
	
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
			Stage stage = (Stage) this.generateDataButton.getScene().getWindow();
		    stage.close();
			
			String cantOfRegisters = numOfRegistersField.getText();
			main.showProgressBar(Integer.parseInt(cantOfRegisters));
		}
	}
	
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
}
