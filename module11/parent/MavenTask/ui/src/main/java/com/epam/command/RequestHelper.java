package com.epam.command;

import java.util.HashMap;
import java.util.Map;

public class RequestHelper {
	
	private Map<String,Command> commands = new HashMap<String,Command>();
	
	public RequestHelper(){
		commands.put("initDAO", new InitDatabaseCommand());
		commands.put("openAccount", new InitDatabaseCommand());
		commands.put("assignPerson", new InitDatabaseCommand());
		commands.put("doExchange", new InitDatabaseCommand());
		commands.put("addPerson", new AddPersonCommand());
		commands.put("addCurrency", new InitDatabaseCommand());
		commands.put("searchPerson", new SearchPersonCommand());
		commands.put("searchAccount", new InitDatabaseCommand());
	}
	
	public Command getCommand(String command){
		return commands.get(command);
	}
}
