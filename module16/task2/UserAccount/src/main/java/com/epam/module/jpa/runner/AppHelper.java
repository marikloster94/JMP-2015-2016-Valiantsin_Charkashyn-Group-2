package com.epam.module.jpa.runner;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.epam.module.jpa.entity.Account;
import com.epam.module.jpa.entity.Person;
import com.epam.module.jpa.service.AccountService;
import com.epam.module.jpa.service.PersonService;

public class AppHelper {

	public static void showMenu() {
		System.out.println("1. Open account");
		System.out.println("2. Delete account");
		System.out.println("3. Add new client");
		System.out.println("4. Show people");
		System.out.println("5. Delete client");
		System.out.println("6. Exit");
		System.out.println("   What do you want to do?");
	}

	public static void workWithMenu(AccountService accServ,
			PersonService perServ) {
		boolean menu = true;
		while (menu) {
			showMenu();
			Scanner sc = new Scanner(System.in);
			int value = sc.nextInt();
			switch (value) {
			case 1:
				Account acc = addAccount(sc, accServ, perServ);
				showAccount(acc);
				break;
			case 2:
				deleteAccount(sc, accServ);
				break;
			case 3:
				Person person = addPerson(sc, perServ);
				showPerson(person);
				break;
			case 4:
				showPeople(perServ);
				break;
			case 5:
				deleteClient(sc, perServ);
				break;
			case 6:
				System.out.println("Bye");
				menu = false;
				break;
			default:
				System.out.println("Please, choose another case");
				showMenu();
			}
		}
	}

	public static Person addPerson(Scanner sc, PersonService service) {
		sc.nextLine();
		System.out.println("Enter user name");
		String name = sc.nextLine();
		System.out.println("Enter user surname");
		String surname = sc.nextLine();
		System.out.println("Enter user age");
		String stringAge = sc.nextLine();
		if (!stringAge.matches("\\d+")) {
			System.out.println("Age value should be integer");
			return null;
		}
		int age = Integer.parseInt(stringAge);
		System.out.println("Enter user passport number");
		String passportNumber = sc.nextLine();
		Person person = new Person();
		person.setPassNumber(passportNumber);
		person.setName(name);
		person.setSurname(surname);
		person.setAge(age);

		return service.add(person);
	}

	public static Account addAccount(Scanner sc, AccountService service,
			PersonService perServ)  {
		Account acc = new Account();
		sc.nextLine();
		System.out.println("Enter client passport number");
		String passportNumber = sc.nextLine();

		Person person = null;
		person = perServ.searchPerson(passportNumber);

		if (person == null) {
			System.out.println("There is not client with passport number "
					+ passportNumber + ". You should create new person");
			person = addPerson(sc, perServ);
		}
		if (person == null) {
			System.out.println("Error occured while adding person.");
			return null;
		}

		acc.setClient(person);
		System.out.println("Enter description of account");
		String description = sc.nextLine();
		acc.setName(description);
		System.out.println("Enter amount");
		Double amount = sc.nextDouble();
		acc.setMoney(amount);
		System.out.println("Enter account number");
		int number = sc.nextInt();
		acc.setNumber(number);
		return service.add(acc);
	}

	public static Person searchPerson(Scanner sc, PersonService service) {
		sc.nextLine();
		System.out.println("Enter client passport number");
		String passportNumber = sc.nextLine();
		return service.searchPerson(passportNumber);

	}

	public static void deleteClient(Scanner sc, PersonService service) {
		Person person = searchPerson(sc, service);
		if (person == null) {
			System.out.println("There is not client with such passport number ");
			return;
		}
		System.out.println("Person was found with id: " + person.getPersonID());
		service.delete(person);
	}

	public static void deleteAccount(Scanner sc, AccountService service) {
		sc.nextLine();
		System.out.println("Enter account id:");
		int accountId = sc.nextInt();
		Account acc = new Account();
		acc.setAccountID(accountId);
		service.delete(acc);

	}

	public static void showPeople(PersonService service) {
		List<Person> people = service.getPeople();
		if(people.size() == 0){
			System.out.println("There is no people in db");
		}
		for (Person person : people) {
			System.out.println(person.getName() + " " + person.getSurname());
			if (person.getAccounts().size() == 0) {
				System.out.println("This person does not have accounts");
				break;
			}
			System.out.println("Accounts:");
			for (Account acc : person.getAccounts()) {
				System.out.println(acc.getName() + " " + acc.getMoney());
			}
		}
	}

	public static void showPerson(Person person) {
		if(person == null){
			System.out.println("No person to show!");
			return;
		}
		System.out.println(person.getName() + " " + person.getSurname());
		if(person.getAccounts() == null ) {
			System.out.println("This person does not have accounts");
			return;
		}
		if (person.getAccounts().size() == 0) {
			System.out.println("This person does not have accounts");
			return;
		}
		System.out.println("Accounts:");
		for (Account acc : person.getAccounts()) {
			System.out.println(acc.getName() + " " + acc.getMoney());
		}

	}
	
	public static void showAccount(Account acc) {
		if(acc == null){
			System.out.println("No account to show!");
			return;
		}
		System.out.println(acc.getName() + " " + acc.getMoney());
		if(acc.getClient() == null){
			return;
		}
		System.out.println(acc.getClient().getName() + " " + acc.getClient().getSurname());
	}
}
