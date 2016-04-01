package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.dao.CurrencyDAO;
import com.epam.exception.AddNewElementException;
import com.epam.model.Currency;

@Repository
public class CurrencyService {
	@Autowired
	private CurrencyDAO dao;

	public List<Currency> getCurrencies() throws Exception {
		return dao.getAll();
	}

	public Currency searchCurrency(String currency) throws Exception {
		return dao.get(currency);
	}

	public void addCurrency(Currency currency) throws Exception {
		Currency foundCurrency = searchCurrency(currency.getShortName());
		if (foundCurrency == null) {
			dao.create(currency);
		} else {
			throw new AddNewElementException("You can't add existing currency");
		}
	}
}
