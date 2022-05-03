package model;

import java.time.LocalDate;

public class Person {
	
	public static final String MALE = "MALE";
	public static final String FEMALE = "FEMALE";

	private String name;
	private String lastName;
	private String fullName;
	private String gender;
	private LocalDate birthDate;
	private double height;
	private String nationality;
	private Object fotografia;
	private String code;
	
	public Person() {
		
	}
	
	
	public Person(String name, String lastName, String gender, LocalDate birthDate, double height, String nationality) {
		this.name = name;
		this.lastName = lastName;
		fullName=name+" "+lastName;
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

	public String isGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
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

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code=code;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
