package Lexer_Project;

public class BoolNode extends Node{
	
	private boolean bool;
	
	/*
	 * Default constructor
	 */
	public BoolNode(boolean booll) {
		this.bool = booll;
	}
	
	/*
	 * getter for boolean value
	 */
	public boolean getBool() {
		return this.bool;
	}
	
	/*
	 * toString to print the boolean.
	 */
	public String toString() {
		return "Boolean["+this.bool+"]";
	}
}
