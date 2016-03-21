package com.epam.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

public class UploadCommand implements Command {

	private static final Logger log = Logger.getLogger(UploadCommand.class);

	private final String httpURL = "http://localhost:8090/Service/LogoService/upload/images";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response, WebClient client) {

		File filePath = null;
		// local variables
		HttpPost httpPost = null;
		CloseableHttpClient closeableHttpClient = null;
		HttpResponse httpResponse = null;
		MultipartEntityBuilder multipartEntityBuilder = null;
		HttpEntity httpEntity = null;
		FileBody fileBody = null;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = null;
		String fileName = null;
		String path = null;
		try {
			Part filePart = request.getPart("uploadedFile");
			fileName = filePart.getSubmittedFileName();

			path = request.getParameter("destination");
			filePath = new File(path + File.separator + fileName);

			// http post request header
			httpPost = new HttpPost(httpURL);

			// constructs file to be uploaded
			fileBody = new FileBody(filePath);
			multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder
					.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			multipartEntityBuilder.addPart("uploadfile", fileBody);
			httpEntity = multipartEntityBuilder.build();
			httpPost.setEntity(httpEntity);

			// actual execution of http post request
			closeableHttpClient = HttpClients.createDefault();
			httpResponse = closeableHttpClient.execute(httpPost);

			log.info("Response code/message: " + httpResponse.getStatusLine());
			httpEntity = httpResponse.getEntity();

			// get the response content
			inputStream = httpEntity.getContent();
			bufferedReader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			stringBuilder = new StringBuilder();
			String strReadLine = bufferedReader.readLine();

			// iterate to get the data and append in StringBuilder
			while (strReadLine != null) {
				stringBuilder.append(strReadLine);
				strReadLine = bufferedReader.readLine();
				if (strReadLine != null) {
					stringBuilder.append("\n");
				}
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		} catch (Exception ex) {
			log.error(ex);
			request.setAttribute("error", "No file found: "+path + File.separator + fileName);
		} finally {
			closeableHttpClient.getConnectionManager().closeExpiredConnections();
		}
		return "/main.jsp";
	}

}
