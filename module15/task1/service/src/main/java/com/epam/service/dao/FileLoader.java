package com.epam.service.dao;

import java.io.File;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

public class FileLoader implements Loader {
	
	private Logger log = Logger.getLogger(FileLoader.class);
	private final Lock lock = new ReentrantLock();
	
	public Object load(String filename, Class<?> className) throws Exception  {
		Object result = null;
		try {
			lock.lock();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL url = classLoader.getResource("/".concat(filename));
			if(url == null){
				throw new Exception("URL can not be null");
			}
			JAXBContext jaxbContext = JAXBContext.newInstance(className);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			result = jaxbUnmarshaller.unmarshal(new File(url.getFile()));
		} catch (Exception e) {
			log.error(e);
			throw new Exception("Problems with loading file", e);
		} finally {
			lock.unlock();
		}
		return result;
	}

}
