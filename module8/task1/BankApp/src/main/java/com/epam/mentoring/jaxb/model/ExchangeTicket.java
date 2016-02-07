package com.epam.mentoring.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.mentoring.model.Currency;
import com.epam.mentoring.model.Person;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeTicket {
	
	private int id;
	private Person client;
	private Currency fromCurr;
	private Currency toCurr;
	private double toCurrAmount;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}

	public Currency getFromCurr() {
		return fromCurr;
	}

	public void setFromCurr(Currency fromCurr) {
		this.fromCurr = fromCurr;
	}

	public Currency getToCurr() {
		return toCurr;
	}

	public void setToCurr(Currency toCurr) {
		this.toCurr = toCurr;
	}

	public double getToCurrAmount() {
		return toCurrAmount;
	}

	public void setToCurrAmount(double toCurrAmount) {
		this.toCurrAmount = toCurrAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
