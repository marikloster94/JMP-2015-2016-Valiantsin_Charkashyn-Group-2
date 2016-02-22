package com.epam.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.command.Command;
import com.epam.command.RequestHelper;

public class App extends HttpServlet {

	private static final long serialVersionUID = 4845018080135856585L;

	private final RequestHelper helper = new RequestHelper();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String command = request.getParameter("command");
		Command c = helper.getCommand(command);
		String result = null;
		if(c != null){
			result = c.execute(request, response);
		}else{
			result = "page/error.jsp";
		}
		response.sendRedirect(result);
	}
}
