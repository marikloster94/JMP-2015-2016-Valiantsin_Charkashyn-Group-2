package com.epam.mentoring.model;

import java.io.Serializable;

public class ExchangeRate implements Serializable {

	private static final long serialVersionUID = -8251790741274076159L;
	private Currency from;
	private Currency to;
	private double rate;
	private String exchangeDay;

	public Currency getFrom() {
		return from;
	}

	public void setFrom(Currency from) {
		this.from = from;
	}

	public Currency getTo() {
		return to;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getExchangeDay() {
		return exchangeDay;
	}

	public void setExchangeDay(String exchangeDay) {
		this.exchangeDay = exchangeDay;
	}

}
