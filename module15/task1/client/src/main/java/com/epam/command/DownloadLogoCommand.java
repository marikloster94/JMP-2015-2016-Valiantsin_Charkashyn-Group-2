package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class DownloadLogoCommand implements Command{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response, WebClient client) {
		int i = client.path("/LogoService/get").get().getStatus();
		client.reset();
		if( i == Response.Status.OK.getStatusCode() ){
			return "/main.jsp";
		}
		return "/error.jsp";
	}

}
