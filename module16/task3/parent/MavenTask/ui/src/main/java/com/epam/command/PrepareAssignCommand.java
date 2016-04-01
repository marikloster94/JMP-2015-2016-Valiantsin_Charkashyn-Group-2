package com.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.epam.model.Account;
import com.epam.model.Person;
import com.epam.service.AccountService;
import com.epam.service.PersonService;

public class PrepareAssignCommand implements Command {

	private static final Logger log = Logger.getLogger(PrepareAssignCommand.class);
	private AccountService accServ;
	private PersonService service;
	
	public PrepareAssignCommand(WebApplicationContext context) {
		accServ =  context.getBean(AccountService.class);
		service =  context.getBean(PersonService.class);
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "/assignPerson.jsp";
		try {
			List<Person> persons = service.getPersons();
			if(persons.isEmpty()){
				request.setAttribute("error", "No persons for assign");
				return "/error.jsp";
			}
			List<Account> accounts = accServ.getAccounts();
			if(accounts.isEmpty()){
				request.setAttribute("error", "No persons for assign");
				return "/error.jsp";
			}
			request.setAttribute("persons", persons);
			request.setAttribute("accounts", accounts);
		} catch (Exception e) {
			log.error(PrepareAssignCommand.class, e);
			request.setAttribute("error", e.getMessage());
			result = "/error.jsp";
		}
		return result;
	}

}
