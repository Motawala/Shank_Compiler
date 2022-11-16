package Lexer_Project;


public class VariableNode extends Node {
	
	private String name;
	private boolean isConstant;
	private Node value;
	private Tokens.Type Etype;
	private InterpreterDataType Itype;
	
	public VariableNode(String Name, Tokens.Type type, Node Value,boolean IsConstant) {
		this.isConstant= IsConstant;
		this.name = Name;
		this.value =Value;
		this.Etype = type;
	}
	
	public VariableNode(String Name, Tokens.Type type) {
		this.Etype=type;
		this.name =Name;
	}
	
	public VariableNode(String Name, InterpreterDataType type) {
		// TODO Auto-generated constructor stub
		this.name = Name;
		this.Itype = type;
	}
	
	public enum EnumType {
		Integer,
		Real
	}
	public String getName() {
		return this.name;
	}
	
	public boolean getIsConstant() {
		return  this.isConstant;
	}
	
	public Tokens.Type getEnumType() {
		return this.Etype;
	}
	
	public InterpreterDataType getIType() {
		return this.Itype;
	}
	
	public Node getValue() {
		return this.value;
	}
	
	public void setValue(Node Val) {
		this.value=Val;
	}
	
	public void setEnumType(Tokens.Type type) {
		this.Etype = type;
	}
	public String toString() {
		if(this.getIsConstant()==true) {
			return "Constant: ("+"Name: "+this.name+", Enum Type: "+this.Etype+", Value: "+this.value+", Is Constant: "+this.isConstant+")";
		}
		else {
			return "Variable: ("+"Name: "+this.name+", Enum Type: "+this.Etype+", Value: "+this.value+", Is Constant: "+this.isConstant+")";
		}
	}

}
