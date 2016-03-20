package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxrs.client.WebClient;


public interface Command {

	String execute(HttpServletRequest request, HttpServletResponse response, WebClient client);
}
