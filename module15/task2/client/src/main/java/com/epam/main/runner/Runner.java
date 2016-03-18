package com.epam.main.runner;

import com.epam.service.RandomService;
import com.epam.service.RandomServiceService;

public class Runner {

	public static void main(String[] args) {
		RandomServiceService service = new RandomServiceService();

		RandomService port = service.getRandomServicePort();
		int[] lottery = new int[6];
		for (int i = 0; i <= 5; i++) {
			
			int result = port.randomNumberGenerator(1, 56);
			lottery[i] = result;
			System.out.println("Attempt number " + (i + 1) + ": " + result);
		}
		System.out.println("result: ");
		for (int i = 0; i < lottery.length; i++) {
			System.out.print(" " + lottery[i]);
		}
	}

}
