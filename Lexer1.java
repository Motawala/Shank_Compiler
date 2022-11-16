package Lexer_Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lexer1 {
	
	public static String wstate = "word";
	
	@SuppressWarnings("unused")
	//Users input
	private String Input;
		
	/*
	 * Constructor for lexer state
	 */
	
	public Lexer1(String input)
	{
		this.Input=input;
	}

	public Lexer1()
	{
	}
		
	/*
	 * This methods creates the tokens and stores them into the List.
	 * @param Input takes string input from user
	 * @returns collection of tokens in a list.
	 */
	public ArrayList<Tokens> vD(String input){
		int state=1;	//BEGIN state
		ArrayList<Tokens> tokens = new ArrayList<>();
		String Value="";// stores the value of the token temporarily.
		
		String wValue="";// stores the value of the token temporarily.
		String temp = " =+-*/,;:()%><";//special char
		char[] chararray = temp.toCharArray();

		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String, Tokens.Type> map = new HashMap();//definition of hashmap
		map.put("integer", Tokens.Type.integer);
		map.put("real", Tokens.Type.real);
		map.put("begin", Tokens.Type.begin);
		map.put("end", Tokens.Type.end);
		map.put("Variables", Tokens.Type.variables);
		map.put("constants", Tokens.Type.constants);
		map.put(":=", Tokens.Type.assign);
		map.put("if", Tokens.Type.If);
		map.put("while", Tokens.Type.While);
		map.put("for", Tokens.Type.For);
		map.put("repeat", Tokens.Type.Repeat);
		map.put("<>", Tokens.Type.NotEqualTo);
		map.put("<=", Tokens.Type.LessThanEqualTo);
		map.put(">=", Tokens.Type.GreaterThanEqualTo);
		map.put("==", Tokens.Type.EqualEqualTo);
		map.put("from", Tokens.Type.From);
		map.put("to", Tokens.Type.To);
		map.put("then", Tokens.Type.Then);
		map.put("elsif", Tokens.Type.Elsif);
		map.put("else", Tokens.Type.Else);
		map.put("mod", Tokens.Type.MOD);
		map.put("write", Tokens.Type.Write);
		map.put("read", Tokens.Type.Read);
		map.put("var", Tokens.Type.Var);
		map.put("squaroot", Tokens.Type.SquareRoot);
		map.put("getRandom", Tokens.Type.getRandom);
		map.put("IntegerToReal", Tokens.Type.IntegerToReal);
		map.put("RealToInteger", Tokens.Type.RealToInteger);
		map.put("true", Tokens.Type.True);
		map.put("false", Tokens.Type.False);
		//runs through every character of the input
		for (int i=0; i<input.length(); i++) {
			char character=input.charAt(i);
			if (wstate == "word") { //if state is word
				wValue+=character;
				if (map.get(wValue) != null) {//if token is in hashmap
					tokens.add(new Tokens(wValue,map.get(wValue)));
					wValue = "";
				}
				if (wValue.length()>1 && check(chararray,wValue.charAt(wValue.length()-1))) { //if number or identifier
					if (checkDigit(wValue.substring(0, wValue.length()-1).toCharArray())){//string is number
						tokens.add(new Tokens(wValue.substring(0, wValue.length()-1),Tokens.Type.number));
						wValue = wValue.substring(wValue.length()-1);						
					}
					else {//string is identifier
						if(wValue.substring(0, wValue.length()-1).charAt(0)=='*' || (wValue.substring(0, wValue.length())==" ")) { //Comments in the codes are ignored.
							wValue="";
							break;
						}
						else if(wValue.substring(0,wValue.length()-1).charAt(0)=='"') {
							tokens.add(new Tokens(wValue.substring(0, wValue.length()-1),Tokens.Type.String));
						}
						else if(wValue.substring(0,wValue.length()-1).charAt(0)=='\'') {
							tokens.add(new Tokens(wValue.substring(0, wValue.length()-1),Tokens.Type.Character));
						}
						else {
							tokens.add(new Tokens(wValue.substring(0, wValue.length()-1),Tokens.Type.identifier));
							wValue = wValue.substring(wValue.length()-1);	
						}
					}
				}
				else if ((wValue.length()>=1 && input.length()-1==i)) {//if the last string is number or identifier
					if (checkDigit(wValue.substring(0, wValue.length()-1).toCharArray())){
						tokens.add(new Tokens(wValue.substring(0, wValue.length()),Tokens.Type.number));
						wValue = "";
					}
					else if(Character.isDigit(character)) { //if the number is a single digit.
						tokens.add(new Tokens(character+"",Tokens.Type.number));
						wValue = "";
					}
					else { //if the last token is an identifier.
						if(check(chararray,wValue.charAt(wValue.length()-1))) {
							wValue = wValue.charAt(wValue.length()-1)+"";
							
						}
						else {
							if(wValue.substring(0,wValue.length()).charAt(0)=='"') {
								tokens.add(new Tokens(wValue.substring(0, wValue.length()-1),Tokens.Type.String));
							}
							else if(wValue.substring(0,wValue.length()).charAt(0)=='\'') {
								tokens.add(new Tokens(wValue.substring(0, wValue.length()-1),Tokens.Type.Character));
							}else {
								tokens.add(new Tokens(wValue.substring(0, wValue.length()),Tokens.Type.identifier));
								wValue = wValue.substring(wValue.length()-1);
							}
						}
					}
				}
				switch(wValue) {
					//Adds the token for the left paren
					case "(":
						if(input.charAt(i+1)=='*'){
							wValue="";
							break;
						}
						tokens.add(new Tokens(wValue,Tokens.Type.leftParen));
						wValue = "";
						break;
					//Adds the token for the right paren.
					case ")":
						if(input.charAt(i-1)=='*') {
							wValue="";
							break;
						}
						tokens.add(new Tokens(wValue,Tokens.Type.rightParen));
						wValue = "";
						break;
					//Adds the token for semi colon
					case ";":
						tokens.add(new Tokens(wValue,Tokens.Type.semicolon));
						wValue = "";
						break;
					//Adds the token for colon and assign
					case ":":
						if(input.charAt(i+1)=='=') {
							wValue+=input.charAt(i+1);
							tokens.add(new Tokens(wValue,Tokens.Type.assign));
							wValue="";
							break;
						}else {
							tokens.add(new Tokens(wValue,Tokens.Type.colon));
							wValue = "";
							break;
						}
					//Adds the token for equals and assign
					case "=":
						if(input.charAt(i-1)==':' || input.charAt(i+1)=='=') {
							wValue="";
							break;
						}else {
							tokens.add(new Tokens(wValue,Tokens.Type.equal));
							wValue = "";
							break;
						}
					//Adds the token for comma.
					case ",":
						tokens.add(new Tokens(wValue,Tokens.Type.comma));
						wValue = "";
						break;
					//Ignores space.
					case " ":
						wValue = "";
						break;
					//Adds the token for define.
					case "define":
						tokens.add(new Tokens(wValue,Tokens.Type.define));
						wValue = "";
						break;
					//Adds the token for times operator.
					case "*":
						if(input.charAt(i+1)==')') {
							break;
						}
						else if(input.charAt(i-1)=='(') {
							break;
						}
						else {
							tokens.add(new Tokens(wValue,Tokens.Type.TIMES));
							wValue = "";
						}
						break;
					//Adds the token for plus operator.
					case "+":
						tokens.add(new Tokens(wValue,Tokens.Type.PLUS));
						wValue="";
						break;
					//Adds the token for minus operator.
					case"-":
						tokens.add(new Tokens(wValue,Tokens.Type.MINUS));
						wValue="";
						break;
					//Adds the token for division operator.
					case "/":
						tokens.add(new Tokens(wValue,Tokens.Type.DIVIDE));
						wValue="";
						break;
					//Adds the token for mod operator.
					case "%":
						tokens.add(new Tokens(wValue,Tokens.Type.MOD));
						wValue="";
						break;
					//Adds the token for Greater than operator.
					case ">":
						if(input.charAt(i+1)=='=') {
							break;
						}
						tokens.add(new Tokens(wValue,Tokens.Type.GreaterThan));
						wValue="";
						break;
					//Adds the token for the Less than operator.
					case "<":
						if(input.charAt(i+1)=='>' || input.charAt(i+1)=='=') {
							break;
						}
						tokens.add(new Tokens(wValue,Tokens.Type.LessThan));
						wValue="";
						break;

				}
			}
			else {
				/*switch(state) {
				case 1:// BEGIN state
					if(Character.isDigit(character)) { //Checks number and goes to number state
						Value+=character;
						state=3;
						break;
					}
					else if(character=='+'|| character=='-') {// Checks operators and goes to +,- state
						Value+=character;
						state=2;
						break;
					}
					else if(character=='.') {// Checks decimal and goes to decimal number state
						Value+=character;
						state=6;
						break;
					}else {
						throw new LexerException("Invalid Input.");
					}
				case 2:// +,- state
					if(Character.isDigit(character)) {
						Value+=character;
						state=3;
						break;
					}
					else if(character=='.') {
						Value+=character;
						state=6;
						break;
					}
				case 3:// NUMBER state
					if(Character.isDigit(character)){
						Value+=character;
						state=3;
						if(Character.isDigit(character) && input.length()-1==i) {
							tokens.add(new Tokens(Value,Tokens.Type.number));//Add token if its a number
							Value="";
						}
						break;
					}
					if(character=='+') {
						tokens.add(new Tokens(Value,Tokens.Type.number));// Adds token if its a number
						tokens.add(new Tokens(character+"",Tokens.Type.number));//Adds token if its a + operator
						Value="";
						state=3;
						break;
					}
					else if(character=='-') {
						tokens.add(new Tokens(Value,Tokens.Type.number));// Adds token if its a number
						tokens.add(new Tokens(character+"",Tokens.Type.MINUS));//Adds token if its a - operator
						Value="";
						state=3;
						break;	
					}
					else if(character=='*') {
						tokens.add(new Tokens(Value,Tokens.Type.number));// Adds token if its a number
						tokens.add(new Tokens(character+"",Tokens.Type.TIMES));// Adds token if its a * operator
						Value="";
						state=3;
						break;
					}
					else if(character=='/') {
						tokens.add(new Tokens(Value,Tokens.Type.number));// Adds token if its a number
						tokens.add(new Tokens(character+"",Tokens.Type.DIVIDE));// Adds token if its a / operator
						Value="";
						state=3;
						break;
					}
					else if(character==' ') {
						Value+="";
						tokens.add(new Tokens(Value,Tokens.Type.number));// Adds token if its a number
						Value="";
						state=5;
						break;
					}
					else if(character=='.') {
						Value+=character;
						state=4;
						break;
					}
					else {
						throw new LexerException("Invalid Expression");
					}
				case 4://DECIMAL NUMBER state
					if(Character.isDigit(character)) {
						Value+=character;
						state=4;
						if(Character.isDigit(character) && input.length()-1==i) {
							tokens.add(new Tokens(Value,Tokens.Type.number));
							Value="";
						}
						break;
					}else if(character==' ') {
						Value="";
						state=5;
						break;
					}else if(character=='+'|| input.length()-1==i){
						//Value+=character;
						state=1;
						tokens.add(new Tokens(Value,Tokens.Type.number));
						tokens.add(new Tokens(character+"",Tokens.Type.PLUS));
						Value="";
						break;
					}else if(character=='-' || input.length()-1==i) {
						tokens.add(new Tokens(Value,Tokens.Type.number));
						tokens.add(new Tokens(character+"",Tokens.Type.MINUS));
						state=1;
						Value="";
						break;
					}else if(character=='*' || input.length()-1==i) {
						tokens.add(new Tokens(Value,Tokens.Type.number));
						tokens.add(new Tokens(character+"",Tokens.Type.TIMES));
						state=1;
						Value="";
						break;
					}else if(character=='/' || input.length()-1==i) {
						tokens.add(new Tokens(Value,Tokens.Type.number));
						tokens.add(new Tokens(character+"",Tokens.Type.DIVIDE));
						state=1;
						Value="";
						break;
					}
					else if(character=='.') {
						Value+=character;
						state=4;
						break;
					}
					else {
						throw new LexerException("Invalid Input.");
					}
				case 5://SPACE state
					if(character=='+') {
						Value+=character;
						tokens.add(new Tokens(character+"",Tokens.Type.PLUS));
						state=1;
						Value="";
						break;
					}else if(character=='-') {
						Value+=character;
						tokens.add(new Tokens(character+"",Tokens.Type.MINUS));
						state=1;
						Value="";
						break;
					}else if(character=='*') {
						Value+=character;
						tokens.add(new Tokens(character+"",Tokens.Type.TIMES));
						state=1;
						Value="";
						break;
					}else if(character=='/') {
						Value+=character;
						tokens.add(new Tokens(character+"",Tokens.Type.DIVIDE));
						state=1;
						Value="";
						break;
					}
					else if(character==' ') {
						Value="";
						state=5;
						break;
					}else if(Character.isDigit(character)) {
						Value+=character;
						state=3;
						break;
					}else {
						throw new LexerException("Invalid Input");
					}
				case 6://DECIMAL state
					if(Character.isDigit(character)) {
						Value+=character;
						state=4;
						break;
					}else {
						throw new LexerException("Invalid Input.");
					}
				}*/
			}
			
		}
		tokens.add(new Tokens("",Tokens.Type.EOL));
		return tokens;
	}
	//this function is for checking if char is in above char array
	public boolean check(char[] chararray, char ch) {
		boolean contains = false;
		for (char c : chararray) {
		    if (c == ch) {
		        contains = true;
		        break;
		    }
		}
		return contains;
		
	}
	//this function is for checking if chararray is digit
	public boolean checkDigit(char[] chararray) {
		boolean contains = false;
		for (char c : chararray) {
		    if (Character.isDigit(c)) {
		        contains = true;
		    }
		    else {
		    	contains = false;
		    }
		}
		return contains;
		
	}

}
