package Lexer_Project;

public class VariableReferenceNode extends Node {
	
	private String name;
	
	/*
	 * Variable reference node created to handle the variable name.
	 */
	public VariableReferenceNode(String Name) {
		this.name = Name;
	}
	
	/*
	 * Default constructor
	 */
	public VariableReferenceNode() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return this.name;
	}
	/*
	 * String method to print the variable name.
	 */
	public String toString() {
		return " "+this.name;
	}

}
