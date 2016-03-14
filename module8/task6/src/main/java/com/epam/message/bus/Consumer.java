package com.epam.message.bus;

import java.util.List;

public abstract class Consumer implements Runnable {

	protected List<Integer> data;

	public Consumer(List<Integer> data) {
		this.data = data;
	}


}
