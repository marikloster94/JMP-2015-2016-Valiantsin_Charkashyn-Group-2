package com.epam.mentoring.jaxb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.mentoring.model.Account;
import com.epam.mentoring.model.Person;

@XmlRootElement(name = "bank")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bank {
	private String bankName;
	
	@XmlElement(name = "person")
	private List<Person> clients;
	
	@XmlElement(name = "account")
	private List<Account> bankAccounts;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public List<Person> getClients() {
		return clients;
	}

	public void setClients(List<Person> clients) {
		this.clients = clients;
	}

	public List<Account> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<Account> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
}
