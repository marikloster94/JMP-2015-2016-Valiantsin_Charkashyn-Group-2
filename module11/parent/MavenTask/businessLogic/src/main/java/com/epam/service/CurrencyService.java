package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.dao.CurrencyDAO;
import com.epam.exception.AddNewElementException;
import com.epam.model.Currency;

public class CurrencyService {
	private static final CurrencyDAO dao = new CurrencyDAO();

	public List<Currency> getCurrencies() throws SQLException {
		return dao.getAll();
	}

	public Currency searchCurrency(String currency) throws SQLException {
		return dao.get(currency);
	}

	public void addCurrency(Currency currency) throws Exception {
		Currency foundCurrency = searchCurrency(currency.getShortName());
		if (foundCurrency == null) {
			currency.setIdCurrency(dao.getLastId());
			dao.create(currency);
		} else {
			throw new AddNewElementException(
					"You can't add this currency cause it exist");
		}
	}

	public int getLastId() throws AddNewElementException {
		try {
			return dao.getLastId();
		} catch (SQLException e) {
			throw new AddNewElementException("You can't add this currency");
		}
	}
}
