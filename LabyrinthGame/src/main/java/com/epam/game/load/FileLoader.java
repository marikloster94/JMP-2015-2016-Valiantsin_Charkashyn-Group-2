package com.epam.game.load;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileLoader implements Loader {

	public List<String> load() {
		String file ="src/resources/labyrinth.txt";        
		List<String> inf = new ArrayList<String>();
		BufferedReader br=null;
	    //reading   
	    try{
	        InputStream ips=new FileInputStream(file); 
	        InputStreamReader ipsr=new InputStreamReader(ips);
	        br=new BufferedReader(ipsr);
	        String line;
	        while ((line=br.readLine())!=null){
	        	inf.add(line);
	        }
	    }       
	    catch (Exception e){
	        System.out.println(e.toString());
	    } finally{
	    	if(br!=null){
	    		try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
	    return inf;
	}

}
