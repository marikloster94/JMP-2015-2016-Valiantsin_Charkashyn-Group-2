package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.dao.ExchangeTicketDAO;
import com.epam.exception.ElementNotFoundException;
import com.epam.model.ExchangeTicket;

@Repository
public class ExchangeTicketService {

	@Autowired
	private ExchangeTicketDAO dao ;
	
	public List<ExchangeTicket> getExchangeTickets() throws Exception {
		return dao.getAll();
	}
	
	@Transactional
	public void addTicket(ExchangeTicket ticket) throws Exception {
		dao.create(ticket);
	}

	public List<ExchangeTicket> getExchangeTickets(String status) throws Exception {
		return dao.getAll(status);
	}
	
	@Transactional
	public void updateExchangeTicket(ExchangeTicket ticket) throws Exception, ElementNotFoundException {
		ExchangeTicket oldTicket = searchExchangeTicket(ticket.getId());
		if (oldTicket != null) {
			dao.update(ticket);
		} else {
			throw new ElementNotFoundException(	"Can't update ticket cause ticket didn't found");
		}

	}

	public ExchangeTicket searchExchangeTicket(int id) throws SQLException {
		return (ExchangeTicket) dao.get(id);
	}
}
