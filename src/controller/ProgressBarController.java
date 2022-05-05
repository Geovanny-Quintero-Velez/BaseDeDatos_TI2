package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProgressBarController implements Initializable
{
	private Main main;
	
	@FXML
	private ProgressBar generateDataProgressBar;
	
	@FXML
	private Label initialTimeLabel;
	
	@FXML
	private Button continueButton;
	
	@FXML
	private Label finalTimeLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		generateDataProgressBar.setStyle("-fx-accent: #800080");
	}
	
	public void incrementTenPercent(double percent)
	{
		generateDataProgressBar.setProgress(percent);
	}

	public void setInitialTime(String initialTime)
	{
		initialTimeLabel.setText(initialTime);
	}
	
	public void setFinalTime(String finalTime)
	{
		finalTimeLabel.setText(finalTime);
	}
	
	public void complete()
	{
		generateDataProgressBar.setProgress(1);
	}
	
	public Main getMain() {
		return main;
	}
	
	public void continueApp()
	{
		main.showMenu();
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
