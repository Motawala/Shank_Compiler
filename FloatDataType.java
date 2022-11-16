package Lexer_Project;

public class FloatDataType extends InterpreterDataType {
	
	private float floatNumber;
	
	public FloatDataType(float floatNum) {
		this.floatNumber = floatNum;
	}
	
	public FloatDataType() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return this.floatNumber+"";
	}
	
	public void FromString(String input) {
		this.floatNumber = Float.parseFloat(input); //Parses the String to a float datatype.
	}

}
