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
				try{
					while(data.isEmpty()){
						data.wait();
					}
				}catch(InterruptedException ex){
					System.out.println(ex.getMessage());
				}
				System.out.println("Consumer get: "	+ data.get(data.size() - 1));

			}
		}
	}

}
