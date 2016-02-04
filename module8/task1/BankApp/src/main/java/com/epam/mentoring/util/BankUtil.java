package com.epam.mentoring.util;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.InvalidDateException;
import com.epam.mentoring.model.Account;
import com.epam.mentoring.model.Currency;
import com.epam.mentoring.model.Person;
import com.epam.mentoring.service.AccountService;
import com.epam.mentoring.service.CurrencyService;
import com.epam.mentoring.service.PersonService;

public class BankUtil {

	private static Logger log = Logger.getLogger(BankUtil.class);
	private static final String DATE_REGEX = "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[.//-]([0]?[1-9]|[1][0-2])[.//-]([0-9]{4}|[0-9]{2})$";
	
	public static void menu() throws Exception{
		boolean menu = true;
		while(menu){
			showMenu();
			Scanner sc = new Scanner(System.in);
			int value = sc.nextInt();
			switch(value){
			case 1:{
				AccountService accountServ = new AccountService();
				Account acc = new Account();
				acc.setId(accountServ.getLastId());
				sc.nextLine();
				System.out.println("Enter client passrort number");
				String passportNumber = sc.nextLine();
				
				PersonService service = new PersonService();
				
				Person person = service.searchPerson(passportNumber);
				if(person == null){
					System.out.println("There is not client with passport number "+passportNumber+". You should create new person");
					sc.nextLine();
					System.out.println("Enter user name");
					String name = sc.nextLine();
					System.out.println("Enter user surname");
					String surname = sc.nextLine();
					System.out.println("Enter user date of Birth");
					String date = sc.nextLine();
					if(!date.matches(DATE_REGEX)){
						System.out.println("Date should me in format dd/mm/yyyy");
						log.warn("Date should me in format dd/mm/yyyy");
						break;
					}
					Person newPerson = new Person(name, surname, date);
					
					service.addPerson(newPerson);
				}
				acc.setPerson(person);
				System.out.println("Enter short name for search currency");
				String currency = sc.nextLine();
				CurrencyService currService = new CurrencyService();
				Currency curr = currService.searchCurrency(currency);
				if(curr == null){
					System.out.println("Currency can not be null");
					log.warn("Currency can not be null");
					break;
				}
				acc.setCurr(curr);
				System.out.println("Enter description of account");
				String description = sc.nextLine();
				acc.setDescription(description);
				System.out.println("Enter startDate of account");
				String startDate = sc.nextLine();
				if(!startDate.matches(DATE_REGEX)){
					System.out.println("Date should me in format dd/mm/yyyy");
					log.warn("Date should me in format dd/mm/yyyy");
					break;
				}
				acc.setStartdDate(startDate);
				System.out.println("Enter endDate of account");
				String endDate = sc.nextLine();
				if(!endDate.matches(DATE_REGEX)){
					System.out.println("Date should me in format dd/mm/yyyy");
					log.warn("Date should me in format dd/mm/yyyy");
					break;
				}
				acc.setEndDate(endDate);
				System.out.println("Enter amount");
				Double amount = sc.nextDouble();
				acc.setValue(amount);
				accountServ.addAccount(acc);
			}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:{
				sc.nextLine();
				System.out.println("Enter user name");
				String name = sc.nextLine();
				System.out.println("Enter user surname");
				String surname = sc.nextLine();
				System.out.println("Enter user date of Birth");
				String date = sc.nextLine();
				PersonService service = new PersonService();
				if(!date.matches(DATE_REGEX)){
					throw new InvalidDateException("Date should me in farmat dd/mm/yyyy");
				}
				Person person = new Person(name, surname, date);
				service.addPerson(person);
			}
				break;
			case 5:{
				CurrencyService service = new CurrencyService();
				Currency curr = new Currency();
				curr.setIdCurrency(service.gelLastId());
				sc.nextLine();
				System.out.println("Enter new shortName of currency");
				String name = sc.nextLine();
				curr.setShortName(name);
				service.addCurrency(curr);
			}
				break;
			case 6:{
				sc.nextLine();
				System.out.println("Enter client passrort number");
				String passportNumber = sc.nextLine();
				PersonService service = new PersonService();
				Person person = service.searchPerson(passportNumber);
				if(person == null){
					System.out.println("Person with passport number "+ passportNumber +" didn't found");
				}else{
					System.out.println("Person: "+ person.getName() +" " + person.getSurname() +"  found");
				}
			}
				break;
			case 7:
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
		System.out.println("1. Open account");
		System.out.println("2. Assign person");
		System.out.println("3. Exchange currencies");
		System.out.println("4. Add new client");
		System.out.println("5. Add currency");
		System.out.println("6. Search person");
		System.out.println("7. Exit");
		System.out.println("   What do you want to do?");
		
	}
	
}
