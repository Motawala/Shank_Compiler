package Lexer_Project;

public class CharNode extends Node{
	
	private char characterValue;
	
	public CharNode(char charval) {
		this.characterValue = charval;
	}
	
	public char getCharValue() {
		return this.characterValue;
	}
	
	public String toString() {
		return "Character Node:["+this.characterValue+"]";
	}
}
