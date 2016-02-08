package com.epam.mentoring.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.mentoring.dao.ExchangeTicketDAO;
import com.epam.mentoring.dao.IDAO;
import com.epam.mentoring.exception.ElementNotFoundException;
import com.epam.mentoring.exception.HsqlDBException;
import com.epam.mentoring.model.ExchangeTicket;

public class ExchangeTicketService {
	
	private static final IDAO dao = new ExchangeTicketDAO();	
	
	public List<ExchangeTicket> getExchangeTickets() throws SQLException {
		synchronized (dao){
			return ((ExchangeTicketDAO)dao).getAll();
		}
	}
	
	public void addTicket(ExchangeTicket ticket) throws HsqlDBException{
		synchronized (dao){
			dao.create(ticket);
		}
		
	}
	
	public List<ExchangeTicket> getExchangeTickets(String status) throws SQLException{
		synchronized (dao){
			return ((ExchangeTicketDAO)dao).getAll(status);
		}
	}
	
	public  void updateExchangeTicket(ExchangeTicket ticket) throws SQLException, ElementNotFoundException{
		ExchangeTicket oldTicket = searchExchangeTicket(ticket.getId());
		if(oldTicket != null){
			dao.update(ticket);
		}else{
			throw new ElementNotFoundException("Can't update ticket cause ticket does not found");
		}
		
	}
	
	public  ExchangeTicket searchExchangeTicket(int id) throws SQLException{
		synchronized (dao){
			return (ExchangeTicket)dao.get(id);
		}
	}
}
