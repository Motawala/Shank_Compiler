package Lexer_Project;

import java.util.List;

public abstract class CallableNode extends Node {
	
	protected String FunctionName;
	protected List<VariableNode> parameters;
	
	
	public CallableNode(String name, List<VariableNode> param) {
		this.FunctionName = name;
		this.parameters = param;
	}
	
	
	public void setFunctionName(String func) {
		this.FunctionName = func;
	}
	
	public void setParemeters(List<VariableNode> param) {
		this.parameters = param;
	}
	
	public CallableNode() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Function Called: "+this.FunctionName+"("+this.parameters+")";
	}

}
