package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.epam.service.model.Person;

public class ShowUpdateWindow implements Command {
	private static final Logger log = Logger.getLogger(ShowUpdateWindow.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response, WebClient client) {
		String login = (String) request.getParameter("login");
		Person person = client.path("/PersonService/getPerson/"+login).get(Person.class);
		client.reset();
		if (person == null) {
			log.debug("Person was not found");
			request.setAttribute("error", "Person was not found");
			return "/error.jsp";
		}
		log.debug("Person was found");
		request.setAttribute("person", person);
		return "/update.jsp";
	}

}
