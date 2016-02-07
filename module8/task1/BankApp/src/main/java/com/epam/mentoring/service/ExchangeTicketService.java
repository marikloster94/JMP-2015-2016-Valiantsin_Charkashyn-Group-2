package com.epam.mentoring.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.epam.mentoring.file.ClientThread;
import com.epam.mentoring.file.FileGenerator;
import com.epam.mentoring.jaxb.model.ExchangeTickets;
import com.epam.mentoring.model.ExchangeTicket;
import com.epam.mentoring.util.FileUtil;

public class ExchangeTicketService {
	private final ClientThread client = new ClientThread("exchangeTicket.xml");
	private final ExchangeTickets ticks = new ExchangeTickets();
	
	public ExchangeTicketService(){
		client.setClassName(ExchangeTickets.class);
	}
	
	public List<ExchangeTicket> getExchangeTickets() {
		return ((ExchangeTickets) FileUtil.loadFromFile(client)).getTickets();
	}
	
	public void addTicket(ExchangeTicket ticket){
		List<ExchangeTicket> tickets = getExchangeTickets();
		tickets.add(ticket);
		updateTickets(tickets);
		
	}
	
	public void updateTickets(List<ExchangeTicket> tickets){
		ExchangeTickets ticks = new ExchangeTickets();
		ticks.setTickets(tickets);
		FileGenerator generator = new FileGenerator("exchangeTicket.xml", ticks);
		generator.setClassName(ExchangeTickets.class);
		FileUtil.writeToFile(generator);
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
	
	public int getLastId(){
		List<ExchangeTicket> tickets = getExchangeTickets();
		if(tickets == null){
			return 1;
		}
		Collections.sort(tickets, new Comparator<ExchangeTicket>() {
			@Override
			public int compare(ExchangeTicket o1, ExchangeTicket o2) {
				return o2.getId() - o1.getId();
			}
		});
		return tickets.get(0).getId()+1;
	}
	
	public void updateExchangeTicket(ExchangeTicket ticket){
		List<ExchangeTicket> tickets = getExchangeTickets();
		ExchangeTicket oldTicket = searchExchangeTicket(ticket.getId());
		if(oldTicket != null){
			tickets.remove(oldTicket);
		}
		tickets.add(ticket);
		ticks.setTickets(tickets);
		FileGenerator generator = new FileGenerator("exchangeTicket.xml", ticks);
		generator.setClassName(ExchangeTickets.class);
		FileUtil.writeToFile(generator);
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
