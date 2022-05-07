package controller;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class configureSearchController {
	public int maxSearchs;
	public int minSearchs;
	Main main;
	SearchController controller;
	@FXML
	TextField maxSearchsText;
	@FXML
	TextField minSearchsText;
	public void configure(int maxSearchs,int minSearchs,SearchController controller,Main main) {
		this.maxSearchs=maxSearchs;
		this.minSearchs=minSearchs;
		this.controller=controller;
		this.main=main;
		minSearchsText.setText(minSearchs+"");
		maxSearchsText.setText(maxSearchs+"");
	}
	
	
	
	@FXML
	public void actualize() {
		controller.configure(Integer.parseInt(maxSearchsText.getText()), Integer.parseInt(minSearchsText.getText()));
		main.closeConfigureSearch();
	}
	
	
	
}
