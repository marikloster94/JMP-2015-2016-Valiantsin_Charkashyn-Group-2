package com.epam.congratulation;

public class Congratulation {

	public void congratulate(String day){
		System.out.println("It is " + day);
	}
	
	public String createNewYear(){
		return "New Year party time";
	}
	
	public String createHoliday(String name, String month, int day){
		StringBuilder builder = new StringBuilder();
		builder.append(name).append(" ( "+day+" "+month+" )");
		return builder.toString();
	}
}
