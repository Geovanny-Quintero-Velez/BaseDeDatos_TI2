package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.Main;
import filters.FilterCode;
import filters.FilterFullName;
import filters.FilterLastName;
import filters.FilterName;

public class SearchController{
	@FXML
	Button edit;
	
	public final int MAX_SEARCHS=100;
	public final int MIN_SEARCHS=20;
	public ArrayList<Person> filtered;
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
		Comparator<Person> comp=null;;
		switch(index) {
		case 1:
			comp=new FilterCode();
			break;
		case 2:
			comp=new FilterName();
			break;
		case 3:
			comp=new FilterLastName();
			break;
		case 4:
			comp=new FilterFullName();
			break;
		}
		String var=search.getEditor().getText();
		Person toSearch=new Person();
		toSearch.setName(var);
		toSearch.setCode(var);
		toSearch.setLastName(var);
		toSearch.setFullName(var);
		Person toFind=null;
		for(Person person:filtered) {
			if(comp.compare(person, toSearch)==0) {
				toFind=person;
				if(toFind!=null) {
					break;
				}
			}
		}
		System.out.println();
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
		ArrayList<Person>people=new ArrayList<>();
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
