package Lexer_Project;

import java.util.List;

public class WhileNode extends statementNode{
	
	private BooleanExpression booleanExp; //Condition of the while loop
	private List<statementNode> statements; //Collection of statements in the loop
	
	/*
	 * Default constructor for the while loop.
	 */
	public WhileNode(BooleanExpression exp, List<statementNode> statementsList) {
		this.booleanExp = exp;
		this.statements = statementsList;
	}
	
	public WhileNode() {
		// TODO Auto-generated constructor stub
	}

	public BooleanExpression getBooleanExpression() {
		return this.booleanExp;
	}
	
	public List<statementNode> getWhileLoopStatements(){
		return this.statements;
	}
	
	
	public String toString() {
		return "While loop: ("+this.booleanExp+") "+this.statements;
	}
}
