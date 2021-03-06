package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.dao.ExchangeTicketDAO;
import com.epam.exception.ElementNotFoundException;
import com.epam.exception.HsqlDBException;
import com.epam.model.ExchangeTicket;

public class ExchangeTicketService {

	private static final ExchangeTicketDAO dao = new ExchangeTicketDAO();

	public List<ExchangeTicket> getExchangeTickets() throws SQLException {
		return dao.getAll();
	}

	public void addTicket(ExchangeTicket ticket) throws HsqlDBException {
		dao.create(ticket);
	}

	public List<ExchangeTicket> getExchangeTickets(String status)
			throws SQLException {
		return dao.getAll(status);
	}

	public void updateExchangeTicket(ExchangeTicket ticket)
			throws SQLException, ElementNotFoundException {
		ExchangeTicket oldTicket = searchExchangeTicket(ticket.getId());
		if (oldTicket != null) {
			dao.update(ticket);
		} else {
			throw new ElementNotFoundException(
					"Can't update ticket cause ticket does not found");
		}

	}

	public ExchangeTicket searchExchangeTicket(int id) throws SQLException {
		return (ExchangeTicket) dao.get(id);
	}
}
