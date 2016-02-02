package com.epam.mentoring.file;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.FileException;

public class ClientThread implements Runnable {

	private Logger log = Logger.getLogger(FileGenerator.class);

	private String fileName;
	private List data;
	
	public ClientThread(String fileName){
		this.fileName = fileName;
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

	@Override
	public void run() {
		try {
			Loader load = new FileLoader();
			setData(load.load(fileName));
		} catch (FileException e) {
			log.error(ClientThread.class, e);
		} 

	}

}
