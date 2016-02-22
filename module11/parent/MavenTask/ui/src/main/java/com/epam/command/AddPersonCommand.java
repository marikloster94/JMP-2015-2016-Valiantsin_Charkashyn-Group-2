package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.model.Person;
import com.epam.service.PersonService;

public class AddPersonCommand implements Command {

	private static final Logger log = Logger.getLogger(AddPersonCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("Add new person");
		String result = "/ModuleUI/page/main.jsp";
		String firstName = (String) request.getAttribute("first_name");
		String lastName = (String) request.getAttribute("last_name");
		String date = (String) request.getAttribute("date");
		String passport = (String) request.getAttribute("passport");
		PersonService service = new PersonService();
		Person person = new Person(firstName, lastName, date);
		person.setPassportNumber(passport);
		try {
			service.addPerson(person);
		} catch (Exception e) {
			log.error(AddPersonCommand.class, e);
			result = "/ModuleUI/page/error.jsp";
		}
		return result;
	}

}
