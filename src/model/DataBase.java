package model;

import java.util.ArrayList;
import java.util.Comparator;

import filters.FilterCode;
import filters.FilterFullName;
import filters.FilterLastName;
import filters.FilterName;
import structures.ArbolBinario;

public class DataBase {
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
