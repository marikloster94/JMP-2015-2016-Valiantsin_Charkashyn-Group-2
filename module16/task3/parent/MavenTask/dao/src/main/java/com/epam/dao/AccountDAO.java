package com.epam.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.exception.HsqlDBException;
import com.epam.model.Account;


@Component
public class AccountDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Account create(Account acc) throws Exception {
		if (acc == null) {
			throw new IllegalArgumentException("Account can not be null");
		}
		try {
			acc = em.merge(acc);
		} catch (Exception ex) {
			throw new HsqlDBException("Cannot add rate to db", ex);
		}
		return acc;
	}

	public Account get(int id) throws Exception {
		Account acc = null;
		try {
			acc = em.find(Account.class, id);
		} catch (Exception ex) {
			throw new HsqlDBException("Cannot load currencies from db", ex);
		}
		return acc;
	}

	public Account update(Account acc) throws Exception {
		if (acc == null) {
			throw new IllegalArgumentException("Person can not be null");
		}
		try {
			acc = em.merge(acc);
		} catch (Exception ex) {
			throw new HsqlDBException("Cannot add person todb", ex);
		}
		return acc;
	}
	
	public List<Account> getAll() throws Exception{
		return em.createQuery("SELECT a FROM Account a").getResultList();
	}

}
