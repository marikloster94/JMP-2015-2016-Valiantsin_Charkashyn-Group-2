package com.epam.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.epam.command.Command;
import com.epam.command.RequestHelper;

public class App extends HttpServlet {

	private static final long serialVersionUID = 4845018080135856585L;

	private RequestHelper helper ;
	private WebApplicationContext application ;
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String command = request.getParameter("command");
		if(application == null){
			application = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		}
		
		if(helper == null) {
			helper = new RequestHelper(application);
		}
		Command c = helper.getCommand(command);
		String result = null;
		if(c != null){
			result = c.execute(request, response);
		}else{
			result = "/error.jsp";
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(result);
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		processRequest(request, response);
	}
}
