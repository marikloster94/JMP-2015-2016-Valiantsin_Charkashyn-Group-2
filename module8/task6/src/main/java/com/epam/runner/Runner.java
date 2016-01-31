package com.epam.runner;

import java.util.ArrayList;
import java.util.List;

import com.epam.message.bus.Consumer;
import com.epam.message.bus.Producer;

public class Runner {

	private static List<Integer> data = new ArrayList<Integer>();
	
	public static void main(String [] args){
		Thread t1 = new Thread(new Producer(data));
		Thread t2 = new Thread(new Consumer(data));
		t2.start();
		t1.start();
	}
}
