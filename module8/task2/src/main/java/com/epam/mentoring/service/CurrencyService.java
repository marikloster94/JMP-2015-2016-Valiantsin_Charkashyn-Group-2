package com.epam.mentoring.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.mentoring.dao.CurrencyDAO;
import com.epam.mentoring.dao.IDAO;
import com.epam.mentoring.exception.AddNewElementException;
import com.epam.mentoring.model.Currency;

public class CurrencyService {
	private static final IDAO dao = new CurrencyDAO();	

	public List<Currency> getCurrencies() throws SQLException {
		synchronized (dao){
			return ((CurrencyDAO)dao).getAll();
		}
	}

	public Currency searchCurrency(String currency) throws SQLException {
		synchronized (dao){
			return ((CurrencyDAO)dao).get(currency);
		}
	}

	public void addCurrency(Currency currency) throws Exception {
		Currency foundCurrency = searchCurrency(currency.getShortName());
		if (foundCurrency == null) {
			currency.setIdCurrency(dao.getLastId());
			dao.create(currency);
		} else {
			 throw new AddNewElementException("You can't add this currency cause it exist");
		}
	}
	
	
	public int getLastId() throws AddNewElementException{
		synchronized (dao){
			try {
				return dao.getLastId();
			} catch (SQLException e) {
				 throw new AddNewElementException("You can't add this currency");
			}
		}
	}

	
}
