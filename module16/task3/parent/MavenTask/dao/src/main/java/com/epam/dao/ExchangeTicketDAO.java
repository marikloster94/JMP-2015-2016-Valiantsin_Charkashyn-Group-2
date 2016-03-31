package com.epam.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.exception.HsqlDBException;
import com.epam.model.ExchangeTicket;

@Component
public class ExchangeTicketDAO {

	@PersistenceContext
	private EntityManager em;

	public ExchangeTicket create(ExchangeTicket ticket) throws Exception {
		if (ticket == null) {
			throw new IllegalArgumentException("ExchangeTicket can not be null");
		}
		try {
			ticket = em.merge(ticket);
		} catch (Exception ex) {
			throw new HsqlDBException("Can not add ticket to db", ex);
		}
		return ticket;

	}

	public ExchangeTicket get(int id) throws SQLException {
		return em.find(ExchangeTicket.class, id);
	}

	public ExchangeTicket update(ExchangeTicket ticket) throws Exception {
		if (ticket == null || ticket.getId() == 0) {
			throw new IllegalArgumentException("ExchangeTicket can not be null");
		}
		try {
			ticket = em.merge(ticket);
		} catch (Exception ex) {
			throw new HsqlDBException("Can not update ticket in db", ex);
		}
		return ticket;
	}


	
	public List<ExchangeTicket> getAll() throws SQLException{
		return em.createQuery("SELECT t FROM ExchangeTicket t").getResultList();
	}

	public List<ExchangeTicket> getAll(String status) throws SQLException{
		return em.createQuery("SELECT t FROM ExchangeTicket t WHERE t.status =:status", ExchangeTicket.class).setParameter("status", status).getResultList();
	}
}
