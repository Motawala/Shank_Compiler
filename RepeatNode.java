package Lexer_Project;

import java.util.List;

public class RepeatNode extends statementNode {
	
	private BooleanExpression booleanExpression; //Conditions of the repeat loop.
	private List<statementNode> statements; //Statements of the repeat loop
	
	/*
	 * Default constructor for the repeat loop
	 */
	public RepeatNode(BooleanExpression exp, List<statementNode> statementsInLoop) {
		this.booleanExpression = exp;
		this.statements = statementsInLoop;
	}
	
	public RepeatNode() {
		// TODO Auto-generated constructor stub
	}

	public BooleanExpression getBooleanExpression() {
		return this.booleanExpression;
	}
	
	public List<statementNode> getRepeatLoopStatement(){
		return this.statements;
	}
	
	public String toString() {
		return "Repeat Loop: ("+this.booleanExpression+") "+this.statements+"\n";
	}
}
