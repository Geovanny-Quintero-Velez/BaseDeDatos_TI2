package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Person;

import java.util.ArrayList;
import java.util.Collections;

import application.Main;



public class SearchController{
	@FXML
	Button edit;
	
	public int maxSearchs;
	public int minSearchs;
	public ArrayList<Person> filtered;
	ArrayList<Person>people;
	Main main;
	public int index;
	
	
	public void configure(int maxSearchs,int minSearchs) {
		this.maxSearchs=maxSearchs;
		this.minSearchs=minSearchs;
		main.maxSearchs=maxSearchs;
		main.minSearchs=minSearchs;
	}
	
	public void options() {
		main.options(this);
	}
	
	
	@FXML
	public ComboBox<String> search;
	
	public void begining(int index,int maxSearchs,int minSearchs) {
		this.index=index;
		search.visibleRowCountProperty().set(maxSearchs);
		this.maxSearchs=maxSearchs;
		this.minSearchs=minSearchs;
	}
	
	@FXML
	public void editElement() {
		
		String var=search.getEditor().getText();
		
		Person toFind=null;
		for(Person person:people) {
			switch(index) {
			case 1:
				if(person.getCode().equals(var)) {
					toFind=person;
					break;
				}
			case 2:
				if(person.getName().equals(var)) {
					toFind=person;
					break;
				}
			case 3:
				if(person.getLastName().equals(var)) {
					toFind=person;
					break;
				}
			case 4:
				if(person.getFullName().equals(var)) {
					toFind=person;
					break;
				}
			}
		}
		
		Person person=main.getPersonInList(index,toFind);
		
		if(person!=null) {
			main.showEditePeople(person);
		}
	}
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	@FXML
	public void actualize() {
		String searchS=search.getEditor().getText();
		filtered= main.getList(index,searchS);
		people=new ArrayList<>();
		ArrayList<String>temp=new ArrayList<>();
		search.getItems().clear();
		String toAdd="";
		for(Person i:filtered) {
			switch(index) {
			case 1:
				toAdd=i.getCode();
				
				break;
			case 2:
				toAdd=i.getName();
				
				break;
			case 3:
				toAdd=i.getLastName();
			
				break;
			case 4:
				toAdd=i.getFullName();
				
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
				people.add(i);
			}
		}
		
		Collections.sort(temp);
		for(int i=0;i<maxSearchs&&i<temp.size();i++) {
			search.getItems().add(temp.get(i));
		}
		
		if(search.getItems().size()<=minSearchs) {
			edit.setVisible(true);
			edit.setDisable(false);
		}else {
			edit.setVisible(false);
			edit.setDisable(true);
		}
		if(search.getItems().size()==0) {
			edit.setVisible(false);
			edit.setDisable(true);
		}
	}
}
