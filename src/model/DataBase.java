package model;

import java.io.Serializable;
import java.util.ArrayList;
import filters.FilterCode;
import filters.FilterFullName;
import filters.FilterLastName;
import filters.FilterName;
import structures.ArbolBinario;

public class DataBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	ArbolBinario<Person,FilterCode> filterByCode;
	ArbolBinario<Person,FilterName> filterByName;
	ArbolBinario<Person,FilterLastName> filterByLastName;
	ArbolBinario<Person,FilterFullName> filterByFullName;
	
	public DataBase() {
		filterByCode=new ArbolBinario<>(new FilterCode());
		filterByName=new ArbolBinario<>(new FilterName());
		filterByLastName=new ArbolBinario<>(new FilterLastName());
		filterByFullName=new ArbolBinario<>(new FilterFullName());
	}
	
	
	
	public ArbolBinario<Person, FilterCode> getFilterByCode() {
		return filterByCode;
	}



	public void setFilterByCode(ArbolBinario<Person, FilterCode> filterByCode) {
		this.filterByCode = filterByCode;
	}



	public ArbolBinario<Person, FilterName> getFilterByName() {
		return filterByName;
	}



	public void setFilterByName(ArbolBinario<Person, FilterName> filterByName) {
		this.filterByName = filterByName;
	}



	public ArbolBinario<Person, FilterLastName> getFilterByLastName() {
		return filterByLastName;
	}



	public void setFilterByLastName(ArbolBinario<Person, FilterLastName> filterByLastName) {
		this.filterByLastName = filterByLastName;
	}



	public ArbolBinario<Person, FilterFullName> getFilterByFullName() {
		return filterByFullName;
	}



	public void setFilterByFullName(ArbolBinario<Person, FilterFullName> filterByFullName) {
		this.filterByFullName = filterByFullName;
	}



	public ArrayList<Person> getFilteredList(int index, String param){
		
		switch(index) {
		case 1:
			return getFilterCode(param);
		case 2:
			
			return getFilterName(param);
		case 3:
			return getFilterLastName(param);
		case 4:
			return getFilterFullname(param);
		}
		return null;
	}
	
	public ArrayList<Person> getFilterCode(String code){
		
		Person mediator=new Person();
		mediator.setCode(code);
		return filterByCode.getGreater(mediator);
	}
	
	public ArrayList<Person> getFilterName(String name){
		Person mediator=new Person();
		mediator.setName(name);
		return filterByName.getGreater(mediator);
	}
	
	public ArrayList<Person> getFilterLastName(String code){
		Person mediator=new Person();
		mediator.setCode(code);
		return filterByLastName.getGreater(mediator);
	}
	
	public ArrayList<Person> getFilterFullname(String name){
		Person mediator=new Person();
		mediator.setFullName(name);
		return filterByFullName.getGreater(mediator);
	}
	
	public void run() {
		
		
	}


	
	
}
