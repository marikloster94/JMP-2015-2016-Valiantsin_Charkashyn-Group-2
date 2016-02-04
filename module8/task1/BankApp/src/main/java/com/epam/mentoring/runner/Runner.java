package com.epam.mentoring.runner;

import com.epam.mentoring.util.BankUtil;

public class Runner {

	public static void main(String[] args) {		
		try {
			BankUtil.menu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		ClientThread file = new ClientThread("account.xml");
//		file.setClassName(Accounts.class);
//		Thread t1 = new Thread(file);
//		t1.start();
//		try {
//			t1.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(Account acc: ((Accounts)file.getData()).getAccounts()){
//			System.out.println(acc.getCurr().getShortName());
//			System.out.println(acc.getPerson().getName()+" "+acc.getPerson().getSurname());
//			System.out.println(acc.getDescription());
//		}
		
	}

}
