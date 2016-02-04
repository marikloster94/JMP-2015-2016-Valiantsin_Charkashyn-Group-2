package com.epam.mentoring.jaxb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.mentoring.model.ExchangeRate;

@XmlRootElement(name = "rates")
@XmlAccessorType (XmlAccessType.FIELD)
public class ExchangeRates {

	@XmlElement(name = "rate")
	private List<ExchangeRate> rates;

	public List<ExchangeRate> getRates() {
		return rates;
	}

	public void setRates(List<ExchangeRate> rates) {
		this.rates = rates;
	}
}
