package Lexer_Project;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Shank {
	
	public static void main(String[] args)throws IOException{
		args= new String[1];
		args[0] = "/Users/karanpatel/eclipse-workspace/ICSI_311/src/Lexer_Project/values.txt";//Path
		//args[0] = "values.txt";//Path
		ArrayList<Tokens> tokens = new ArrayList<>();
		if(args.length!=1) {
			throw new IOException("File not found");
		}
		else {
			String file=args[0];
			try { 
				//prints tokens.
				for(String readlines: Files.readAllLines(Paths.get(file))) {
					Lexer1 vD = new Lexer1();
					tokens.addAll(vD.vD(readlines));
					String tk ="";
				}
				for(int i=0; i<tokens.size();i++) {
					System.out.print(tokens.get(i)+" ");
					if(tokens.get(i).getType()==Tokens.Type.EOL) {
						System.out.println("\n");
					}
				}
				Parser parser = new Parser(tokens);
				FunctionAST ast = parser.parse();// AST node created.
				SemanticAnalysis semanticAnalysis = new SemanticAnalysis();
				semanticAnalysis.ChechAssignment(ast.getStatements());
				Interpreter interpret = new Interpreter(ast);
				if(ast!=null) {
					System.out.println(ast.toString());
					//FunctionNode ast_call = parser.parse_call();// AST node created.
					//System.out.println(ast_call.toString());
					interpret.InterpreterFunction(ast); //Expression being executed.
					interpret.InterpreterBlock(ast.getStatements());
					//System.out.println("Answer: "+Expression);// prints the answer after calculating the expression.
				}

			}
			
			catch (FileNotFoundException e) {
				throw new IOException("File not readable.");
			}
		}
	}
}
	
