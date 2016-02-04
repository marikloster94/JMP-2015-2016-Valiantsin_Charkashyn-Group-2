package com.epam.mentoring.jaxb.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.mentoring.model.Currency;

@XmlRootElement(name = "currencies")
@XmlAccessorType (XmlAccessType.FIELD)
public class Currencies {
	
	@XmlElement(name = "curr")
    private List<Currency> currencies = new ArrayList<Currency>();
 
    public List<Currency> getCurrencies() {
        return currencies;
    }
 
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
