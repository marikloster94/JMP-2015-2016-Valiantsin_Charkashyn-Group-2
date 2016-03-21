package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

public class DeleteCommand implements Command {

	private static final Logger log = Logger.getLogger(DeleteCommand.class);
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response, WebClient client) {
		String person = (String) request.getParameter("login");
		client.path("/PersonService/delete/"+person).delete();
		client.reset();
		return "/main.jsp";
	}

}
