package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.epam.model.Person;
import com.epam.service.PersonService;

public class SearchPersonCommand implements Command {

	private static final Logger log = Logger.getLogger(SearchPersonCommand.class);
	private PersonService service;

	public SearchPersonCommand(WebApplicationContext context) {
		service =  context.getBean(PersonService.class);
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Search person");
		String result = "/searchPerson.jsp";
		String passport = (String) request.getParameter("passport");
		try {
			Person person = service.searchPerson(passport);
			if(person == null){
				log.debug("Person was not found");
				request.setAttribute("error", "Person was not found");
				return "/error.jsp";
			}
			log.debug("Person was found");
			request.setAttribute("passport", person.getPassportNumber());
			request.setAttribute("first_name", person.getName());
			request.setAttribute("last_name", person.getSurname());
			request.setAttribute("person", person);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			log.error(SearchPersonCommand.class, e);
			result = "/error.jsp";
		}
		return result;
	}

}
