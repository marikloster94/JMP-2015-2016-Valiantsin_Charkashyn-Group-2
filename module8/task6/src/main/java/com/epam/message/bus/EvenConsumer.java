package com.epam.message.bus;

import java.util.List;

public class EvenConsumer extends Consumer {

	public EvenConsumer(List<Integer> data) {
		super(data);
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (data) {
				try {
					while (data.isEmpty()) {
						data.wait();
					}
				} catch (InterruptedException ex) {
					System.out.println(ex.getMessage());
				}
				if (data.get(data.size() - 1) % 2 == 0) {
					System.out.println("Consumer get last even value: " + data.get(data.size() - 1));
				}
			}
		}
	}


}
