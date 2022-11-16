package Lexer_Project;

import java.util.List;

public class IfNode extends statementNode{

	private BooleanExpression condition; //Condition of the if statement
	private List<statementNode> statements; //statement in if
	private IfNode next; //else if statement
	
	/*
	 * Default constructor for the if else statement
	 */
	public IfNode(BooleanExpression Condition, List<statementNode> Statements, IfNode IFNODE) {
		this.condition = Condition;
		this.statements = Statements;
		this.next = IFNODE;
	}
	
	public List<statementNode> getStatements(){
		return this.statements;
	}
	
	public BooleanExpression getBooleanExpression() {
		return this.condition;
	}
	public IfNode() {
		// TODO Auto-generated constructor stub
	}

	public IfNode(IfNode ifNode) {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "if elsif statement ("+this.condition+") "+this.statements+" "+this.next+"";
	}
}
