package Lexer_Project;

import java.util.List;

public class ElseNode extends IfNode{
		
	private List<statementNode> statements;//Statements of the Else condition
	
	public ElseNode() {}
	
	public ElseNode(List<statementNode> statements) {
		this.statements = statements;
	}
	
	public List<statementNode> getStatements(){
		return this.statements;
	}
	public String toString() {
		return "Else statements: "+this.statements+"";
	}
}
