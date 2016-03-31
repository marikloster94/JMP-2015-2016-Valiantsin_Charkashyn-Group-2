package com.epam.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.exception.HsqlDBException;
import com.epam.model.Currency;
import com.epam.model.ExchangeRate;

@Component
public class RateDAO{
	
	@PersistenceContext
	EntityManager em;
	
	public ExchangeRate create(ExchangeRate rate) throws HsqlDBException {
		if (rate == null) {
			throw new IllegalArgumentException("ExchangeRate can not be null");
		}
		rate = em.merge(rate);
		return rate;
	}

	public ExchangeRate get(int id) throws SQLException {
		return em.find(ExchangeRate.class, id);
	}
	
	public List<ExchangeRate> getAll() throws SQLException{
		return em.createQuery("SELECT r FRON ExchangeRate r").getResultList();
	}

}
