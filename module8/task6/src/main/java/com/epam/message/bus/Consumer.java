package com.epam.message.bus;

import java.util.Iterator;
import java.util.List;

public class Consumer implements Runnable {

	private List<Integer> data;

	public Consumer(List<Integer> data) {
		this.data = data;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (data) {
				if (!data.isEmpty()) {
					System.out.println("Consumer get: "	+ data.get(data.size() - 1));
				} else {
					System.out.println("Consumer can not get data. List is empty");
				}

			}
		}
	}

}
