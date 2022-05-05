package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Timer;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProgressBarController
{
	private Timer time;
	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;
	
	private Main main;
	
	@FXML
	private ProgressBar generateDataProgressBar;
	
	@FXML
	private Label usedTimeLabel;
	
	@FXML
	private Button uwu;
	
	public void actualizeTimer()
	{
		String timetext = (hours <= 9 ? "0" : "")+hours+":"+(minutes <= 9 ? "0" : "")+minutes+":"+(seconds <= 9 ? "0" : "")+seconds;
		usedTimeLabel.setText(timetext);
	}
	
	public ActionListener actions = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e)
		{
			seconds++;
			if(seconds == 60)
			{
				minutes++;
				seconds = 0;
			}
			if(minutes == 60)
			{
				hours++;
				minutes = 0;
			}
			
			actualizeTimer();
		}
	};
	
	public ProgressBarController()
	{
		time = new Timer(10, actions);
	}
	
	public void s()
	{
		time.start();
	}
	

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	
	
	

}
