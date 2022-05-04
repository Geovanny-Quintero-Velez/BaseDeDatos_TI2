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
	
	public final int MAX_SEARCHS=100;
	public final int MIN_SEARCHS=20;
	public ArrayList<Person> filtered;
	ArrayList<Person>people;
	Main main;
	public int index;
	@FXML
	public ComboBox<String> search;
	
	public void begining(int index) {
		this.index=index;
		search.visibleRowCountProperty().set(MAX_SEARCHS);
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
		System.out.println(toFind.getName());
		Person person=main.getPersonInList(index,toFind);
		System.out.println(person);
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
				people.add(filtered.get(i));
			}
			
		}
		Collections.sort(temp);
		search.getItems().addAll(temp);
		if(search.getItems().size()<=MIN_SEARCHS) {
			edit.setVisible(true);
			edit.setDisable(false);
		}else {
			edit.setVisible(false);
			edit.setDisable(true);
		}
	}
}
