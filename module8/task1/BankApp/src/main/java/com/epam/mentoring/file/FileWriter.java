package com.epam.mentoring.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.FileException;

public class FileWriter implements Writer {

	private Logger log = Logger.getLogger(FileWriter.class);
	private final Lock lock = new ReentrantLock();
	
	public void write(String filename, List data) throws FileException {
		ObjectOutputStream oos = null;
		try {
			lock.lock();
			URL ips = ClassLoader.getSystemResource(filename);
			FileOutputStream fos = new FileOutputStream(ips.getFile(), true);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
		} catch (Exception e) {
			throw new FileException("Problems with writing to file occured", e); 
		}finally{
			if(oos != null){
				try {
					oos.close();
				} catch (IOException e) {
					log.error(e);
				}
				lock.unlock();
			}
		}
		
	}

}
