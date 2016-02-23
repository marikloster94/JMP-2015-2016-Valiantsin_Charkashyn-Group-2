package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.service.DatabaseService;

public class InitDatabaseCommand implements Command {

	private static final Logger log = Logger.getLogger(InitDatabaseCommand.class);
	private final DatabaseService service = new DatabaseService();
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "/main.jsp";
		try {
			service.createDB();
		} catch (Exception e) {
			result = "/error.jsp";
			request.setAttribute("error", e.getMessage());
			log.error(InitDatabaseCommand.class, e);
		}
		return result;
	}

}
