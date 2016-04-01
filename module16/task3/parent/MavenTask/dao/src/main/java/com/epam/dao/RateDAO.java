package com.epam.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.exception.HsqlDBException;
import com.epam.model.ExchangeRate;

@Component
public class RateDAO{
	
	@PersistenceContext
	EntityManager em;
	
	public ExchangeRate create(ExchangeRate rate) throws HsqlDBException {
		if (rate == null) {
			throw new IllegalArgumentException("ExchangeRate can not be null");
		}
		return em.merge(rate);
	}

	public ExchangeRate get(int id) throws SQLException {
		return em.find(ExchangeRate.class, id);
	}
	
	public List<ExchangeRate> getAll() throws SQLException{
		return em.createQuery("SELECT r FRON ExchangeRate r").getResultList();
	}

}
