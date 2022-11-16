package Lexer_Project;

import java.util.List;

public class SemanticAnalysis {
	
	public SemanticAnalysis() {
		
	}

	public void ChechAssignment(List<statementNode> statements) {
		for(int i=0; i<statements.size();i++) {
			if(statements.get(i) instanceof WhileNode whileloop) {
				for(statementNode statementsInLoop: whileloop.getWhileLoopStatements()) {
					if(statementsInLoop instanceof assignmentNode assignments) {
						String name = assignments.getVariableReferenceNode().getName();
						Node exp = assignments.getExpression();
						
					}
				}
			}
		}
	}
}
