package Lexer_Project;

public class FloatNode extends Node {
	
	/*
	 * Private float number with read -only access.
	 */
	private float FloatNumber;
	
	/*
	 * Default constructor for the class.
	 */
	public FloatNode(float FloatNum) {
		this.FloatNumber=FloatNum;
	}
	
	/*
	 * Getter method to read the float Number.
	 */
	public float getFloatNumber() {
		return this.FloatNumber;
	}
	
	@Override
	/*
	 * String method that prints the float number.
	 */
	public String toString() {
		return this.FloatNumber+"";
	}
}
