package com.epam.mentoring.file;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.FileException;

public class FileGenerator implements Runnable {

	private Logger log = Logger.getLogger(FileGenerator.class);

	private String fileName;
	private Object data;
	private Class<?> className;
	
	public FileGenerator(String fileName, Object data) {
		this.fileName = fileName;
		this.data = data;
	}
	
	public Class<?> getClassName() {
		return className;
	}

	public void setClassName(Class<?> className) {
		this.className = className;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void run() {
		try {
			Writer write = new FileWriter();
			write.write(fileName, data, className);
		} catch (FileException e) {
			log.error(FileGenerator.class, e);
		}
	}

}
