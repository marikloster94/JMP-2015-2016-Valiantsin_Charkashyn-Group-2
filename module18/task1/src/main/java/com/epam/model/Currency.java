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
@Table(name="currency")
@Access(AccessType.PROPERTY)
public class Currency {

	private int idCurrency;
	private String shortName;
	private List<Account> accounts = new ArrayList<Account>();
	
	@Id
	@Column(name="idCurrency")
	@GeneratedValue
	public int getIdCurrency() {
		return idCurrency;
	}
	public void setIdCurrency(int idCurrency) {
		this.idCurrency = idCurrency;
	}
	@Column(name="shortName")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "curr")
	@PrimaryKeyJoinColumn
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
}
