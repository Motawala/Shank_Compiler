package Lexer_Project;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter  {
	
	public FunctionAST ASTnode;
	public Map<String, InterpreterDataType> map2 = new HashMap();
	
	public Interpreter(FunctionAST ASTnode) {
		this.ASTnode = ASTnode;
	}

	public Interpreter() {
		// TODO Auto-generated constructor stub
	}

	static HashMap<String, CallableNode> FuncMap = new HashMap();
	static {
		FuncMap.put("write", new write());
		FuncMap.put("read", new read());
		FuncMap.put("SquareRoot", new squareRoot());
		FuncMap.put("getRandom", new getRandom());
		FuncMap.put("IntegerToReal", new IntegerToReal());
		FuncMap.put("RealToInteger", new RealToInteger());
	}
	/**
	 * This method checks the type of node to perform the operation accordingly
	 * @param ASTnode
	 * @return float number
	 */
	public float Resolve(Node ASTnode) {
		if(ASTnode instanceof FloatNode) {
			//getting the value from the node.
			return((FloatNode) ASTnode).getFloatNumber();
		}else if(ASTnode instanceof IntegerNode) {
			//float casted to integer node. Getting the value from the node.
			return (float)((IntegerNode) ASTnode).getIntegerNumber();
		}else {
			MathOpNode MathNode = (MathOpNode)ASTnode;
			MathOpNode.Operators operator = MathNode.getoperator();
			Node Leftoperand = MathNode.getLeftNode();
			Node Rightoperand = MathNode.getRightNode();
			float Left = Resolve(Leftoperand);
			float Right = Resolve(Rightoperand);
			float calculation;
			//Operations being performed
			if(operator==MathOpNode.Operators.ADD) //Adding left and right
			{
				calculation=Left+Right;
				return calculation;
			}
			else if(operator==MathOpNode.Operators.SUBTRACT)//Subtracting left and right
			{
				calculation=Left-Right;
				return calculation;
			}
			else if(operator==MathOpNode.Operators.MULTIPLY)//Multiplying left and right
			{
				calculation=Left*Right;
				return calculation;
			}
			else if(operator==MathOpNode.Operators.DIVIDE)//Dividing left and right
			{
				calculation=Left/Right;
				return calculation;
			}
			else if(operator==MathOpNode.Operators.MOD) {
				calculation = Left%Right;
				return calculation;
			}
			else {
				return 0;
			}
		}
	}
	
	/*
	 * This void function stores the local variables, parameter and functions into the hash map.
	 * @param: Funciton Node that is the functions to be interpreted.
	 */
	public void InterpreterFunction(FunctionAST FunctionNode) {
		
		
		for(VariableNode VarNode:this.ASTnode.getLocalvariables()) { //Stores the local variables in the hash map.
			if(VarNode.getEnumType()==Tokens.Type.integer) {
				map2.put(VarNode.getName(), new IntDataType());
			}
			else {
				map2.put(VarNode.getName(), new FloatDataType());
			}
		}
		

		
		for(statementNode statement: this.ASTnode.getStatements()) { //Stores the parameters of the functions in the hash map
			
			if(statement instanceof FunctionCall FunCalled) {
				for(int k=0; k<FunCalled.getFunctionParameters().size(); k++) {
					if((FunCalled.getFunctionParameters().get(k).getEnumType()!= null)){
						String Value = FunCalled.getFunctionParameters().get(k).getValue()+"";
						
						map2.put(FunCalled.getFunctionParameters().get(k).getName(), new IntDataType());
					}
					else if((FunCalled.getFunctionParameters().get(k).getEnumType()!= Tokens.Type.real)) {
						map2.put(FunCalled.getFunctionParameters().get(k).getName(), new FloatDataType());
					}else {
						map2.put(FunCalled.getFunctionParameters().get(k).getName(), null);
					}
				}
			}
			System.out.println(map2);
			
			
		}
		
	}
	
	
	/*
	 * This void method interprets the statements of the For, Repeat, while and if else statements.
	 * @param: List of statement nodes in the function that are to be interpreted.
	 */
	public void InterpreterBlock(List<statementNode> statements) {
		for(statementNode Statements: statements) { // Calls all the statements in the list.
			
			if(Statements instanceof WhileNode WhileLoop) { // interprets the statements in the While loop.
				for(statementNode StatsinLoop:WhileLoop.getWhileLoopStatements()){
					if(StatsinLoop instanceof assignmentNode assignments) {
						BooleanExpression Bool = WhileLoop.getBooleanExpression();
						if(Bool.getLeftExpression()+""=="null ") {
							String Left = Bool.getLeftExpression()+"";
							char[] chararray = Left.toCharArray();
							String tempLeft = null;
							for(int i=0; i<chararray.length-1; i++) {
								char Leftchar =Left.charAt(i);
								tempLeft+= Leftchar;
							}
							int LeftCondition = Integer.parseInt(tempLeft);
							String Right = Bool.getLeftExpression()+"";
							char[] chararray2 = Right.toCharArray();
							for(int i=0; i<chararray2.length-1; i++) {
								char Rightchar =Right.charAt(i+1);
								Right ="";
								Right+= Rightchar;
							}
							int RightCondition = Integer.parseInt(Right);
							/*
							 * Interprets the boolean expression
							 */
							if(Bool.getCondition()==Tokens.Type.GreaterThan) {
								System.out.println("Expressions of While Loop: \n");
								while(LeftCondition>RightCondition) {
									float Expression = Resolve(assignments.getExpression()); //Solve the expression
									System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
									RightCondition++;
								}
							}
							if(Bool.getCondition()==Tokens.Type.LessThan) {
								System.out.println("Expressions of While Loop: \n");
								while(LeftCondition<RightCondition) {
									float Expression = Resolve(assignments.getExpression()); //Solve the expression
									System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
									RightCondition++;
								}
	
							}
							if(Bool.getCondition()==Tokens.Type.GreaterThanEqualTo) {
								while(LeftCondition>=RightCondition) {
									float Expression = Resolve(assignments.getExpression()); //Solve the expression
									System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
									RightCondition++;
								}
							}
							if(Bool.getCondition()==Tokens.Type.LessThanEqualTo) {
								while(LeftCondition<=RightCondition) {
									float Expression = Resolve(assignments.getExpression()); //Solve the expression
									System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
									RightCondition++;
								}
							}
							if(Bool.getCondition()==Tokens.Type.NotEqualTo) {
								while(LeftCondition!=RightCondition) {
									float Expression = Resolve(assignments.getExpression()); //Solve the expression
									System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
									RightCondition++;
								}
							}
						}else {
							continue;
						}
						float Expression = Resolve(assignments.getExpression()); //Solve the expression
						System.out.println("Expressions of While Loop: \n");
						System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
					}
				}
			}else if(Statements instanceof IfNode ifNode) { //interprets the statements in the if else statements.
				for(statementNode StatsInIf:ifNode.getStatements()) {
					if(StatsInIf instanceof assignmentNode assignments) {
						BooleanExpression Bool = ifNode.getBooleanExpression();
						String Left = Bool.getLeftExpression()+"";
						char[] chararray = Left.toCharArray();
						for(int i=0; i<chararray.length-1; i++) {
							char Leftchar =Left.charAt(1+i);
							Left ="";
							Left+= Leftchar;
						}
						int LeftCondition = Integer.parseInt(Left);
						String Right = Bool.getLeftExpression()+"";
						char[] chararray2 = Right.toCharArray();
						for(int i=0; i<chararray2.length-1; i++) {
							char Rightchar =Right.charAt(i+1);
							Right ="";
							Right+= Rightchar;
						}
						int RightCondition = Integer.parseInt(Right);
						
						 //Interprets the boolean expression
						if(Bool.getCondition()==Tokens.Type.GreaterThan) {
							if(LeftCondition>RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						if(Bool.getCondition()==Tokens.Type.LessThan) {
							if(LeftCondition<RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						if(Bool.getCondition()==Tokens.Type.GreaterThanEqualTo) {
							if(LeftCondition>=RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						if(Bool.getCondition()==Tokens.Type.LessThanEqualTo) {
							if(LeftCondition<=RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						if(Bool.getCondition()==Tokens.Type.NotEqualTo) {
							if(LeftCondition!=RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						float Expression = Resolve(assignments.getExpression()); //Solves the expression
						System.out.println("Expressions of if elif: \n");
						System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
					}
				}
			}else if(Statements instanceof RepeatNode Repeat) { //interprets the statements in the Repeat Loop.
				for(statementNode StatsInRepeat:Repeat.getRepeatLoopStatement()) {
					if(StatsInRepeat instanceof assignmentNode assignments) {
						BooleanExpression Bool = Repeat.getBooleanExpression();
						String Left = Bool.getLeftExpression()+"";
						char[] chararray = Left.toCharArray();
						for(int i=0; i<chararray.length-1; i++) {
							char Leftchar =Left.charAt(1+i);
							Left ="";
							Left+= Leftchar;
						}
						int LeftCondition = Integer.parseInt(Left);
						String Right = Bool.getLeftExpression()+"";
						char[] chararray2 = Right.toCharArray();
						for(int i=0; i<chararray2.length-1; i++) {
							char Rightchar =Right.charAt(i+1);
							Right ="";
							Right+= Rightchar;
						}
						/*
						 * Interprets the boolean expression.
						 */
						int RightCondition = Integer.parseInt(Right);
						if(Bool.getCondition()==Tokens.Type.GreaterThan) {
							if(LeftCondition>RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						if(Bool.getCondition()==Tokens.Type.LessThan) {
							if(LeftCondition<RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						if(Bool.getCondition()==Tokens.Type.GreaterThanEqualTo) {
							if(LeftCondition>=RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						if(Bool.getCondition()==Tokens.Type.LessThanEqualTo) {
							if(LeftCondition<=RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						if(Bool.getCondition()==Tokens.Type.NotEqualTo) {
							if(LeftCondition!=RightCondition) {
								float Expression = Resolve(assignments.getExpression()); //Solves the expression
								System.out.println("Expressions of if elif: \n");
								System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
							}
						}
						float Expression = Resolve(assignments.getExpression()); //Solves the expression
						System.out.println("Expressions of Repeat loop: \n");
						System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
					}
			
				}
			}else if(Statements instanceof ForNode forNode) { // interprets the statements in the for loop.
				for(statementNode StatsInFor: forNode.getForLoopStatements())
				{
					if(StatsInFor instanceof assignmentNode assignments) {
						
						float Expression = Resolve(assignments.getExpression()); //Solves the expression.
						System.out.println("Expressions of For loop: \n");
						System.out.println(assignments.getVariableReferenceNode()+": "+Expression);
					}
				}
			}
				
		}
	}
	
	/**
	 * This method resolve the String operations
	 * @param StringASTnode String Nodes
	 * @return String Value after performing the operations
	 */
	public static String ResolveString(Node StringASTnode) {
		if(StringASTnode instanceof StringNode StrNode) {
			return StrNode.getStringValue();
		}else if(StringASTnode instanceof CharNode CharacterNode) {
			return ""+CharacterNode.getCharValue();
		}else if(StringASTnode instanceof MathOpNode MathOp) {
			MathOp = (MathOpNode)(StringASTnode);
			
			//if the left node of the Math op node is String
			String LeftVal = ResolveString(MathOp.getLeftNode());
			//if the right node of the math op node is String
			String RightVal = ResolveString(MathOp.getRightNode());
			
			if(MathOp.getoperator()==MathOpNode.Operators.ADD) {
				return LeftVal+RightVal;
			}else {
				throw new LexerException("Invalid Syntax.");
			}
		}
		return ((StringNode)(StringASTnode)).getStringValue();
	}
	
	
	/**
	 * This method resolves boolean operations
	 * @param BoolASTnode Boolean Node
	 * @return Boolean after performing the operations
	 */
	public static boolean ResolveBoolean(Node BoolASTnode) {
		if(BoolASTnode instanceof BoolNode BooleanNode) {
			return BooleanNode.getBool();
		}
		return ((BoolNode)(BoolASTnode)).getBool();
	}
	
	
	/**
	 * This method resolves Character operations
	 * @param CharASTnode Character Node
	 * @return Character after performing character operations.
	 */
	public static char ResolveCharacter(Node CharASTnode) {
		if(CharASTnode instanceof CharNode characterNode) {
			return characterNode.getCharValue();
		}else {
			throw new LexerException("Invalid Syntax.");
		}
	}
}
