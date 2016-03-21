package com.epam.service.web;

import java.io.File;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


public class LogoServiceImpl implements LogoService {
	private static final String FILE_PATH = "D:\\logo.PNG";

	@GET
	@Path("/get")
	@Produces("image/png")
	public Response getLogo() {

		File file = new File(FILE_PATH);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=image_from_server.png");
		return response.build();

	}
}
