package com.epam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.exception.HsqlDBException;
import com.epam.model.Currency;

@Component
public class CurrencyDAO  {
	
	@PersistenceContext
	private EntityManager em;
	
	public Currency create(Currency currency) throws Exception {
		if (currency == null) {
			throw new IllegalArgumentException("Currency can not be null");
		}
		try {
			currency = em.merge(currency);
		} catch (Exception ex) {
			throw new HsqlDBException("Can not add currency to db", ex);
		}
		return currency;
	}

	public Currency get(int id) throws Exception {
		Currency currency = null;
		try {
			currency = em.find(Currency.class, id);
		} catch (Exception ex) {
			throw new HsqlDBException("Can not load currency from db", ex);
		}
		return currency;
	}
	
	public List<Currency> getAll() throws Exception{
		return em.createQuery("SELECT c FROM Currency c").getResultList();
	}
	
	public Currency get(String currencyName) throws Exception{
		if (currencyName == null) {
			return null;
		}
		return (Currency) em.createQuery("SELECT c FROM Currency c WHERE c.shortName =: shortName", Currency.class).setParameter("shortName", currencyName).getSingleResult();
	}

}
