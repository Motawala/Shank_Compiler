package Lexer_Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Parser {
	
	ArrayList<Tokens> token;
	
	/*
	 * Default constructor that collects all the tokens.
	 */
	public Parser(ArrayList<Tokens> tokens) {
		this.token=tokens;
	}
	
	/**
	 * This parse method calls the expression.
	 * @return expression executed.
	 */
	public FunctionAST parse() 
	{
		FunctionAST ASTNode = FunctionDefinition();
		return ASTNode;
	}
	
	public FunctionNode parse_call() 
	{
		FunctionNode ASTNode = myFunctionCall();
		return ASTNode;
	}
	
	private FunctionNode myFunctionCall() {
		// TODO Auto-generated method stub
		String name = "";
		FunctionNode FunctionCalled = new FunctionNode();
		while (token.size()>0) {
			if(token.get(0).getType()==Tokens.Type.identifier) {
				name = MatchAndRemove(Tokens.Type.identifier).getValue();
				if(token.get(0).getType()==Tokens.Type.identifier) {
					FunctionCalled = new FunctionNode(name,getParameters());
				}
				if(token.get(0).getType()==Tokens.Type.number) {
					FunctionCalled = new FunctionNode(name,getParameters_call());
				}
				if(token.get(0).getType()==Tokens.Type.end) {
					MatchAndRemove(Tokens.Type.end);
					MatchAndRemove(Tokens.Type.EOL);
				}
				else {
					MatchAndRemove(token.get(0).getType());
				}
			}
			else {
				MatchAndRemove(token.get(0).getType());
			}
		}
		return FunctionCalled;
	}
	 
	/*
	 * Parameters Function. Returns parameters in the function
	 */
	private List<VariableNode> getParameters_call() { 
		List<String> names = new ArrayList<String>(); //name of the parameter
		List<VariableNode> variables = new ArrayList<VariableNode>();
		String tempname ="";
		boolean varflag = false;
		while(token.get(0).getType()==Tokens.Type.number){	
			if (varflag==true) {
				names.add(new String("var "+token.remove(0).getValue()));
			}
			else {
				names.add(new String(token.remove(0).getValue()));
			}
			
			if(token.get(0).getType()==Tokens.Type.comma) {
				while(MatchAndRemove(Tokens.Type.comma)!=null) {
					
					if (token.get(0).getType()==Tokens.Type.Var) {
						MatchAndRemove(Tokens.Type.Var);
						names.add(new String("var "+token.remove(0).getValue()));
					}
					else {
						names.add(new String(token.remove(0).getValue()));
					}
				}
			}
			for(int j=0; j<names.size();j++) {
				if(names.size()!=j+1) {
					tempname+=names.get(j)+",";
				}
				else {
					tempname+=names.get(j);
				}
			}
			names.clear();
			
			if(MatchAndRemove(Tokens.Type.colon)!=null) {
			}
			if((token.get(0).getType()==Tokens.Type.integer) || token.get(0).getType()==Tokens.Type.real) {
				variables.add(new VariableNode(tempname, token.remove(0).getType(),null,false));
				tempname="";
			}
			else {
				//variables.clear();
				variables.add(new VariableNode(tempname, null,null,false));
			}
			MatchAndRemove(Tokens.Type.semicolon);
//			MatchAndRemove(Tokens.Type.EOL);
			if (token.get(0).getType()==Tokens.Type.Var) {
				MatchAndRemove(Tokens.Type.Var);
				varflag = true;
			}
		}
		return variables;
	}

	private FunctionAST FunctionDefinition() 
	{
		String name;
		List<VariableNode> parameters = null;
		List<VariableNode> localvariables = new ArrayList<VariableNode>();
		List<statementNode> body = new ArrayList<statementNode>();
		if(MatchAndRemove(Tokens.Type.define)==null) {
			throw new LexerException("Invalid Syntax");
		}
		name = MatchAndRemove(Tokens.Type.identifier).getValue();
		if(MatchAndRemove(Tokens.Type.leftParen)!=null) {
			parameters = getParameters();
		}
		if(MatchAndRemove(Tokens.Type.rightParen)!=null) {
			MatchAndRemove(Tokens.Type.EOL);
		}
		if(token.get(0).getType()==Tokens.Type.EOL) {
			MatchAndRemove(Tokens.Type.EOL);
		}
		if((MatchAndRemove(Tokens.Type.constants)!=null)){
			MatchAndRemove(Tokens.Type.EOL);
			localvariables.addAll((getConstant()));
		}
		if(token.get(0).getType()==Tokens.Type.EOL) {
			MatchAndRemove(Tokens.Type.EOL);
		}
		if((MatchAndRemove(Tokens.Type.variables)!=null)) {
			MatchAndRemove(Tokens.Type.EOL);
			while(token.get(0).getType()==Tokens.Type.identifier) {
				localvariables.addAll(getVariable());
			}
		}
		
		if(token.get(0).getType()==Tokens.Type.EOL) {
			MatchAndRemove(Tokens.Type.EOL);
		}
		if(token.get(0).getType()==Tokens.Type.begin) {
			body=getBody();
		}
		if(token.get(0).getType()==Tokens.Type.end) {
			MatchAndRemove(Tokens.Type.end);
			MatchAndRemove(Tokens.Type.EOL);
		}
		
		if((MatchAndRemove(Tokens.Type.EOL)!=null)) {
			
		}
		
		MatchAndRemove(Tokens.Type.EOL);
		
		return new FunctionAST(name, parameters, localvariables, body);
	}
	
	/*
	 * Parameters Function. Returns parameters in the function
	 */
	private List<VariableNode> getParameters() { 
		List<String> names = new ArrayList<String>(); //name of the parameter
		List<VariableNode> variables = new ArrayList<VariableNode>();
		String tempname ="";
		boolean varflag = false;
		while(token.get(0).getType()==Tokens.Type.identifier){	
			if (varflag==true) {
				names.add(new String("var "+token.remove(0).getValue()));
			}
			else {
				names.add(new String(token.remove(0).getValue()));
			}
			
			if(token.get(0).getType()==Tokens.Type.comma) {
				while(MatchAndRemove(Tokens.Type.comma)!=null) {
					
					if (token.get(0).getType()==Tokens.Type.Var) {
						MatchAndRemove(Tokens.Type.Var);
						names.add(new String("var "+token.remove(0).getValue()));
					}
					else {
						names.add(new String(token.remove(0).getValue()));
					}
				}
			}
			for(int j=0; j<names.size();j++) {
				if(names.size()!=j+1) {
					tempname+=names.get(j)+",";
				}
				else {
					tempname+=names.get(j);
				}
			}
			names.clear();
			
			if(MatchAndRemove(Tokens.Type.colon)!=null) {
			}
			if((token.get(0).getType()==Tokens.Type.integer) || token.get(0).getType()==Tokens.Type.real) {
				variables.add(new VariableNode(tempname, token.remove(0).getType(),null,false));
				tempname="";
			}
			else {
				//variables.clear();
				variables.add(new VariableNode(tempname, null,null,false));
			}
			MatchAndRemove(Tokens.Type.semicolon);
//			MatchAndRemove(Tokens.Type.EOL);
			if (token.get(0).getType()==Tokens.Type.Var) {
				MatchAndRemove(Tokens.Type.Var);
				varflag = true;
			}
		}
		return variables;
	}
	
	
	
	/*
	 * Constant functions. Handles constants in the code
	 */
	private List<VariableNode> getConstant() {
		String names = "";
		List<VariableNode> constant = new ArrayList<VariableNode>();
		Tokens.Type enumTypes=Tokens.Type.equal;
		String Val;
		float number=0;
		//Looks for the identifier.
		while(token.get(0).getType()==Tokens.Type.identifier) {
			names = MatchAndRemove(Tokens.Type.identifier).getValue();
			//Looks for the EqualTo sign.
			if(token.get(0).getType()==Tokens.Type.equal) { 
				MatchAndRemove(Tokens.Type.equal);
			}
			
			//Looks for the number assigned to the constant.
			if(token.get(0).getType()==Tokens.Type.number) {
				enumTypes = token.get(0).getType();
				Val=MatchAndRemove(Tokens.Type.number).getValue();
				number = Float.parseFloat(Val) ;
			}
			
			//Removes the semi colon from the end.
			if(MatchAndRemove(Tokens.Type.semicolon)!=null) {throw new LexerException("Invalid Syntax");}
			
			MatchAndRemove(Tokens.Type.EOL);
			constant.add(new VariableNode(names,enumTypes,new FloatNode(number),true));
		}
		return constant;
		
	}
	
	
	/*
	 * Variable function that defines the variable of type integer and real.
	 */
	private List<VariableNode> getVariable() {
		List<String> names= new ArrayList<String>();
		List<VariableNode> variables = new ArrayList<VariableNode>();
		Tokens.Type enumTypes=null;
		String name;
		float number = 0;
		while(token.get(0).getType()==Tokens.Type.number || token.get(0).getType()==Tokens.Type.identifier) {
			if(token.get(0).getType()==Tokens.Type.number) {
				name=MatchAndRemove(Tokens.Type.number).getValue();
				char[] cVal;
				cVal=name.toCharArray();
				for(int i=0;i<name.length();i++) {
					if(cVal[i]=='.') {
						enumTypes=Tokens.Type.real;
						break;
					}else {
						enumTypes=Tokens.Type.integer;
					}
				}
				number= Float.parseFloat(name);
				if(token.get(0).getType()==Tokens.Type.comma) {
					MatchAndRemove(Tokens.Type.comma);
				}
				variables.add(new VariableNode(name,enumTypes, new FloatNode(number),false));
				enumTypes=null;
				
			}
			if(token.get(0).getType()==Tokens.Type.identifier)
			{
				name=MatchAndRemove(Tokens.Type.identifier).getValue();
				if(token.get(0).getType()==Tokens.Type.comma) {
					MatchAndRemove(Tokens.Type.comma);
					for(int i=0; i<token.size();i++) {
						if(token.get(i).getType()==Tokens.Type.colon) {
							enumTypes = token.get(1+i).getType();
						}
					}
				}else {
					MatchAndRemove(Tokens.Type.colon);
					token.remove(0);
				}
				variables.add(new VariableNode(name,enumTypes,null,false));
				//enumTypes=null;
			}
			
		}
		
		MatchAndRemove(Tokens.Type.EOL);
		
		return variables;
	}
	
	/*
	 * Body functions that handles the body of the code that is from begin to end.
	 */
	private List<statementNode> getBody(){
		List<statementNode> statementNode = new ArrayList<statementNode>();
		MatchAndRemove(Tokens.Type.begin);
		MatchAndRemove(Tokens.Type.EOL);
		while(token.get(0).getType()==Tokens.Type.identifier){
			statementNode.addAll(getStatements());
		}
		if(token.get(0).getType()==Tokens.Type.Read) {
			statementNode.addAll(getStatements());
		}
		if(token.get(0).getType()==Tokens.Type.Write) {
			statementNode.addAll(getStatements());
		}
		if(token.get(0).getType()==Tokens.Type.getRandom) {
			statementNode.addAll(getStatements());
		}
		if(token.get(0).getType()==Tokens.Type.IntegerToReal) {
			statementNode.addAll(getStatements());
		}
		if(token.get(0).getType()==Tokens.Type.RealToInteger) {
			statementNode.addAll(getStatements());
		}
		if(token.get(0).getType()==Tokens.Type.SquareRoot) {
			statementNode.addAll(getStatements());
		}
		//Handles while loop statements in the function
		if(token.get(0).getType()==Tokens.Type.While) {
			statementNode.add(getWhileLoop());
			while(token.get(0).getType()==Tokens.Type.identifier) {
				statementNode.addAll(getStatements());
			}
		}
		
		//Handles For loop statements in the function
		if(token.get(0).getType()==Tokens.Type.For) {
			statementNode.add(getForLoop());
			while(token.get(0).getType()==Tokens.Type.identifier) {
				statementNode.addAll(getStatements());
			}
		}
		
		//Handles Repeat loop statements in the function
		if(token.get(0).getType()==Tokens.Type.Repeat) {
			statementNode.add(getRepeatLoop());
			while(token.get(0).getType()==Tokens.Type.identifier) {
				statementNode.addAll(getStatements());
			}
		}
		
		//Handles if else statements in the function
		if(token.get(0).getType()==Tokens.Type.If) {
			statementNode.add(getIfNode());
		}
		return statementNode;
	}
	
	/*
	 * Statement function that handles the statements in the body.
	 */
	private List<statementNode> getStatements(){
		assignmentNode statements = new assignmentNode();
		FunctionCall functions = new FunctionCall();
		List<statementNode> StatementNode = new ArrayList<statementNode>();
		while(token.get(0).getType()==Tokens.Type.identifier || token.get(0).getType()==Tokens.Type.Read || token.get(0).getType()==Tokens.Type.Write || token.get(0).getType()==Tokens.Type.getRandom || token.get(0).getType()==Tokens.Type.SquareRoot || token.get(0).getType()==Tokens.Type.IntegerToReal || token.get(0).getType()==Tokens.Type.RealToInteger) {
			if(token.get(1).getType()==Tokens.Type.assign) {
				statements = getAssignment();
				StatementNode.add(statements);
			}else {
				functions = getFunctionCall();
				StatementNode.add(functions);
			}
		}
		MatchAndRemove(Tokens.Type.EOL);
		return StatementNode;
	}
	
	/*
	 * Assignment function handles the assignments in the function
	 */
	private assignmentNode getAssignment(){
		VariableReferenceNode VariableName = new VariableReferenceNode();
		assignmentNode assignment = new assignmentNode();
		Node expression1 = new MathOpNode();
		while(token.get(0).getType()==Tokens.Type.identifier) {
			VariableName = getVariableReference();
		}
		if(token.get(0).getType()==Tokens.Type.assign || token.get(0).getType()==Tokens.Type.equal) {
			MatchAndRemove(Tokens.Type.assign);
		}
		while(token.get(0).getType()==Tokens.Type.number || token.get(0).getType()==Tokens.Type.identifier || token.get(0).getType()==Tokens.Type.String || token.get(0).getType()==Tokens.Type.Character) {
			expression1 = Expression();
		}
		
		MatchAndRemove(Tokens.Type.EOL);
		assignment = new assignmentNode(VariableName,expression1);
		return assignment;
	}
	
	/*
	 * Variable reference function that handles the variable name of the variable assigned.
	 */
	private VariableReferenceNode getVariableReference(){
		VariableReferenceNode variableRef = new VariableReferenceNode();
		while(token.get(0).getType()==Tokens.Type.identifier) {
			variableRef=new VariableReferenceNode(token.remove(0).getValue());
		}
		while(token.get(0).getType()==Tokens.Type.number) {
			variableRef=new VariableReferenceNode(token.remove(0).getValue());
		}
		MatchAndRemove(Tokens.Type.EOL);
		return variableRef;
	}
	
	/*
	 * This methods gets the boolean expression for the conditions of While and repeat loop
	 */
	private BooleanExpression getBooleanExpression() {
		VariableReferenceNode leftExp = new VariableReferenceNode();
		VariableReferenceNode  RightExp = new VariableReferenceNode();
		BooleanExpression ConditionExpression = new BooleanExpression();
		Tokens.Type LoopConditionType = Tokens.Type.True;
		Node LoopCondition;
		if(token.get(0).getType()==Tokens.Type.identifier) { 
			leftExp = getVariableReference();
		}else {
			LoopCondition = Expression();
		}
		if(token.get(0).getType()==Tokens.Type.GreaterThan) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.GreaterThanEqualTo){
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.LessThan) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.LessThanEqualTo) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.NotEqualTo) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.EqualEqualTo) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.MOD) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.PLUS) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.MINUS) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.TIMES) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.DIVIDE) {
			LoopConditionType = token.remove(0).getType();
		}
		else if(token.get(0).getType()==Tokens.Type.equal) {
			LoopConditionType = token.remove(0).getType();
		}else if(token.get(0).getType()==Tokens.Type.True || token.get(0).getType()==Tokens.Type.False) {
			LoopCondition = (BooleanExpression)Expression();
		}
		else {
			token.remove(0);//Remove EOL
		}
		if(token.get(0).getType()==Tokens.Type.number) { //if there is a number used in the condition
			float number = Float.parseFloat(token.remove(0).getValue());
			
			if(token.get(0).getType()==Tokens.Type.equal) {
				ConditionExpression = getBooleanExpression();
				LoopCondition = new BooleanExpression(leftExp, LoopConditionType, ConditionExpression);
			}
			else {
				MatchAndRemove(Tokens.Type.EOL);
				LoopCondition = new BooleanExpression(leftExp,LoopConditionType, new FloatNode(number));
			}
		}else if(token.get(0).getType()==Tokens.Type.String || token.get(0).getType()==Tokens.Type.Character) {
			LoopCondition = Expression();
		}
		else { //if there is an identifier used in the condition
			RightExp = getVariableReference();
			LoopCondition = new BooleanExpression(leftExp,LoopConditionType, RightExp);
		}
		if(token.get(0).getType()==Tokens.Type.Then) {
			MatchAndRemove(Tokens.Type.Then);
		}
		MatchAndRemove(Tokens.Type.EOL);
		return (BooleanExpression)LoopCondition;
	}
	
	/*
	 * This method handles the statements of the while loop
	 */
	private WhileNode getWhileLoop() {
		BooleanExpression condition = new BooleanExpression();
		WhileNode WhileLoop = new WhileNode();
		if(token.remove(0).getType()==Tokens.Type.While) {
			condition = getBooleanExpression();
			MatchAndRemove(Tokens.Type.begin);
			MatchAndRemove(Tokens.Type.EOL);
		}
		if(token.get(0).getType()==Tokens.Type.While) {
			WhileLoop = new WhileNode(condition,getBody());
		}
		else if(token.get(0).getType()==Tokens.Type.Repeat) { //If there is a nested repeat loop in the loop
			WhileLoop = new WhileNode(condition,getBody());
		}
		else if(token.get(0).getType()==Tokens.Type.For) { //If there is a nested for loop in the loop
			WhileLoop = new WhileNode(condition,getBody());
		}
		else if(token.get(0).getType()==Tokens.Type.If){ //if there are if else statements in the loop
			WhileLoop = new WhileNode(condition,getBody());
		}
		else { //If there are simple expressions in the loop
			WhileLoop = new WhileNode(condition,getStatements());
		}
		if(token.get(0).getType()==Tokens.Type.end){
			MatchAndRemove(Tokens.Type.end);
			MatchAndRemove(Tokens.Type.EOL);
		}else {
			throw new LexerException("Invalid Syntax.");
		}
		return WhileLoop;
	}
	
	/*
	 * This method handles the statements in the for loop.
	 */
	private ForNode getForLoop() {
		VariableReferenceNode variable = new VariableReferenceNode();
		Node start;
		Node end;
		ForNode ForCondition = new ForNode();
		/*
		 * Handling the condition of the foor loop.
		 */
		if(token.remove(0).getType()==Tokens.Type.For) {
			variable = getVariableReference();
			if(token.get(0).getType()==Tokens.Type.From) {
				MatchAndRemove(Tokens.Type.From);
			}
			else {
				throw new LexerException("Invalid Syntax");
			}
			if(token.get(0).getType()==Tokens.Type.number) {
				float number = Float.parseFloat(token.remove(0).getValue());
				start = new FloatNode(number);
			}
			else {
				start = getVariableReference();
			}
			
			if(token.get(0).getType()==Tokens.Type.To) {
				MatchAndRemove(Tokens.Type.To);
			}
			else {
				throw new LexerException("Invali Syntax.");
			}
			if(token.get(0).getType()==Tokens.Type.number) {
				float number = Float.parseFloat(token.remove(0).getValue());
				end = new FloatNode(number);
			}
			else {
				end = getVariableReference();
			}
			MatchAndRemove(Tokens.Type.EOL);
			if(token.get(0).getType()==Tokens.Type.begin) {
				MatchAndRemove(Tokens.Type.begin);
				MatchAndRemove(Tokens.Type.EOL);
			}

			/*
			 * If there is a nested loop statement in the For loop.
			 */
			if(token.get(0).getType()==Tokens.Type.While) {
				ForCondition = new ForNode(variable,start,end,getBody());
			}
			else if(token.get(0).getType()==Tokens.Type.Repeat) {
				ForCondition = new ForNode(variable,start,end,getBody());
			}
			else if(token.get(0).getType()==Tokens.Type.For) {
				ForCondition = new ForNode(variable,start,end,getBody());
			}
			else if(token.get(0).getType()==Tokens.Type.If){ //Handles if else statements of the for loop
				ForCondition = new ForNode(variable,start,end, getBody());
			}
			else {
				ForCondition = new ForNode(variable,start,end,getStatements());
			}
				/*
				 * Removing the End and Eol tokens.
				 */
			if(token.get(0).getType()==Tokens.Type.end) {
				MatchAndRemove(Tokens.Type.end);
				MatchAndRemove(Tokens.Type.EOL);
			}
			
		}

		return ForCondition;
}
	
	/*
	 * This method returns the RepeatNode with Repeat boolean expression and its statements
	 */
	private RepeatNode getRepeatLoop() {
		BooleanExpression condition = new BooleanExpression();
		RepeatNode RepeatCondition = new RepeatNode();
		//Handling Repeat Loop.
		if(token.remove(0).getType()==Tokens.Type.Repeat) {
			condition = getBooleanExpression();
			if(token.get(0).getType()==Tokens.Type.begin) {
				MatchAndRemove(Tokens.Type.begin);
				MatchAndRemove(Tokens.Type.EOL);				
			}
			else {
				throw new LexerException("Invalid Syntax");
			}
			
			/*
			 * Handles nested loop inside the repeat loop and its statement.
			 */
			if(token.get(0).getType()==Tokens.Type.For) {
				RepeatCondition = new RepeatNode(condition,getBody());
			}
			else if(token.get(0).getType()==Tokens.Type.While){
				RepeatCondition = new RepeatNode(condition, getBody());
			}
			else if(token.get(0).getType()==Tokens.Type.Repeat) {
				RepeatCondition = new RepeatNode(condition, getBody());
			}
			else if(token.get(0).getType()==Tokens.Type.If){ //If there are if else statements in the loop
				RepeatCondition = new RepeatNode(condition, getBody());
			}
			else { //Handles expressions of the repeat loop
				RepeatCondition = new RepeatNode(condition,getStatements());
			}
			//Removes end and Eol tokens.
			if(token.get(0).getType()==Tokens.Type.end) {
				MatchAndRemove(Tokens.Type.end);
				MatchAndRemove(Tokens.Type.EOL);
			}
		}
		return RepeatCondition;
	}
	
	/*
	 * Handles the if else statements in the function and loops
	 */
	private IfNode getIfNode() {
		
		BooleanExpression condition = new BooleanExpression();
		List<statementNode> statements = new ArrayList<statementNode>();
		IfNode ifNode = new IfNode();
		IfNode elseif = new IfNode();
		
		//Handles else if statements
		if(token.get(0).getType()==Tokens.Type.Elsif) {
			MatchAndRemove(Tokens.Type.Elsif);
			condition = getBooleanExpression();
			MatchAndRemove(Tokens.Type.begin);
			MatchAndRemove(Tokens.Type.EOL);
			statements.addAll(getStatements());
			MatchAndRemove(Tokens.Type.end);
			MatchAndRemove(Tokens.Type.EOL);
			elseif= new IfNode(condition,statements,getIfNode());
			if(elseif!=null) {
				ifNode = (elseif);
			}
			
		}		
		
		//gets else statements from the else node.
		if(token.get(0).getType()==Tokens.Type.Else) {
			ifNode = getElseNode();
		}
		
		//If conditions in the function
		if(token.get(0).getType()==Tokens.Type.If) {
			MatchAndRemove(Tokens.Type.If);
			condition = getBooleanExpression();
			MatchAndRemove(Tokens.Type.begin);
			MatchAndRemove(Tokens.Type.EOL);
			statements.addAll(getStatements());
			MatchAndRemove(Tokens.Type.end);
			MatchAndRemove(Tokens.Type.EOL);
			ifNode = new IfNode(condition,statements,getIfNode());
		}
		return ifNode;
	}
	
	/*
	 * This method handles the else statements in the function and loop.
	 */
	private ElseNode getElseNode() {
		ElseNode elseNode = new ElseNode();
		List<statementNode> statements = new ArrayList<statementNode>();
		if(token.get(0).getType()==Tokens.Type.Else) {
			MatchAndRemove(Tokens.Type.Else);
			MatchAndRemove(Tokens.Type.EOL);
			MatchAndRemove(Tokens.Type.begin);
			MatchAndRemove(Tokens.Type.EOL);
			statements.addAll(getStatements());
			elseNode = new ElseNode(statements);
			MatchAndRemove(Tokens.Type.end);
			MatchAndRemove(Tokens.Type.EOL);
		}
		return elseNode;
	}
	
	
	
	private FunctionCall getFunctionCall() {
		String name = "";
		FunctionCall FunctionCalled = new FunctionCall();
		if(token.get(0).getType()==Tokens.Type.identifier) {
			name = MatchAndRemove(Tokens.Type.identifier).getValue();
		}
		if(token.get(0).getType()==Tokens.Type.Read) {
			name = MatchAndRemove(Tokens.Type.Read).getValue();
		}
		if(token.get(0).getType()==Tokens.Type.Write) {
			name = MatchAndRemove(Tokens.Type.Write).getValue();
		}
		if(token.get(0).getType()==Tokens.Type.SquareRoot) {
			name = MatchAndRemove(Tokens.Type.SquareRoot).getValue();
		}
		if(token.get(0).getType()==Tokens.Type.getRandom) {
			name = MatchAndRemove(Tokens.Type.getRandom).getValue();
		}
		if(token.get(0).getType()==Tokens.Type.IntegerToReal) {
			name = MatchAndRemove(Tokens.Type.IntegerToReal).getValue();
		}
		if(token.get(0).getType()==Tokens.Type.RealToInteger) {
			name = MatchAndRemove(Tokens.Type.RealToInteger).getValue();
		}
		
		if(token.get(0).getType()==Tokens.Type.identifier ||  token.get(0).getType()==Tokens.Type.number) {
			FunctionCalled = new FunctionCall(name,getVariable());
		}
		return FunctionCalled;
	}
	

	/**
	 * This method matches the tokens and checks its presence for other methods.
	 * @param tokenType type of enum used in the token.
	 * @return null after removing the token
	 */
	private Tokens MatchAndRemove(Tokens.Type tokenType) 
	{
		if (this.token.size()>0) {
			Tokens tk = this.token.get(0);
			if(tk.getType() == tokenType) 
			{
				token.remove(0);
				return tk;
			}
			return null;
		}
		else {
			return null;
		}
		
		
	}
		
	/**
	 * This method handles the expression order in: TERM{plus or minus}TERM.
	 * @return expression node.
	 */
	private Node Expression() 
	{
		//Left node of the expression
		Node Term = Term();
		if(Term==null) 
		{
			return null;
		}
		//Plus and minus handled in the expression.
		Node ExpressionNode = Term;
		while(true) {
			int flag = 0;
			//PLUS 
			Tokens tk = MatchAndRemove(Tokens.Type.PLUS);
			if(tk!=null) 
			{
				Node tempnode=Term();
				//creates the math operation for addition
				MathOpNode.Operators operators = MathOpNode.Operators.ADD;
				MathOpNode tempMathNode = new MathOpNode(ExpressionNode,operators,tempnode);
				ExpressionNode=tempMathNode;
				flag = 1;
				//return ExpressionNode;
			}
			//SUBTRACT
			tk=MatchAndRemove(Tokens.Type.MINUS);
			if(tk!=null) 
			{
				Node tempnode= Term();
				//creates a math operation for subtraction.
				MathOpNode.Operators operators = MathOpNode.Operators.SUBTRACT;
				MathOpNode tempMathNode = new MathOpNode(ExpressionNode,operators,tempnode);
				ExpressionNode = tempMathNode;
				//return ExpressionNode;
				flag = 1;
			}
			if (flag == 0) {
				break;
			}
		}


		return ExpressionNode;
	}

	/**
	 * This method handles FACTOR order in: {-} number or lparen EXPRESSION rparen.
	 * @return Factor node.
	 */
	private Node Factor()
	{
		//Match and Remove to see if the token is present or not.
		Tokens tk = MatchAndRemove(Tokens.Type.number);
		if(tk!=null) 
		{
			String TokenVal = tk.getValue();
			try 
			{
				//Checks if the String value is a float.
				float FloatNumber = Float.parseFloat(TokenVal);
				return new FloatNode(FloatNumber);

			}
			catch(NumberFormatException e)
			{
				//check if the string is an Integer.
				int IntNumber = Integer.parseInt(TokenVal);
				return new IntegerNode(IntNumber);
			}
		}
		tk = MatchAndRemove(Tokens.Type.identifier);
		if(tk!=null) {
			String TokenVal = tk.getValue();
			return new VariableReferenceNode(TokenVal);
			
		}
		
		tk = MatchAndRemove(Tokens.Type.String);
		if(tk!=null) {
			String TokenVal = tk.getValue();
			return new StringNode(TokenVal);
		}
		
		tk = MatchAndRemove(Tokens.Type.Character);
		if(tk!=null) {
			String TokenVal = tk.getValue();
			char charTokenVal = 0;
			for(int i=0; i<TokenVal.length(); i++) {
				charTokenVal+= TokenVal.charAt(i);
			}
			return new CharNode(charTokenVal);
		}
		if(MatchAndRemove(Tokens.Type.True)!=null) {
			boolean TokenVal = true;
			return new BoolNode(TokenVal);
		}else if(MatchAndRemove(Tokens.Type.False)!=null) {
			boolean TokenVal = false;
			return new BoolNode(TokenVal);
		}
		else
		{
			//Returns expression
			Node tempnode = Expression();
			if(tempnode==null)
			{
				return null;
			}
			else 
			{
				return tempnode;
			}
		}
	}


	/**
	 * This method handles TERM order in: FACTOR{ times ore divide}FACTOR.
	 * @return Term node.
	 */
	private Node Term() 
	{
		//Left node of the operation
		Node FactNode = Factor();
		if(FactNode==null)
		{
			return null;
		}
		Node Term = FactNode;
		while(true) {
			//checks the presence of DIVIDE the token
			Tokens tk =MatchAndRemove(Tokens.Type.DIVIDE);
			if(tk!=null) 
			{
				Node tempnode=Factor();
				//creates a math operaton node that can be divided.
				MathOpNode.Operators operators = MathOpNode.Operators.DIVIDE;
				MathOpNode tempMathNode = new MathOpNode(Term,operators,tempnode);
				Term = tempMathNode;// Division as an operator.
				//return Term;
			}

			//checks the presence of the TIMES token.
			tk = MatchAndRemove(Tokens.Type.TIMES);
			if(tk!=null)
			{
				Node tempnode =Factor();
				if(tempnode==null) 
				{
					return null;
				}
				//creates a math operation node that can be multiplied.
				MathOpNode.Operators operators = MathOpNode.Operators.MULTIPLY;
				MathOpNode tempMathNode = new MathOpNode(Term,operators,tempnode);
				Term=tempMathNode;// Multiply as an operator.
			}
			//Checks and remove if the token type is MOD.
			tk = MatchAndRemove(Tokens.Type.MOD);
			if(tk!=null) {
				Node tempnode = Factor();
				if(tempnode == null) {
					return null;
				}
				//creates a math operation node that can perform mod function.
				MathOpNode.Operators operators = MathOpNode.Operators.MOD;
				MathOpNode tempMathNode = new MathOpNode(Term,operators,tempnode);
				Term=tempMathNode;
			}
			else {
				break;
			}
		}

		return Term;
	
	}

}