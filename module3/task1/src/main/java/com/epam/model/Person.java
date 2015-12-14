package com.epam.model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -141206091697454788L;
	private String name;
	private String surname;
	private int age;
	
	
	public Person() {

	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name +" " + surname + " is " + age +" years old";
	}
	
	

}
