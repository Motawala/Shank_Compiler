package Lexer_Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class getRandom extends BuiltInFunction{

	public void Execute(Collection<InterpreterDataType> InterpreterType) {
		// TODO Auto-generated method stub
		List<String> randomlist = new ArrayList<String>();
		for(InterpreterDataType i : InterpreterType) {
			randomlist.add(i.toString());
		}
		Random rand = new Random();

		int n = rand.nextInt(2);
		System.out.println(randomlist.get(n));
		
	}
}
