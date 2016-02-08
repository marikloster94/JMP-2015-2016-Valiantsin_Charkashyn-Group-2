package com.epam.mentoring.service;

import java.util.ArrayList;
import java.util.List;

import com.epam.mentoring.dao.ExchangeTicketDAO;
import com.epam.mentoring.dao.IDAO;
import com.epam.mentoring.exception.HsqlDBException;
import com.epam.mentoring.model.ExchangeTicket;

public class ExchangeTicketService {
	
	private static final IDAO dao = new ExchangeTicketDAO();	
	
	public List<ExchangeTicket> getExchangeTickets() {
		return null;
//		return ((ExchangeTickets) FileUtil.loadFromFile(client)).getTickets();
	}
	
	public void addTicket(ExchangeTicket ticket) throws HsqlDBException{
		dao.create(ticket);
		
	}
	
	public List<ExchangeTicket> getExchangeTickets(String status){
		List<ExchangeTicket> tickets = getExchangeTickets();
		List<ExchangeTicket> newTickets = new ArrayList<ExchangeTicket>();
		for(ExchangeTicket ticket : tickets){
			if(ticket.getStatus().equals(status)){
				newTickets.add(ticket);
			}
		}
		return newTickets;
	}
	
	public void updateExchangeTicket(ExchangeTicket ticket) throws HsqlDBException{
		ExchangeTicket oldTicket = searchExchangeTicket(ticket.getId());
		if(oldTicket != null){
			dao.update(ticket);
		}
		
	}
	
	public ExchangeTicket searchExchangeTicket(int id){
		List<ExchangeTicket> tickets = getExchangeTickets();
		for(ExchangeTicket ticket : tickets){
			if(ticket.getId() == id){
				return ticket;
			}
		}
		return null;
	}
}
