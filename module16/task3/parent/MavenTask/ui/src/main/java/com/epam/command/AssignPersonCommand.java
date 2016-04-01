package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.epam.model.Account;
import com.epam.model.Person;
import com.epam.service.AccountService;
import com.epam.service.PersonService;

public class AssignPersonCommand implements Command {

	private static final Logger log = Logger.getLogger(AssignPersonCommand.class);
	private AccountService accountService;
	private PersonService personService;
	
	public AssignPersonCommand(WebApplicationContext context) {
		personService = context.getBean(PersonService.class);
		accountService =  context.getBean(AccountService.class);
	}
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Assign person to account");
		String result = "/main.jsp";
		String passport = request.getParameter("person");
		int accountID = Integer.parseInt(request.getParameter("account"));
		try {
			Person person = personService.searchPerson(passport);
			Account account = accountService.searchAccount(accountID);
			if (account == null || person == null) {
				request.setAttribute("error", "No data for assign");
				return "/error.jsp";
			}
			accountService.assignPerson(person, account);
		} catch (Exception e) {
			log.error(PrepareAssignCommand.class, e);
			request.setAttribute("error", "Error occured. Please see log file");
			result = "/error.jsp";
		}
		return result;
	}

}
