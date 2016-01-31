package com.epam.message.bus;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable {

	private List<Integer> data;

	public Producer(List<Integer> data) {
		this.data = data;
	}

	public void run() {
		for (int i = 0; i < 200; i++) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (data){
				int message = new Random().nextInt(150);
				data.add(message);
				data.notify();
				System.out.println("Producer add message " + message);
				
			}
		}
	}

}
