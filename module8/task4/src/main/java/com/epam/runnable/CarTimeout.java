package com.epam.runnable;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

public class CarTimeout implements Callable<String> {

	private static final long MAX_DISTANCE = 100000;
	Logger log = Logger.getLogger(getClass());

	private long distance;
	private String name;
	

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CarTimeout(String name) {
		this.name = name;
	}

	public String call() throws Exception {
		while (distance < MAX_DISTANCE && !Thread.interrupted()) {
			distance += 10;
			log.info(name + " " + distance);
		}
		return name;
	}

}
