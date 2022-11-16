package Lexer_Project;

import java.util.List;

public class FunctionNode extends CallableNode {
	
	
	public FunctionNode(String name, List<VariableNode> param) {
		this.setFunctionName(name);
		this.setParemeters(param);
	}
	
	public FunctionNode() {
		
	}
}
