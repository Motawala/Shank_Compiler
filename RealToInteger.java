package Lexer_Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RealToInteger extends BuiltInFunction{

	public void Execute(Collection<InterpreterDataType> InterpreterType) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		for(InterpreterDataType i : InterpreterType) {
			int f = Integer.parseInt(i.toString());
			list.add(f);
		}
		System.out.println(list);
		
	}
}
