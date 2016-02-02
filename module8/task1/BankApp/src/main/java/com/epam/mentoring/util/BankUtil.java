package com.epam.mentoring.util;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.mentoring.file.ClientThread;
import com.epam.mentoring.model.Account;

public class BankUtil {

	private static Logger log = Logger.getLogger(BankUtil.class);
	
	public static void menu(){
		boolean menu = true;
		while(menu){
			showMenu();
			Scanner sc = new Scanner(System.in);
			int value = sc.nextInt();
			switch(value){
			case 1 :
				List data = loadData();
				System.out.println(data.size());
				break;
			case 2:
				List<Account> accounts = getAccounts();
				for(Account acc: accounts){
					System.out.println(acc.getDescription());
				}
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				System.out.println("Bye");
				menu = false;
				break;
			default:
				System.out.println("Please, choose another case");
				showMenu();	
				
			}
		}
	}
	
	public static void showMenu(){
		System.out.println("1. Load data");
		System.out.println("2. Open account");
		System.out.println("3. Assign person");
		System.out.println("4. Exchange currencies");
		System.out.println("5. Add new client");
		System.out.println("6. Add currency");
		System.out.println("7. Search person");
		System.out.println("8. Exit");
		System.out.println("   What do you want to do?");
		
	}
	
	public static List loadData(){
		
		System.out.println("1. Load accounts");
		System.out.println("2. Load people");
		System.out.println("3. Load currencies");
		System.out.println("4. Load banks");
		System.out.println("   What do you want to load?");
		Scanner sc = new Scanner(System.in);
		int value = sc.nextInt();
		ClientThread client = null;
		switch(value){
		case 1:
			client = new ClientThread("account.txt");	
			break;
		case 2:
			client = new ClientThread("person.txt");	
			break;
		case 3:
			client = new ClientThread("currency.txt");
			break;
		case 4:
			client = new ClientThread("bank.txt");
			break;
			
		}
		if(client != null){
			Thread t1 = new Thread(client);
			t1.start();
			try {
				t1.join();
			} catch (InterruptedException e) {
				log.error(BankUtil.class, e);
			}finally{
				if(t1.isAlive()){
					t1.interrupt();
				}
			}
			return client.getData();
		}
		return null;
	}
	
	public static List<Account> getAccounts(){
		ClientThread client = new ClientThread("account.txt");	
		Thread t1 = new Thread(client);
		try {
			t1.start();
			t1.join();
		} catch (InterruptedException e) {
			log.error(BankUtil.class, e);
		}finally{
			if(t1.isAlive()){
				t1.interrupt();
			}
		}
		
		return (List<Account>) client.getData();
	}
}
