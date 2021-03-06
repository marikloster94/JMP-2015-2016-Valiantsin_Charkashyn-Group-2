package com.epam.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.model.Person;
import com.epam.service.PersonService;

public class ShowClientsCommand implements Command {

	private static final Logger log = Logger.getLogger(ShowClientsCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "/clients.jsp";
		PersonService service = new PersonService();
		try {
			List<Person> clients = service.getPersons();
			if (clients.isEmpty()) {
				request.setAttribute("error", "No clients");
				return "/error.jsp";
			}
			request.setAttribute("clients", clients);
		} catch (SQLException e) {
			request.setAttribute("error", "Error occured. Please see log");
			log.error(ShowClientsCommand.class, e);
			result = "/error.jsp";
		}
		return result;
	}

}
