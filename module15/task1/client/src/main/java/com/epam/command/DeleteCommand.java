package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxrs.client.WebClient;

import com.epam.service.model.Person;

public class DeleteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response, WebClient client) {
		String person = (String) request.getParameter("login");
		client.path("/PersonService/delete/"+person).delete();
		client.reset();
		return "/main.jsp";
	}

}
