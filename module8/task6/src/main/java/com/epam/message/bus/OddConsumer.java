package com.epam.message.bus;

import java.util.List;

public class OddConsumer extends Consumer {

	public OddConsumer(List<Integer> data) {
		super(data);
	}

	@Override
	public void run() {
		while (true) {
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
				if (data.get(data.size() - 1) % 2 != 0) {
					System.out.println("Consumer get last odd value: " + data.get(data.size() - 1));
				}
			}
		}
	}

}
