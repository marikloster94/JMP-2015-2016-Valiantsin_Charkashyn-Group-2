package com.epam.service;

import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class RandomService {

	@WebMethod
	public int randomNumberGenerator(
			@WebParam(name = "lowerBound") int lowerBound,
			@WebParam(name = "upperBound") int upperBound) {
		Random r = new Random();
		return r.nextInt(upperBound - lowerBound) + lowerBound;
	}
}
