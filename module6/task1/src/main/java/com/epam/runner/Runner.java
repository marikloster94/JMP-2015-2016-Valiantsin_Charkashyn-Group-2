package com.epam.runner;

import java.util.ArrayList;
import java.util.List;

import com.epam.management.Memory;
import com.epam.management.MemoryManagement;

public class Runner {

	public static void main(String[] args) {
		List<Memory> list = new ArrayList<Memory>();
		while(true){
			System.out.println(list.size());
			list.add(new Memory(512, "heap", "young"));
			MemoryManagement.takeMemory(new Memory(128, "heap", "young"));
		}
	}

}
