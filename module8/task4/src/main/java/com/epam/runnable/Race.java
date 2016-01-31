package com.epam.runnable;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

public class Race {
	
	private static Logger log = Logger.getLogger(Race.class);
	private CountDownLatch latch = new CountDownLatch(1);
	private String winner;

	public synchronized void finished(String threadName) {
		if (winner == null) {
			winner = threadName;
		}
		latch.countDown();
	}

	public void run() {
		Thread car1 = new Thread(new Car("ford", 100){
			public void run(){
				super.run();
				finished("ford");
			}
		});
		Thread car2 = new Thread(new Car("audi", 100){
			public void run(){
				super.run();
				finished("audi");
			}
		});
		car1.start();
		car2.start();
		try {
			latch.await();
			log.info("The winner is " + winner);
			System.out.println("The winner is " + winner);
		} catch (InterruptedException e) {
			System.out.println("No winner");
			log.error(e);
			Thread.currentThread().interrupt();
		}
	}
}
