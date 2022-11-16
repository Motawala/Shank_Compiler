package Lexer_Project;

public class StringNode extends Node {

	private String StringValue;
	
	public StringNode(String Stringval) {
		this.StringValue = Stringval;
	}
	
	public String getStringValue() {
		return this.StringValue;
	}
	
	public String toString() {
		return "Striing Node:["+this.StringValue+"]";
	}
}

