package com.epam.command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

public class DownloadLogoCommand implements Command{
	private static final Logger log = Logger.getLogger(DownloadLogoCommand.class);
	public String execute(HttpServletRequest request,
			HttpServletResponse response, WebClient client) {
		
		try {
			URL url = new URL("http://localhost:8090/Service/LogoService/get");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-Type", "application/json");
			client.reset();
			if( conn.getResponseCode() == Response.Status.OK.getStatusCode() ){
				InputStream inputStream = conn.getInputStream();
	            OutputStream output = new FileOutputStream("D:\\copyOfTest.png");
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	               output.write(buffer, 0, bytesRead);
	            }
	            output.close();
				return "/main.jsp";
			}
		} catch (MalformedURLException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
        
		return "/error.jsp";
	}

}
