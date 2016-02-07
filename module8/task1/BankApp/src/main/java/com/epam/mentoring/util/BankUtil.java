package com.epam.mentoring.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.AddNewElementException;
import com.epam.mentoring.exception.ExchangeException;
import com.epam.mentoring.model.Account;
import com.epam.mentoring.model.Currency;
import com.epam.mentoring.model.ExchangeRate;
import com.epam.mentoring.model.ExchangeTicket;
import com.epam.mentoring.model.Person;
import com.epam.mentoring.service.AccountService;
import com.epam.mentoring.service.CurrencyService;
import com.epam.mentoring.service.ExchangeService;
import com.epam.mentoring.service.ExchangeTicketService;
import com.epam.mentoring.service.PersonService;

public class BankUtil {

	private static Logger log = Logger.getLogger(BankUtil.class);
	private static final String DATE_REGEX = "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[.//-]([0]?[1-9]|[1][0-2])[.//-]([0-9]{4}|[0-9]{2})$";
	private static final List<String> currencies = Arrays.asList(new String []{"USD", "BYR", "EUR"});
	
	public static void menu(){
		boolean menu = true;
		while(menu){
			showMenu(); 
			Scanner sc = new Scanner(System.in);
			int value = sc.nextInt();
			switch(value){
			case 1:	
				addAccount(sc);
				break;
			case 2:
				assignPerson(sc);
				break;
			case 3:
				exchange();
				break;
			case 4:
				addPerson(sc);
				break;
			case 5:{
				CurrencyService service = new CurrencyService();
				Currency curr = new Currency();
				curr.setIdCurrency(service.gelLastId());
				sc.nextLine();
				System.out.println("Enter new shortName of currency");
				String name = sc.nextLine();
				curr.setShortName(name);
				try {
					service.addCurrency(curr);
				} catch (Exception e) {
					System.out.println("Error occured. Please see log file");
					log.error(BankUtil.class, e);
					break;
				}
			}
				break;
			case 6:{
				Person person = searchPerson(sc);
				if(person == null){
					System.out.println("Person with didn't found");
				}else{
					System.out.println("Person: "+ person.getName() +" " + person.getSurname() +"  found");
				}
			}
				break;
			case 7:{
				Account account = searchAccount(sc);
				if(account == null){
					System.out.println("Account didn't found");
				}else{
					printAccount(account, formMultiplyCurr(account));
				}
			}
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
		System.out.println("1. Open account");
		System.out.println("2. Assign person");
		System.out.println("3. Exchange currencies");
		System.out.println("4. Add new client");
		System.out.println("5. Add currency");
		System.out.println("6. Search person");
		System.out.println("7. Search account");
		System.out.println("8. Exit");
		System.out.println("   What do you want to do?");
		
	}
	
	public static HashMap<String, Double> formMultiplyCurr(Account account){
		HashMap<String, Double> multiplyCurr = new HashMap<String, Double>();
		multiplyCurr.put(account.getCurr().getShortName(), account.getValue());
		for(String currency : currencies){
			if(!multiplyCurr.containsKey(currency)){
				ExchangeService service = new ExchangeService();
				ExchangeRate rate = service.searchExchange(new Date(), account.getCurr().getShortName(), currency);
				if(rate != null){
					double value = service.calculate(rate, account.getValue());
					multiplyCurr.put(currency, value);
				}
			}
		}
		return multiplyCurr;
	}
	
	public static void printAccount(Account account, HashMap<String, Double> multiplyCurr){
		System.out.println("Account " + account.getDescription() + " with id: "+account.getId());
		System.out.println("Account value: ");
		Set<Entry<String, Double>> entry = multiplyCurr.entrySet();
		for(Entry<String, Double> key:entry){
			System.out.print(key.getKey());
			System.out.printf(" - %.0f\n" , key.getValue());
		}
		System.out.println("Account is assigned to: " + account.getPerson().getName() +" "+account.getPerson().getSurname());
	}
	
	public static Person searchPerson(Scanner sc ){
		sc.nextLine();
		System.out.println("Enter client passport number");
		String passportNumber = sc.nextLine();
		PersonService service = new PersonService();
		return service.searchPerson(passportNumber);
		
	}
	
	public static Account searchAccount(Scanner sc ){
		System.out.println("Enter account id");
		int id = sc.nextInt();
		AccountService accountService = new AccountService();
		return accountService.searchAccount(id);
	}
	
	public static void exchange(){
		ExchangeTicketService service = new ExchangeTicketService();
		AccountService accService = new AccountService();
		PersonService personService = new PersonService();
		ExchangeService exchService = new ExchangeService();
		Person person = personService.getBankPerson();
		List<ExchangeTicket> tickets = service.getExchangeTickets("new");
		for(ExchangeTicket ticket : tickets){
			List<String> currencies = new ArrayList<String>();
			currencies.add(ticket.getFromCurr().getShortName());
			currencies.add(ticket.getToCurr().getShortName());
			List<Account> userAccounts = accService.getAccounts(ticket.getClient().getPassportNumber(), currencies);
			Account bankAccount = accService.getBankAccount(ticket.getToCurr().getShortName(), person.getPassportNumber());
			ExchangeRate rate = exchService.searchExchange(new Date(), ticket.getFromCurr().getShortName(), ticket.getToCurr().getShortName());
			try {
				double result = exchService.convert(rate, bankAccount, userAccounts, ticket);
				System.out.println("Dear, "+ticket.getClient().getName() + " " + ticket.getClient().getSurname()+" you should pay "+result+ " "+ticket.getFromCurr().getShortName());
				accService.updateAccount(bankAccount);
				for(Account acc: userAccounts){
					accService.updateAccount(acc);
				}
				service.updateExchangeTicket(ticket);
			} catch (ExchangeException e) {
				log.error(BankUtil.class, e);
				break;
			}
			
		}
	}
	
	public static Person addPerson(Scanner sc){
		sc.nextLine();
		System.out.println("Enter user name");
		String name = sc.nextLine();
		System.out.println("Enter user surname");
		String surname = sc.nextLine();
		System.out.println("Enter user date of Birth");
		String date = sc.nextLine();
		PersonService service = new PersonService();
		if(!date.matches(DATE_REGEX)){
			System.out.println("Date should me in format dd/MM/yyyy");
			log.warn("Date should me in format dd/MM/yyyy");
			return null;
		}
		System.out.println("Enter user passport number");
		String passportNumber = sc.nextLine();
		Person person = new Person(name, surname, date);
		person.setPassportNumber(passportNumber);
		try {
			service.addPerson(person);
		} catch (Exception e) {
			System.out.println("Error occured. Please see log file");
			log.error(BankUtil.class, e);
			return null;
		}
		return person;
	}
	
	public static void assignPerson(Scanner sc){
		Person person = searchPerson(sc);
		if(person == null){
			System.out.println("You can't assign nonexistent person to account");
			log.warn("You can't assign nonexistent person to account");
			return;
		}
		
		Account account = searchAccount(sc);
		if(account == null){
			System.out.println("Account didn't found. You can't assign person to nonexistent account");
			log.warn("Account didn't found. You can't assign person to nonexistent account");
			return;
		}
		try {
			AccountService accountService = new AccountService();
			accountService.assignPerson(person, account);
		} catch (Exception e) {
			System.out.println("Error occured. Please see log file");
			log.error(BankUtil.class, e);
		}
	}
	
	
	public static void addAccount(Scanner sc){
		AccountService accountServ = new AccountService();
		Account acc = new Account();
		acc.setId(accountServ.getLastId());
		sc.nextLine();
		System.out.println("Enter client passport number");
		String passportNumber = sc.nextLine();
		
		PersonService service = new PersonService();
		
		Person person = service.searchPerson(passportNumber);
		if(person == null){
			System.out.println("There is not client with passport number "+passportNumber+". You should create new person");
			person = addPerson(sc);
		}
		if(person == null){
			System.out.println("Error occured while adding person. Please see log file");
			return;
		}
		System.out.println("New client successfully added");
		acc.setPerson(person);
		System.out.println("Enter short name for search currency");
		String currency = sc.nextLine();
		CurrencyService currService = new CurrencyService();
		Currency curr = currService.searchCurrency(currency);
		if(curr == null){
			System.out.println("Currency can not be null");
			log.warn("Currency can not be null");
			return;
		}
		acc.setCurr(curr);
		System.out.println("Enter description of account");
		String description = sc.nextLine();
		acc.setDescription(description);
		System.out.println("Enter startDate of account");
		String startDate = sc.nextLine();
		if(!startDate.matches(DATE_REGEX)){
			System.out.println("Date should be in format dd/MM/yyyy");
			log.warn("Date should be in format dd/MM/yyyy");
			return;
		}
		acc.setStartdDate(startDate);
		System.out.println("Enter endDate of account");
		String endDate = sc.nextLine();
		if(!endDate.matches(DATE_REGEX)){
			System.out.println("Date should be in format dd/MM/yyyy");
			log.warn("Date should be in format dd/MM/yyyy");
			return;
		}
		acc.setEndDate(endDate);
		System.out.println("Enter amount");
		Double amount = sc.nextDouble();
		acc.setValue(amount);
		accountServ.addAccount(acc);
	}
}
