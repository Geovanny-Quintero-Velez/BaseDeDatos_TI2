package model;

import java.util.Date;

public class Person {

	private String name;
	private String lastName;
	private boolean gender;
	private Date birthDate;
	private double height;
	private String nationality;
	private Object fotografia;
	
	public Person() {
		
	}
	
	public Person(String name, String lastName, boolean gender, Date birthDate, double height, String nationality) {
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.height = height;
		this.nationality = nationality;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Object getFotografia() {
		return fotografia;
	}

	public void setFotografia(Object fotografia) {
		this.fotografia = fotografia;
	}
	
	
}
