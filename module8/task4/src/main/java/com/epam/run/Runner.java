package com.epam.run;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.epam.runnable.CarTimeout;
import com.epam.runnable.Race;

public class Runner {
	private static Logger log = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
		new Race().run();
		ExecutorService service = Executors.newFixedThreadPool(2);
		List<CarTimeout> cars = new ArrayList<CarTimeout>();
		cars.add(new CarTimeout("audi"));
		cars.add(new CarTimeout("volvo"));
		try {
			service.invokeAll(cars, 2, TimeUnit.SECONDS);
			for(CarTimeout car:cars){
				log.info("Disqualify "+ car.getName()+" at distance "+car.getDistance());
			}
		} catch (InterruptedException e) {
			log.error(e);
		} catch(Exception e){
			log.error(e);
		}finally{
			service.shutdown();
		}

	}

}
