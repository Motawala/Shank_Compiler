package Lexer_Project;

import java.util.List;

public class FunctionAST extends Node {

	private String name; //Name of the function
	private List<VariableNode> parameters; //Parameters of the function
	private List<VariableNode> localvariables; //local variables of the function
	private List<statementNode> statements; //Statements in the function
	
	/*
	 * Default constructor for the function
	 */
	public FunctionAST(String f, List<VariableNode> param, List<VariableNode> localVar, List<statementNode> Statements) {
		this.name=f;
		this.parameters=param;
		this.localvariables=localVar;
		this.statements = Statements;
	}
	
	public String getFunction() {
		return this.name;
	}
	
	public List<VariableNode> getParameters(){
		return this.parameters;
	}
	
	public List<VariableNode> getLocalvariables(){
		return this.localvariables;
	}
	
	public List<statementNode> getStatements(){
		return this.statements;
	}
	@Override
	public String toString() {
		return "Function "+this.name+" "+"(parameters: "+this.parameters+" "+"Local Variables: "+this.localvariables+" "+"Statements: "+this.statements+"";
	}
}
