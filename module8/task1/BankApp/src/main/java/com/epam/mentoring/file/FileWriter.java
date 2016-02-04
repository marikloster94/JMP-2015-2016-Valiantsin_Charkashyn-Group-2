package com.epam.mentoring.file;

import java.io.File;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.FileException;

public class FileWriter implements Writer {

	private Logger log = Logger.getLogger(FileWriter.class);
	private final Lock lock = new ReentrantLock();

	public void write(String filename, Object data, Class<?> className)
			throws FileException {
		try {
			lock.lock();
			JAXBContext jaxbContext = JAXBContext.newInstance(className);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			URL ips = ClassLoader.getSystemResource(filename);
			jaxbMarshaller.marshal(data, new File(ips.getFile()));
		} catch (Exception e) {
			log.error(e);
			throw new FileException("Problems with writing to file occured", e);
		} finally {
			lock.unlock();
		}
	}

}
