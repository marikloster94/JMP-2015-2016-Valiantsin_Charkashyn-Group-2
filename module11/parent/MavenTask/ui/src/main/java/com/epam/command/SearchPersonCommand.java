package com.epam.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.model.Person;
import com.epam.service.PersonService;

public class SearchPersonCommand implements Command {

	private static final Logger log = Logger.getLogger(SearchPersonCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Search person");
		String result = "/searchPerson.jsp";
		String passport = (String) request.getAttribute("passport");
		PersonService service = new PersonService();
		try {
			Person person = service.searchPerson(passport);
			if(person == null){
				System.out.println("Person was not found");
			}
			if(person != null){
				System.out.println("Person was found");
				request.setAttribute("passport", person.getPassportNumber());
				request.setAttribute("first_name", person.getName());
				request.setAttribute("last_name", person.getSurname());
			}
			
		} catch (SQLException e) {
			log.error(SearchPersonCommand.class, e);
			result = "/error.jsp";
		}
		return result;
	}

}
