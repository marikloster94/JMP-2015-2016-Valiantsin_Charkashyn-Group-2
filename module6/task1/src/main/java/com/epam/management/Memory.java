package com.epam.management;

public class Memory {

	private int size;
	private String type;
	private String subType;

	public Memory(){
		
	}
	
	public Memory (int size, String type, String subType) {
		this.size = size;
		this.type = type;
		this.subType = subType;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}
