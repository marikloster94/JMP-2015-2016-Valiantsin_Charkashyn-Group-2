package com.epam.mentoring.jaxb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.mentoring.model.ExchangeTicket;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeTickets {
	
	@XmlElement(name = "ticket")
	private List<ExchangeTicket> tickets;

	public List<ExchangeTicket> getTickets() {
		return tickets;
	}

	public void setTickets(List<ExchangeTicket> tickets) {
		this.tickets = tickets;
	}

}
