package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.epam.service.model.Person;

public class AddPersonCommand implements Command {

	private static final Logger log = Logger.getLogger(AddPersonCommand.class);
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response, WebClient client) {
		log.debug("Add new person");
		String result = "/main.jsp";
		String firstName = (String) request.getParameter("first_name");
		String lastName = (String) request.getParameter("last_name");
		String date = (String) request.getParameter("date");
		String login = (String) request.getParameter("login");
		String email = (String) request.getParameter("email");
		Person person = new Person(firstName, lastName, date);
		person.setLogin(login);
		person.setEmail(email);
		int i = client.path("/PersonService/addPerson").post(person).getStatus();
		client.reset();
		if( i == Response.Status.OK.getStatusCode()){
			return result;
		}
		if( i == Response.Status.BAD_REQUEST.getStatusCode()){
			log.debug("Person with login " + login + " exists");
			request.setAttribute("error", "Person with login " + login + " exists");
			result = "/error.jsp";
		}
		if( i == Response.Status.CONFLICT.getStatusCode()){
			log.debug("Error occured while adding new person");
			request.setAttribute("error", "Error occured while adding new person");
			result = "/error.jsp";
		}
		return result;
	}

}
