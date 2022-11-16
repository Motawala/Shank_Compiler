package Lexer_Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class squareRoot extends BuiltInFunction{

	
	public void Execute(Collection<InterpreterDataType> InterpreterType) {
		// TODO Auto-generated method stub
		List<Double> sqrlist = new ArrayList<Double>();
		for(InterpreterDataType i : InterpreterType) {
			double f = Math.sqrt(Float.parseFloat(i.toString()));
			sqrlist.add(f);
		}
		System.out.println(sqrlist);
		
	}
}
