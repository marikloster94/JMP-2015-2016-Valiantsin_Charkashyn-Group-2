package com.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.epam.model.Account;
import com.epam.service.AccountService;

public class ShowAccountsCommand implements Command {
	private static final Logger log = Logger.getLogger(ShowAccountsCommand.class);
	
	private AccountService service;

	public ShowAccountsCommand(WebApplicationContext context) {
		service =  context.getBean(AccountService.class);
	}
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "/acounts.jsp";
		try {
			List<Account> accounts = service.getAccounts();
			if (accounts.isEmpty()) {
				request.setAttribute("error", "No Accounts");
				return "/error.jsp";
			}
			request.setAttribute("accounts", accounts);
		} catch (Exception e) {
			request.setAttribute("error", "Error occured. Please see log");
			log.error(ShowClientsCommand.class, e);
			result = "/error.jsp";
		}
		return result;
	}
}
