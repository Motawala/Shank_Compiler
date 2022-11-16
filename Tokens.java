package Lexer_Project;

public class Tokens {

	private String value;
	private Type Enumtype;
	
	/*
	 * Enum method for the  tokens
	 */
	public enum Type{
		PLUS ,
		MINUS ,
		DIVIDE ,
		TIMES ,
		EOL,
		identifier, define, leftParen, rightParen, integer, real, begin, end, semicolon, colon, equal, comma, variables, constants, assign, number,
		If, Then, Else, Elsif, For, From, To, While, Repeat, Until, MOD, GreaterThan , LessThan , GreaterThanEqualTo, LessThanEqualTo, EqualEqualTo, NotEqualTo, Write,Read, Var, IntegerToReal,RealToInteger,getRandom,SquareRoot,
		True, False, String, Character;
		
	}
	
	/*
	 * Default token constructor for String,enums.
	 */
	@SuppressWarnings("static-access")
	public Tokens(String Value, Type Type)
	{
		this.value=Value;
		this.Enumtype=Type;
	}
	
	@SuppressWarnings("static-access")
	public Tokens(Type enums) {
		this.Enumtype=enums;
	}
	/*
	 * Token constructor for string.
	 */
	@SuppressWarnings("static-access")
	public Tokens(String Value)
	{
		this.value=Value;
	}
	
	public Tokens() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * gets the value instance.
	 */
	@SuppressWarnings("static-access")
	public String getValue()
	{
		return this.value;
	}
	
	public Type getType() {
		return this.Enumtype;
	}
	/*
	 * returns the token as a string.
	 */
	public String toString()
	{
		if (Lexer1.wstate=="word") {
			if(this.Enumtype==Type.identifier || this.Enumtype==Type.number ) {
				return this.Enumtype+"("+this.value+")";
			}
			else {
				return this.Enumtype+"";
			}
		}
		else {
			return this.Enumtype+"("+this.value+")";
		}
		
	}
}
