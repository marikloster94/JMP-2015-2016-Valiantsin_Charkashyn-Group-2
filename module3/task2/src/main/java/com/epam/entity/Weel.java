package com.epam.entity;

public class Weel {

	private String weelType;

	public Weel() {

	}

	public String getWeelType() {
		return weelType;
	}

	public void setWeelType(String weelType) {
		this.weelType = weelType;
	}

	@Override
	public String toString() {
		return "Weel [weelType=" + weelType + "]";
	}

}
