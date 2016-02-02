package com.epam.mentoring.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.FileException;

public class FileLoader implements Loader {
	
	private Logger log = Logger.getLogger(FileLoader.class);
	private final Lock lock = new ReentrantLock();
	
	public List load(String filename) throws FileException {
		List result = null;
		ObjectInputStream ois = null;
		try {
			lock.lock();
			InputStream ips = ClassLoader.getSystemResourceAsStream(filename);
			ois = new ObjectInputStream(ips);
			result = (List) ois.readObject();
		} catch (Exception e) {
			throw new FileException("Problems with loading file", e);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
			lock.unlock();
		}
		return result;
	}

}
