package com.epam.mentoring.model;

import java.io.Serializable;
import java.util.List;

public class Bank implements Serializable {

	private static final long serialVersionUID = -6378307730638931705L;

	private String bankName;
	private List<Person> clients;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		return true;
	}

}
