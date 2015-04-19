package logic;

import java.io.File;
import java.util.Vector;

import compiler.Compile;
import checker.AbstractChecker;
import checker.NormalChecker;

public class CodeCheckeLogic {
	
	protected AbstractChecker checker;
	
	protected static Vector<Compile> compiler=new Vector<>();
	
	public CodeCheckeLogic() {
		checker=new NormalChecker();
	}
	
	public static void addCompiler(Compile compiler){
		CodeCheckeLogic.compiler.addElement(compiler);
	}
	
	public void setChecker(AbstractChecker checker){
		this.checker=checker;
	}
	
	public void setAnswerCode(String path, String language, String ansFile){
		checker.setAnswer(runCode(path, language).getOutputPath()+"/"+ansFile);
	}
	
	public String checkCode(String code, String language, String ansFile){
		Compile compile = null;
		String runAns = null;
		for (int i=0;i<compiler.size();i++){
			if (compiler.get(i).isThisCompiler(language)){
				compiler.get(i).setUp();
				runAns = compiler.get(i).runCode(new File(code));
				compile=compiler.get(i);
				break;
			}
		}
		
		if (runAns.equals("ok")){
			if (checker.checkFile(compile.getOutputPath()+"/"+ansFile)){
				return "accept";
			}else {
				return "wrong answer";
			}
		}else{
			return runAns;
		}
	}
	
	protected Compile runCode(String path, String language) {
		for (int i=0;i<compiler.size();i++){
			if (compiler.get(i).isThisCompiler(language)){
				compiler.get(i).setUp();
				compiler.get(i).runCode(new File(path));
				return compiler.get(i);
			}
		}
		System.out.println("null error");
		return null;
	}
	
}
