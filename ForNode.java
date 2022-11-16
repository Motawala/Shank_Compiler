package Lexer_Project;

import java.util.List;

public class ForNode extends statementNode {

	private VariableReferenceNode variable; //Variable initialized in the condition
	private Node start; //Where to start
	private Node end; //Where to end
	private List<statementNode> statements; //Statements of the For loop
	
	/*
	 * Default constructor for the for node
	 */
	public ForNode(VariableReferenceNode var, Node Start, Node End, List<statementNode> Statements) {
		this.variable = var;
		this.start = Start;
		this.end = End;
		this.statements = Statements;
	}
	
	public ForNode() {
		// TODO Auto-generated constructor stub
	}

	public VariableReferenceNode getVariable() {
		return this.variable;
	}
	
	public Node getStart() {
		return this.start;
	}
	
	public void setStart(Node Start) {
		this.start=Start;
	}
	
	public Node getEnd() {
		return this.end;
	}
	
	public void setEnd(Node End) {
		this.end=End;
	}
	
	
	public List<statementNode> getForLoopStatements(){
		return this.statements;
	}
	
	public String toString() {
		return "For Loop: ("+this.variable+", "+this.start+", "+this.end+") "+this.statements+"";
	}
}
