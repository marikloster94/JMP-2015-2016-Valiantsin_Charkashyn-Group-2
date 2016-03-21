package com.epam.service.web;

import java.util.List;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

@WebService (name = "LogoService")
public interface LogoService {
	@GET
	@Path("/get")
	@Produces("image/png")
	Response getLogo();
	
	@POST
    @Path("/upload/images")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImageFile(List<Attachment> attachments, @Context HttpServletRequest request);

}
