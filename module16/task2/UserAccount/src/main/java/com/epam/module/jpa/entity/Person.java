package com.epam.module.jpa.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 26.03.16.
 */
@Entity
@Table(name = "person")
public class Person {

	private int personID;
	private String name;
	private String surname;
	private String passNumber;
	private int age;
	private List<Account> accounts = new ArrayList<Account>();

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "passNumber")
	public String getPassNumber() {
		return passNumber;
	}

	public void setPassNumber(String passNumber) {
		this.passNumber = passNumber;
	}

	@Column(name = "age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
	@PrimaryKeyJoinColumn
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Id
	@Column(name = "personId")
	@GeneratedValue
	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}
}
