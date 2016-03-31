package com.epam.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.model.Account;
import com.epam.service.AccountService;

public class ShowAccountsCommand implements Command {
	private static final Logger log = Logger.getLogger(ShowAccountsCommand.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "/acounts.jsp";
		AccountService service = new AccountService();
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
