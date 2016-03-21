package com.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.epam.service.model.Person;

public class ShowPersonsCommand implements Command {

	private static final Logger log = Logger.getLogger(ShowPersonsCommand.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response, WebClient client) {
		String result = "/persons.jsp";
		List<Person> persons = (List<Person>) client.path("/PersonService/getAllPerson").getCollection(Person.class);
		client.reset();
		if (persons.isEmpty()) {
			request.setAttribute("error", "No persons");
			return "/error.jsp";
		}
		request.setAttribute("persons", persons);

		return result;
	}

}
