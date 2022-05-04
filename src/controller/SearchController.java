package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import model.Person;

import java.util.ArrayList;
import java.util.Collections;

import application.Main;
import filters.FilterName;

public class SearchController{
	
	public final int MAX_SEARCHS=100;
	
	public ArrayList<Person> filtered;
	Main main;
	public int index;
	@FXML
	public ComboBox<String> search;
	
	public void begining(int index) {
		this.index=index;
		search.visibleRowCountProperty().set(MAX_SEARCHS);
	}
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	@FXML
	public void actualize() {
		String searchS=search.getEditor().getText();
		
		filtered= main.getList(index,searchS);
		ArrayList<String>temp=new ArrayList<>();
		search.getItems().clear();
		String toAdd="";
		for(int i=0;i<filtered.size();i++) {
			switch(index) {
			case 1:
				toAdd=filtered.get(i).getCode();
				break;
			case 2:
				toAdd=filtered.get(i).getName();
				break;
			case 3:
				toAdd=filtered.get(i).getLastName();
				break;
			case 4:
				toAdd=filtered.get(i).getFullName();
				break;
			}
			boolean flag=false;
			for(int j=0;j<searchS.length()&&j<toAdd.length();j++) {
				if((toAdd.charAt(j)==searchS.charAt(j))) {
					flag=true;
				}else {
					flag=false;
					break;
				}
			}
			if(searchS.length()>toAdd.length()) {
				flag=false;
			}
			if(flag) {
				temp.add(toAdd);
			}
			
		}
		Collections.sort(temp);
		search.getItems().addAll(temp);
		
	}
}
