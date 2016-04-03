package com.epam.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exchangeRate")
@Access(AccessType.PROPERTY)
public class ExchangeRate  {

	private int idRate;
	private String from;
	private String to;
	private double rate;
	private String exchangeDay;
	@Id
	@Column(name="rateId")
	@GeneratedValue
	public int getId() {
		return idRate;
	}

	public void setId(int id) {
		this.idRate = id;
	}
	@Column(name="fromCurr")
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	@Column(name="toCurr")
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	@Column(name="currRate")
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	@Column(name="exchangeDay")
	public String getExchangeDay() {
		return exchangeDay;
	}

	public void setExchangeDay(String exchangeDay) {
		this.exchangeDay = exchangeDay;
	}

}
