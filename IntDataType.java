package Lexer_Project;

public class IntDataType extends InterpreterDataType{
	
	private int intNumber;
	
	public IntDataType(int intNum) {
		this.intNumber = intNum;
	}
	
	public IntDataType() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return this.intNumber+"";
	}
	
	public void FromString(String input) {
		this.intNumber = Integer.parseInt(input); //Parse the String to integer number.
	}

}
