package com.epam.mentoring.file;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.FileException;

public class ClientThread implements Runnable {

	private Logger log = Logger.getLogger(FileGenerator.class);

	private String fileName;
	private Object data;
	private Class<?> className;
	
	public ClientThread(String fileName){
		this.fileName = fileName;
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

	@Override
	public void run() {
		try {
			Loader load = new FileLoader();
			setData(load.load(fileName, className));
		} catch (FileException e) {
			log.error(ClientThread.class, e);
		} 

	}

}
