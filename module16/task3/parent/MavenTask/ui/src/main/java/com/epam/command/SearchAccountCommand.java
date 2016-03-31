package com.epam.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.model.Account;
import com.epam.service.AccountService;

public class SearchAccountCommand implements Command {
	private static final Logger log = Logger.getLogger(SearchAccountCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Search account");
		String result = "/searchAccount.jsp";
		int id = Integer.parseInt(request.getParameter("id"));
		AccountService accountService = new AccountService();
		 try {
			Account account = accountService.searchAccount(id);
			if (account == null) {
				log.debug("Account was not found");
				request.setAttribute("error", "Account was not found");
				return "/error.jsp";
			}
			log.debug("Account was found");
			request.setAttribute("account", account);
			request.setAttribute("id", id);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			log.error(SearchAccountCommand.class, e);
			result = "/error.jsp";
		}
		return result;
	}

}
