package com.epam.mentoring.util;

import org.apache.log4j.Logger;

import com.epam.mentoring.file.ClientThread;
import com.epam.mentoring.file.FileGenerator;

public class FileUtil {
	private static Logger log = Logger.getLogger(FileUtil.class);

	public static Object loadFromFile(ClientThread client) {

		if (client != null) {
			Thread t1 = new Thread(client);
			t1.start();
			try {
				t1.join();
			} catch (InterruptedException e) {
				log.error(BankUtil.class, e);
			} finally {
				if (t1.isAlive()) {
					t1.interrupt();
				}
			}
			return client.getData();
		}
		return null;
	}

	public static void writeToFile(FileGenerator file) {

		if (file != null) {
			Thread t1 = new Thread(file);
			t1.start();
			try {
				t1.join();
			} catch (InterruptedException e) {
				log.error(BankUtil.class, e);
			} finally {
				if (t1.isAlive()) {
					t1.interrupt();
				}
			}
		}
	}
}
