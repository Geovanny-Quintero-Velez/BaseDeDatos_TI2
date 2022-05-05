package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProgressBarController implements Initializable
{
	private Main main;
	
	private int total;
	
	private double progress;
	
	@FXML
	private ProgressBar generateDataProgressBar;
	
	@FXML
	private Label usedTimeLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		double onePercent = (1/total);

		
		generateDataProgressBar.setStyle("-fx-accent: #800080");
		
	}	

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	

	
	
	

}
