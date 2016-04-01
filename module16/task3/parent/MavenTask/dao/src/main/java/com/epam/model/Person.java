package com.epam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="person")
@Access(AccessType.PROPERTY)
public class Person {

	private int idPerson;
	private String name;
	private String surname;
	private String dateOfBirth;
	private String passportNumber;
	private List<Account> accounts = new ArrayList<Account>();
	private List<ExchangeTicket> tickets = new ArrayList<ExchangeTicket>();

	@Id
	@Column(name="idPerson")
	@GeneratedValue
	public int getId() {
		return idPerson;
	}
	public void setId(int id) {
		this.idPerson = id;
	}
	public Person(){
		
	}
	public Person(String name, String surname, String date){
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = date;
		
	}
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Column(name="dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Column(name="passportNumber")
	@PrimaryKeyJoinColumn
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "person")
	@PrimaryKeyJoinColumn
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
	@PrimaryKeyJoinColumn
	public List<ExchangeTicket> getTickets() {
		return tickets;
	}
	public void setTickets(List<ExchangeTicket> tickets) {
		this.tickets = tickets;
	}

}
