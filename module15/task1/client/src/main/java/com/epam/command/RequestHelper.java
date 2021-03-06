package com.epam.command;

import java.util.HashMap;
import java.util.Map;

public class RequestHelper {
	
	private Map<String,Command> commands = new HashMap<String,Command>();
	
	public RequestHelper(){
		
		commands.put("addPerson", new AddPersonCommand());
		commands.put("showPersons", new ShowPersonsCommand());
		commands.put("searchPerson", new SearchPersonCommand());
		commands.put("downloadLogo", new DownloadLogoCommand());
		commands.put("delete", new DeleteCommand());
		commands.put("showUpdateWidow", new ShowUpdateWindow());
		commands.put("update", new UpdateCommand());
		commands.put("upload", new UploadCommand());
	}
	
	public Command getCommand(String command){
		return commands.get(command);
	}
}
