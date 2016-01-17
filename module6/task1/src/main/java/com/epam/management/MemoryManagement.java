package com.epam.management;

public class MemoryManagement {

	public static void takeMemory(Memory memory){
		memory = new Memory(512, "heap", "old");		
	}
	
	
	public static int getUsingMemory(Memory memory){
		memory = new Memory(128, "heap", "young");
		return memory.getSize();
	}
	
	public static Memory getMemoryClone(Memory memory){
		memory = new Memory(memory.getSize(), memory.getType(), memory.getSubType());
		return memory;
	}
	
}
