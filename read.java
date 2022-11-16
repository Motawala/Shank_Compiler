package Lexer_Project;
import java.util.Scanner;
import java.util.Collection;
import java.util.List;

public class read extends BuiltInFunction {

	public void Execute(Collection<InterpreterDataType> InterpreterType) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		System.out.println("Enter the Value:"+a);
	}

}
