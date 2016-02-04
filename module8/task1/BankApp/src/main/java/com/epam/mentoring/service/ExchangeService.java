package com.epam.mentoring.service;

import java.util.List;

import com.epam.mentoring.file.ClientThread;
import com.epam.mentoring.jaxb.model.ExchangeRates;
import com.epam.mentoring.model.Account;
import com.epam.mentoring.model.ExchangeRate;
import com.epam.mentoring.model.ExchangeTicket;
import com.epam.mentoring.util.FileUtil;

public class ExchangeService {
	private final ClientThread client = new ClientThread("exchange.xml");

	public ExchangeService() {
		client.setClassName(ExchangeRates.class);
	}

	public List<ExchangeRate> getExchangeRates() {
		return ((ExchangeRates) FileUtil.loadFromFile(client)).getRates();
	}

	public ExchangeRate searchExchange(String date, String currencyFrom, String currencyTo) {
		List<ExchangeRate> rates = getExchangeRates();
		for (ExchangeRate rate : rates) {
			if(rate.getExchangeDay().equals(date) && rate.getFrom().getShortName().equalsIgnoreCase(currencyFrom) &&
					rate.getTo().getShortName().equalsIgnoreCase(currencyTo)){
				return rate;
			}
		}
		return null;
	}
	
	public void convert(ExchangeRate rate, List<Account> accounts, ExchangeTicket order){
		
	}
	
	public double calculate(ExchangeRate rate, double expectedValue ){
		return 0;
	}
}
