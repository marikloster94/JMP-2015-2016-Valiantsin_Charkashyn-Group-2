package com.epam.command;

import java.util.HashMap;
import java.util.Map;

public class RequestHelper {
	
	private Map<String,Command> commands = new HashMap<String,Command>();
	
	public RequestHelper(){
		commands.put("initDAO", new InitDatabaseCommand());
		commands.put("openAccount", new OpenAccountCommand());
		commands.put("prepareOpenAccount", new PrepareOpenAccountCommand());
		commands.put("assignPerson", new AssignPersonCommand());
		commands.put("prepareAssignPerson", new PrepareAssignCommand());
		commands.put("doExchange", new DoExchangeCommand());
		commands.put("addClient", new AddPersonCommand());
		commands.put("addCurrency", new AddCurrencyCommand());
		commands.put("searchPerson", new SearchPersonCommand());
		commands.put("searchAccount", new SearchAccountCommand());
		commands.put("showAccounts", new ShowAccountsCommand());
		commands.put("showClients", new ShowClientsCommand());
	}
	
	public Command getCommand(String command){
		return commands.get(command);
	}
}
