package com.epam.service.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;


public class LogoServiceImpl implements LogoService {
	private static final String FILE_PATH = "D:\\logo.PNG";
	public static final String UPLOAD_FILE_SERVER = "D:\\Demo\\upload\\";

	public Response getLogo() {

		File file = new File(FILE_PATH);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=image_from_server.png");
		return response.build();

	}

	@Override
	public Response uploadImageFile(List<Attachment> attachments,
			HttpServletRequest request) {
		// local variables
        DataHandler dataHandler = null;
        MultivaluedMap<String, String> multivaluedMap = null;
        String fileName = null;
        InputStream inputStream = null;
 
        for (Attachment attachment : attachments) {
            dataHandler = attachment.getDataHandler();
            try{
                // get filename to be uploaded
                multivaluedMap = attachment.getHeaders();
                fileName = getFileName(multivaluedMap);
 
                if(null != fileName && !"".equalsIgnoreCase(fileName)){
                    // write & upload file to server
                    inputStream = dataHandler.getInputStream();
                    writeToFileServer(inputStream, fileName);
 
                    // close the stream
                    inputStream.close();
                }
            }
            catch(IOException ioex) {
                ioex.printStackTrace();
            }
            finally {
                // release resources, if any
            }
        }
        return Response.ok("upload success").build();
        
	}
	
	/**
    *
    * @param inputStream
    * @param fileName
    * @throws IOException
    */
   private void writeToFileServer(InputStream inputStream, String fileName) throws IOException {

       OutputStream outputStream = null;
       try {
           outputStream = new FileOutputStream(new File(UPLOAD_FILE_SERVER + fileName));
           int read = 0;
           byte[] bytes = new byte[1024];
           while ((read = inputStream.read(bytes)) != -1) {
               outputStream.write(bytes, 0, read);
           }
           outputStream.flush();
       }
       catch (IOException ioe) {
           ioe.printStackTrace();
       }
       finally{
           //release resource, if any
           outputStream.close();
       }
   }

   private String getFileName(MultivaluedMap<String, String> multivaluedMap) {

       String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");
       for (String filename : contentDisposition) {

           if ((filename.trim().startsWith("filename"))) {
               String[] name = filename.split("=");
               String exactFileName = name[1].trim().replaceAll("\"", "");
               return exactFileName;
           }
       }
       return "UnknownFile";
   }
}
