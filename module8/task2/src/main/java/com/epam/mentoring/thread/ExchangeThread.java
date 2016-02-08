package com.epam.mentoring.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.ElementNotFoundException;
import com.epam.mentoring.exception.ExchangeException;
import com.epam.mentoring.model.Account;
import com.epam.mentoring.model.ExchangeRate;
import com.epam.mentoring.model.ExchangeTicket;
import com.epam.mentoring.model.Person;
import com.epam.mentoring.service.AccountService;
import com.epam.mentoring.service.ExchangeService;
import com.epam.mentoring.service.ExchangeTicketService;
import com.epam.mentoring.service.PersonService;
import com.epam.mentoring.util.BankUtil;

public class ExchangeThread implements Callable<String> {

	private ExchangeTicket ticket ;
	private static Logger log = Logger.getLogger(ExchangeThread.class);
	
	public ExchangeThread(ExchangeTicket ticket){
		this.ticket = ticket;
	}
	
	@Override
	public String call() throws Exception {
		ExchangeTicketService service = new ExchangeTicketService();
		AccountService accService = new AccountService();
		PersonService personService = new PersonService();
		ExchangeService exchService = new ExchangeService();
		Person person = personService.getBankPerson();
		List<String> currencies = new ArrayList<String>();
		currencies.add(ticket.getFromCurr().getShortName());
		currencies.add(ticket.getToCurr().getShortName());
		List<Account> userAccounts = accService.getAccounts(ticket.getClient().getPassportNumber(), currencies);
		Account bankAccount = accService.getBankAccount(ticket.getToCurr().getShortName(), person.getPassportNumber());
		ExchangeRate rate = exchService.searchExchange(new Date(), ticket.getFromCurr().getShortName(), ticket.getToCurr().getShortName());
		double result = 0.0;
		try {
			result = exchService.convert(rate, bankAccount, userAccounts, ticket);
			accService.updateAccount(bankAccount);
			for(Account acc: userAccounts){
				accService.updateAccount(acc);
			}
			service.updateExchangeTicket(ticket);
		} catch (ExchangeException e) {
			log.error(BankUtil.class, e);
			return null;
		} catch (ElementNotFoundException e) {
			log.error(BankUtil.class, e);
			return null;
		}
		return (String) ("Dear, "+ticket.getClient().getName() + " " + ticket.getClient().getSurname()+" you should pay "+result+ " "+ticket.getFromCurr().getShortName());
	}

	

}
