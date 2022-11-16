package Lexer_Project;

import java.util.Collection;

public abstract class BuiltInFunction extends CallableNode{
	
	protected boolean variadic;
	
	public abstract void Execute(Collection<InterpreterDataType> InterpreterType);
	
	public String toString() {
		String param="";
		for(VariableNode variables: this.parameters) {
			param+= variables;
		}
		return "Built-In_Function: "+this.FunctionName+", Is Variadic:"+this.variadic+", Parameters: "+param;
	} 
	
	

}
