package com.epam.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxrs.client.WebClient;

import com.epam.command.Command;
import com.epam.command.RequestHelper;

public class App extends HttpServlet {

	private static final long serialVersionUID = 4845018080135856585L;

	private final RequestHelper helper = new RequestHelper();
	private final WebClient client = WebClient.create("http://localhost:8090/Service");

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String command = request.getParameter("command");
		Command c = helper.getCommand(command);
		String result = null;
		if(c != null){
			result = c.execute(request, response, client);
		}else{
			result = "/error.jsp";
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(result);
		dispatcher.forward(request, response);
//		response.sendRedirect(result);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		processRequest(request, response);
	}
}
