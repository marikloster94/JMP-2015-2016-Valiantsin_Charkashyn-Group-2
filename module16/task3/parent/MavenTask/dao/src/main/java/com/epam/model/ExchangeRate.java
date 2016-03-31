package com.epam.model;

import java.io.Serializable;

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
public class ExchangeRate implements Serializable {

	private static final long serialVersionUID = -8251790741274076159L;
	private int id;
	private String from;
	private String to;
	private double rate;
	private String exchangeDay;
	@Id
	@Column(name="rateId")
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="from")
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	@Column(name="to")
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	@Column(name="rate")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exchangeDay == null) ? 0 : exchangeDay.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + id;
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		ExchangeRate other = (ExchangeRate) obj;
		if (exchangeDay == null) {
			if (other.exchangeDay != null)
				return false;
		} else if (!exchangeDay.equals(other.exchangeDay))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (id != other.id)
			return false;
		if (rate != other.rate)
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

}
