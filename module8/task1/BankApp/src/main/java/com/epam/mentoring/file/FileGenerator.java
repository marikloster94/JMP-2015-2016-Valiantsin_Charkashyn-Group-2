package com.epam.mentoring.file;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.FileException;

public class FileGenerator implements Runnable {

	private Logger log = Logger.getLogger(FileGenerator.class);

	private String fileName;
	private List data;
	

	public FileGenerator(String fileName, List data) {
		this.fileName = fileName;
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public void run() {
		try {
			Writer write = new FileWriter();
			write.write(fileName, data);
		} catch (FileException e) {
			log.error(FileGenerator.class, e);
		} 
	}

}
