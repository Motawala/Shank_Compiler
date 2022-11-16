package Lexer_Project;

import java.util.List;

public class ParameterNodeAST {
	
	private List<VariableNode> variable;

	
	public ParameterNodeAST(List<VariableNode> var) {
		this.variable = var;
	}
	
	public ParameterNodeAST() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Parameters: "+this.variable;
	}
}
