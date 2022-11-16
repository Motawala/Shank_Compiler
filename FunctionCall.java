package Lexer_Project;

import java.util.List;

public class FunctionCall extends statementNode {
	
	public String FunctionName;
	public List<VariableNode> parameters;
	
	
	public FunctionCall(String name, List<VariableNode> param) {
		this.FunctionName = name;
		this.parameters = param;
	}
	
	public FunctionCall() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return this.FunctionName;
	}
	
	
	public List<VariableNode> getFunctionParameters(){
		return this.parameters;
	}
	@Override
	public String toString() {
		return "Function Called: "+this.FunctionName+"("+this.parameters+")";
	}

}
