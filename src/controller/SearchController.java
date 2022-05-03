package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.Person;

import java.util.ArrayList;

import application.Main;

public class SearchController<E>{
	
	public ArrayList<Person> filtered;
	Main main;
	public int index;
	@FXML
	public ComboBox<Person> search;
	
	@FXML
	MenuButton filter;
	
	@FXML
	MenuItem name;
	@FXML
	MenuItem code;
	@FXML
	MenuItem lastName;
	@FXML
	MenuItem fullName;
	@FXML
	public void byCode() {
		index=1;
	}
	@FXML
	public void byName() {
		index=2;
	}
	@FXML
	public void byLastName() {
		index=3;
	}
	
	@FXML
	public void byFullName() {
		index=4;
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	public void actualize() {
		if(filter.isPressed()) {
			filtered=(ArrayList<Person>) main.getArrayList(index);
			search.getItems().clear();
			search.getItems().addAll(filtered);
		}
	}
}
