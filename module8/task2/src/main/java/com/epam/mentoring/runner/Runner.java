package com.epam.mentoring.runner;

import com.epam.mentoring.util.BankUtil;

public class Runner {

	public static void main(String[] args) {
		try {
			BankUtil.menu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
