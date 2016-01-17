package com.epam.runner;

import com.epam.management.Memory;
import com.epam.management.MemoryManagement;

public class Runner {

	public static void main(String[] args) {
		
		int i = 0;
		while(true){
			System.out.println(i++);
			Memory memory = new Memory(512, "heap", "young");
			MemoryManagement.takeMemory(memory);
		}
	}

}
