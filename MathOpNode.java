package Lexer_Project;
/*
 * Create a new class called MathOpNode that also derives
 *  from Node. MathOpNode must have an enum indicating which
 *   math operation (add, subtract, multiply, divide) the 
 *   class represents. The enum must be read-only. The class
 *    must have two references (left and right) to the Nodes 
 *    that will represent the operands. These references must
 * also be read-only and an appropriate constructor and 
 * ToString() must be created.
 */
public class MathOpNode extends Node{
	
	//Operator
	private Operators operator;
	//Right element of the Node.
	private Node rightNode;
	//Left element of the Node.
	private Node leftNode;
	
	/*
	 * Enums indicating which math operation to be performed.
	 */
	public enum Operators {
		ADD,
		SUBTRACT,
		MULTIPLY,
		DIVIDE,
		MOD
	}
	
	/*
	 * This method gives the reference to the element on the right node.
	 */
	public Node getRightNode() {
		return rightNode;
	}

	/*
	 * This method gives the reference to the element on the left node.
	 */
	public Node getLeftNode() {
		return leftNode;
	}
	
	/*
	 * this method gives reference to the Operation that is to be performed.
	 */
	public Operators getoperator() {
		return this.operator;
	}
	
	/*
	 * Default constructor for the class.
	 */
	public MathOpNode(Node leftnode, Operators operator, Node rightnode) {
		this.leftNode=leftnode;
		this.operator=operator;
		this.rightNode=rightnode;
	}
	
	public MathOpNode() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		String str="";
		str+= ""+this.getLeftNode()+" "+this.getoperator()+" "+this.getRightNode()+"";
		return str;
		
	}
	
}
