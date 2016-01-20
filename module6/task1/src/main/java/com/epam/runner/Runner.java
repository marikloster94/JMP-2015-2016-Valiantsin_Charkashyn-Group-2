package com.epam.runner;

import java.util.ArrayList;
import java.util.List;

import com.epam.management.Memory;
import com.epam.management.MemoryManagement;

public class Runner {

	public static void main(String[] args) {
		List<Memory> list = new ArrayList<Memory>();
		for(int i = 0 ; i <150; i++){
			list.add(new Memory(512, "heap", "young"));
			MemoryManagement.takeMemory(new Memory(128, "heap", "young"));
		}
		System.out.println("Created "+ list.size()+ " objects");
	}

}
