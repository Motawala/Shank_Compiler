package Lexer_Project;


public class assignmentNode extends statementNode{
	
	private Node  expression;
	private VariableReferenceNode variableRef;
	
	/*
	 * Default constructor for the assignment node.
	 */
	public assignmentNode(VariableReferenceNode var, Node exp) {
		this.expression = exp;
		this.variableRef = var;
	}
	
	public assignmentNode() {
		// TODO Auto-generated constructor stub
	}

	
	public VariableReferenceNode getVariableReferenceNode() {
		return this.variableRef;
	}
	
	public Node getExpression() {
		return this.expression;
	}
	/*
	 * This method prints the assignment node.
	 */
	@Override
	public String toString() {
		return "Assignment: "+this.variableRef+"="+this.expression+"";
	}

}
