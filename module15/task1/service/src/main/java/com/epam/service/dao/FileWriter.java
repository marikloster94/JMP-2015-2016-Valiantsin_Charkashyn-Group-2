package com.epam.service.dao;

import java.io.File;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

public class FileWriter implements Writer {

	private Logger log = Logger.getLogger(FileWriter.class);
	private final Lock lock = new ReentrantLock();

	public void write(String filename, Object data, Class<?> className) throws Exception {
		try {
			lock.lock();
			JAXBContext jaxbContext = JAXBContext.newInstance(className);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//			URL url = classLoader.getResource("/WEB-INF/classes/".concat(filename));
//			if(url == null){
//				throw new Exception("URL can not be null");
//			}
//			jaxbMarshaller.marshal(data, new File(url.getFile()));
			jaxbMarshaller.marshal(data, new File("D:\\person.xml"));
		} catch (Exception e) {
			log.error(e);
			throw new Exception("Problems with writing to file occured", e);
		} finally {
			lock.unlock();
		}
	}

}
