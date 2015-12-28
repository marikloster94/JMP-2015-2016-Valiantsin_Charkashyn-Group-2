package com.epam.congratulation;

public class Congratulation {

	public void congratulate(String day){
		System.out.println("Congrats!");
		System.out.println("With " + day);
	}
	
	public String createNewYear(){
		return "New Year";
	}
	
	public String createHoliday(String name, String month, int day){
		StringBuilder builder = new StringBuilder();
		builder.append(name).append(" ( "+day+" "+month+" )");
		return builder.toString();
	}
}
