package com.epam.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
@Access(AccessType.PROPERTY)
public class Account {

	private int idAccount;
	private String description;
	private String startDate;
	private String endDate;
	private double value;
	private Currency curr = new Currency();
	private Person man =  new Person();

	@Id
	@GeneratedValue
	public int getId() {
		return idAccount;
	}

	public void setId(int id) {
		this.idAccount = id;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="startDate")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startdDate) {
		this.startDate = startdDate;
	}
	@Column(name="endDate")
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idCurrency", nullable = false)
	public Currency getCurr() {
		return curr;
	}

	public void setCurr(Currency curr) {
		this.curr = curr;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idPerson", nullable = false)
	public Person getMan() {
		return man;
	}

	public void setMan(Person person) {
		this.man = person;
	}
	@Column(name="value")
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
