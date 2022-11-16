package Lexer_Project;

public class BooleanExpression extends Node{
	private Node LeftExpression; //Left expression of the condition
	private Node RightExpression; //Right expression of the condition
	private Tokens.Type condition; //operator
	
	
	/*
	 * Default constructor
	 */
	public BooleanExpression(Node left, Tokens.Type condition, Node right) {
		this.LeftExpression = left;
		this.RightExpression = right;
		this.condition = condition;
	}
	
	/*
	 * Empty constructor to initialize the variable
	 */
	public BooleanExpression() {
		// TODO Auto-generated constructor stub
	}

	public Node getLeftExpression() {
		return this.LeftExpression;
	}
	
	public Node getRightExpression() {
		return this.RightExpression;
	}
	
	public Tokens.Type getCondition(){
		return this.condition;
	}
	

	@Override
	public String toString() {
		
		return "Boolean Expression"+this.LeftExpression+" "+this.condition+" "+this.RightExpression+"";
	}
}
