package com.epam.module.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

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
	@Cascade({CascadeType.SAVE_UPDATE})
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
