package com.epam.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.exception.ElementNotFoundException;
import com.epam.exception.ExchangeException;
import com.epam.model.Account;
import com.epam.model.ExchangeRate;
import com.epam.model.ExchangeTicket;
import com.epam.model.Person;
import com.epam.service.AccountService;
import com.epam.service.ExchangeService;
import com.epam.service.ExchangeTicketService;
import com.epam.service.PersonService;

public class DoExchangeCommand implements Command {

	private static final Logger log = Logger.getLogger(DoExchangeCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		ExchangeTicketService service = new ExchangeTicketService();
		String result = "/exchangeTicket.jsp";
		List<ExchangeTicket> exchangeTickets = null;
		try {
			exchangeTickets = service.getExchangeTickets("new");
		} catch (Exception e) {
			log.error(DoExchangeCommand.class, e);
		}
		if (exchangeTickets == null) { 
			request.setAttribute("error", "There is no exchange tickets");
			return "/error.jsp";
		}
		ExchangeTicket ticket = exchangeTickets.get(0);
		AccountService accService = new AccountService();
		PersonService personService = new PersonService();
		ExchangeService exchService = new ExchangeService();
		try {
			Person person = personService.getBankPerson();
			List<String> currencies = new ArrayList<String>();
			currencies.add(ticket.getFromCurr());
			currencies.add(ticket.getToCurr());
			List<Account> userAccounts = accService.getAccounts(ticket.getClient().getPassportNumber(), currencies);
			Account bankAccount = accService.getBankAccount(ticket.getToCurr(), person.getPassportNumber());
			ExchangeRate rate = exchService.searchExchange(new Date(), ticket.getFromCurr(), ticket.getToCurr());
			if (rate == null){
				request.setAttribute("error", "No rate for exchange");
				return "/error.jsp";
			}
			double exchangeRes = 0.0;
			exchangeRes = exchService.convert(rate, bankAccount, userAccounts, ticket);
			accService.updateAccount(bankAccount);
			for(Account acc: userAccounts){
				accService.updateAccount(acc);
			}
			service.updateExchangeTicket(ticket);
			String exchange = (String) ("Dear, "+ticket.getClient().getName() + " " + ticket.getClient().getSurname()+" you should pay "+result+ " "+ticket.getFromCurr());
			request.setAttribute("exchange", exchange);
		} catch (ExchangeException e) {
			log.error(DoExchangeCommand.class, e);
			request.setAttribute("error", "Error occured. Please see log file");
			result = "/error.jsp";
		} catch (ElementNotFoundException e) {
			log.error(DoExchangeCommand.class, e);
			request.setAttribute("error", "Error occured. Please see log file");
			result = "/error.jsp";
		}catch (SQLException e) {
			log.error(DoExchangeCommand.class, e);
			request.setAttribute("error", "Error occured. Please see log file");
			result = "/error.jsp";
		} catch (Exception e) {
			log.error(DoExchangeCommand.class, e);
			request.setAttribute("error", "Error occured. Please see log file");
			result = "/error.jsp";
		}
		
		return result;
	}
	

}
