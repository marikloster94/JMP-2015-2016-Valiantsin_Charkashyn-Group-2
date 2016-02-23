package com.epam.command;

import java.util.HashMap;
import java.util.Map;

public class RequestHelper {
	
	private Map<String,Command> commands = new HashMap<String,Command>();
	
	public RequestHelper(){
		commands.put("initDAO", new InitDatabaseCommand());
		commands.put("openAccount", new InitDatabaseCommand());
		commands.put("assignPerson", new AssignPersonCommand());
		commands.put("prepareAssignPerson", new PrepareAssignCommand());
		commands.put("doExchange", new InitDatabaseCommand());
		commands.put("addClient", new AddPersonCommand());
		commands.put("addCurrency", new AddCurrencyCommand());
		commands.put("searchPerson", new SearchPersonCommand());
		commands.put("searchAccount", new SearchAccountCommand());
	}
	
	public Command getCommand(String command){
		return commands.get(command);
	}
}
