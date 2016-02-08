package com.epam.mentoring.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

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
import com.epam.mentoring.thread.ExchangeThread;

public class BankUtil {

	private static Logger log = Logger.getLogger(BankUtil.class);
	private static final String DATE_REGEX = "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[.//-]([0]?[1-9]|[1][0-2])[.//-]([0-9]{4}|[0-9]{2})$";
	private static final List<String> currencies = Arrays.asList(new String []{"USD", "BYR", "EUR"});
	
	public static void menu(){
		try {
			SQLUtil.createDb();
		} catch (Exception e) {
			System.out.println("Error occured while init db. Please see log file");
			log.error(BankUtil.class, e);
			return;
		}
		boolean menu = true;
		while(menu){
			showMenu(); 
			Scanner sc = new Scanner(System.in);
			int value = sc.nextInt();
			switch(value){
			case 1:	
				try {
					addAccount(sc);
				} catch (SQLException e) {
					log.error(BankUtil.class, e);
				}
				break;
			case 2:
				assignPerson(sc);
				break;
			case 3:
				try {
					exchange();
				} catch (SQLException ex) {
					log.error(BankUtil.class, ex);
				}
				break;
			case 4:
				addPerson(sc);
				break;
			case 5:{
				CurrencyService service = new CurrencyService();
				Currency curr = new Currency();
				sc.nextLine();
				System.out.println("Enter new shortName of currency");
				String name = sc.nextLine();
				curr.setShortName(name);
				try {
					curr.setIdCurrency(service.getLastId());
					service.addCurrency(curr);
				} catch (Exception e) {
					System.out.println("Error occured. Please see log file");
					log.error(BankUtil.class, e);
					break;
				}
			}
				break;
			case 6:{
				Person person = null;
				try {
					person = searchPerson(sc);
				} catch (SQLException e) {
					log.error(BankUtil.class, e);
				}
				if(person == null){
					System.out.println("Person didn't found");
				}else{
					System.out.println("Person: "+ person.getName() +" " + person.getSurname() +"  found");
				}
			}
				break;
			case 7:{
				Account account = null;
				try {
					account = searchAccount(sc);
				} catch (SQLException e) {
					log.error(BankUtil.class, e);
				}
				if(account == null){
					System.out.println("Account didn't found");
				}else{
					try {
						printAccount(account, formMultiplyCurr(account));
					} catch (SQLException e) {
						log.error(BankUtil.class, e);
					}
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
	
	public static HashMap<String, Double> formMultiplyCurr(Account account) throws SQLException{
		HashMap<String, Double> multiplyCurr = new HashMap<String, Double>();
		multiplyCurr.put(account.getCurr().getShortName(), account.getValue().setScale(2, RoundingMode.HALF_UP).doubleValue());
		for(String currency : currencies){
			if(!multiplyCurr.containsKey(currency)){
				ExchangeService service = new ExchangeService();
				ExchangeRate rate = service.searchExchange(new Date(), account.getCurr().getShortName(), currency);
				if(rate != null){
					double value = service.calculate(rate, account.getValue().setScale(2, RoundingMode.HALF_UP).doubleValue());
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
	
	public static Person searchPerson(Scanner sc ) throws SQLException{
		sc.nextLine();
		System.out.println("Enter client passport number");
		String passportNumber = sc.nextLine();
		PersonService service = new PersonService();
		return service.searchPerson(passportNumber);
		
	}
	
	public static Account searchAccount(Scanner sc ) throws SQLException{
		System.out.println("Enter account id");
		int id = sc.nextInt();
		AccountService accountService = new AccountService();
		return accountService.searchAccount(id);
	}
	
	public static void exchange() throws SQLException{
		ExecutorService service = Executors.newFixedThreadPool(2);
		ExchangeTicketService excService = new ExchangeTicketService();
		List<ExchangeTicket> exchangeTickets = excService.getExchangeTickets("new");
		List<ExchangeThread> tickets = new ArrayList<ExchangeThread>();
		if(exchangeTickets.size() == 0){
			System.out.println("There is no exchange tickets");
			return;
		}
		tickets.add(new ExchangeThread(exchangeTickets.get(0)));
		tickets.add(new ExchangeThread(exchangeTickets.get(1)));
		tickets.add(new ExchangeThread(exchangeTickets.get(2)));
		tickets.add(new ExchangeThread(exchangeTickets.get(3)));
		try {
			List<Future<String>> results = service.invokeAll(tickets);
			for(Future<String> result: results){
				System.out.println(result.get());
			}
		} catch (InterruptedException e) {
			log.error(e);
		} catch (ExecutionException e) {
			log.error(e);
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
		Person person = null;
		try {
			person = searchPerson(sc);
		} catch (SQLException e) {
			log.error(BankUtil.class, e);
		}
		if(person == null ){
			System.out.println("You can't assign nonexistent person to account");
			log.warn("You can't assign nonexistent person to account");
			return;
		}
		
		Account account = null;
		try {
			account = searchAccount(sc);
		} catch (SQLException e) {
			System.out.println("Error occured. Please see log file");
			log.error(BankUtil.class, e);
		}
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
	
	
	public static void addAccount(Scanner sc) throws SQLException{
		AccountService accountServ = new AccountService();
		Account acc = new Account();
		sc.nextLine();
		System.out.println("Enter client passport number");
		String passportNumber = sc.nextLine();
		
		PersonService service = new PersonService();
		
		Person person = null;
		try {
			person = service.searchPerson(passportNumber);
		} catch (SQLException e) {
			log.error(BankUtil.class, e);
		}
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
		acc.setValue(new BigDecimal(amount));
		accountServ.addAccount(acc);
	}
}
