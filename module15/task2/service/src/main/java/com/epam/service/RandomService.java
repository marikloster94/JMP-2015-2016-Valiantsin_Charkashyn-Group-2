package com.epam.service;

import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class RandomService {

	@WebMethod
	public int randomNumberGenerator(int lowerBound, int upperBound) {
		Random r = new Random();
		return r.nextInt(upperBound - lowerBound) + lowerBound;
	}
}
