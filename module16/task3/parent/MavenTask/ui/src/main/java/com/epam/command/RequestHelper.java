package com.epam.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import com.epam.service.AccountService;
import com.epam.service.PersonService;

public class RequestHelper {
	
	private Map<String,Command> commands = new HashMap<String,Command>();
	
	public RequestHelper(WebApplicationContext context){
		commands.put("openAccount", new OpenAccountCommand(context));
		commands.put("prepareOpenAccount", new PrepareOpenAccountCommand(context));
		commands.put("assignPerson", new AssignPersonCommand(context));
		commands.put("prepareAssignPerson", new PrepareAssignCommand(context));
		commands.put("doExchange", new DoExchangeCommand(context));
		commands.put("addClient", new AddPersonCommand(context));
		commands.put("addCurrency",new AddCurrencyCommand(context));
		commands.put("searchPerson", new SearchPersonCommand(context));
		commands.put("searchAccount", new SearchAccountCommand(context));
		commands.put("showAccounts", new ShowAccountsCommand(context));
		commands.put("showClients", new ShowClientsCommand(context) );
	}
	
	public Command getCommand(String command){
		return commands.get(command);
	}
}
