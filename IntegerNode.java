package Lexer_Project;

public class IntegerNode extends Node {
	
	//Private Integer with just read -only access.
	private Integer IntegerNumber;
	
	/*
	 * Default Constructor for the class that takes an int number.
	 */
	public IntegerNode(int intNum) {
		this.IntegerNumber=intNum;
	}
	
	
	/*
	 * Getter method to read the private integer number.
	 */
	public int getIntegerNumber() {
		return this.IntegerNumber;
	}
	
	@Override
	/**
	 * String method to return the number.
	 */
	public String toString() {
		return "Integer["+this.IntegerNumber+"]"+"";
	}
}
