package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.epam.service.model.Person;

public class UpdateCommand implements Command {
	private static final Logger log = Logger.getLogger(UpdateCommand.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response, WebClient client) {
		log.debug("Update person");
		String result = "/searchPerson.jsp";
		String firstName = (String) request.getParameter("name");
		String lastName = (String) request.getParameter("surname");
		String date = (String) request.getParameter("date");
		String login = (String) request.getParameter("log");
		String email = (String) request.getParameter("mail");
		Person person = new Person(firstName, lastName, date);
		person.setLogin(login);
		person.setEmail(email);
		int i = client.path("/PersonService/updatePerson").put(person).getStatus();
		client.reset();
		if( i == Response.Status.OK.getStatusCode()){
			request.setAttribute("person", person);
			return result;
		}
		if( i == Response.Status.NOT_FOUND.getStatusCode()){
			log.debug("Person with login " + person.getLogin() + " not found");
			request.setAttribute("error", "Person with login " +  person.getLogin() + " not found");
			result = "/error.jsp";
		}
		if( i == Response.Status.CONFLICT.getStatusCode()){
			log.debug("Error occured while updating person");
			request.setAttribute("error", "Error occured while updating person");
			result = "/error.jsp";
		}
		return result;
	}

}
