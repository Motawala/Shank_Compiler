package Lexer_Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IntegerToReal extends BuiltInFunction{

	
	public void Execute(Collection<InterpreterDataType> InterpreterType) {
		// TODO Auto-generated method stub
		List<Float> list = new ArrayList<Float>();
		for(InterpreterDataType i : InterpreterType) {
			float f = Float.parseFloat(i.toString());
			list.add(f);
		}
		System.out.println(list);
		
	}
}
