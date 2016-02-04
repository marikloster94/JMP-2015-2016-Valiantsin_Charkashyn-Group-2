package com.epam.mentoring.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.mentoring.model.Currency;

@XmlRootElement(name = "exchange")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRate {
	
	@XmlElement(name = "from")
	private Currency from;
	@XmlElement(name = "to")
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
