package logic;

import java.io.File;
import java.util.Vector;

import checker.AbstractChecker;
import checker.NormalChecker;
import runner.Runner;
import compiler.Compile;
import compiler.CompileManager;

public class Logic extends Thread {
	
	AbstractChecker checker=new NormalChecker();
	
	CodePath ansCode, nowCode=new CodePath();
	Vector<CodePath> codes=new Vector<>();
	
	Vector <CodePath> runnedcodes=new Vector<>();
	
	public void setAns(String path, String language, String outFile, long limit) {
		ansCode=new CodePath();
		ansCode.path=path;
		ansCode.language=language;
		ansCode.out=outFile;
		ansCode.timeLimit=limit;
	}
	
	public void addCodes(String path, String language, String outFile, long limit) {
		CodePath now=new CodePath();
		now.path=path;
		now.language=language;
		now.out=outFile;
		now.timeLimit=limit;
		codes.addElement(now);
	}
	
	@Override
	public void run() {
		Runner runner;
		Compile compile=CompileManager.getCompiler(ansCode.language);
		compile.compileCode(new File(ansCode.path));
		runner=compile.getRunner();
		runner.setFile(compile.getRunableFile(new File(ansCode.path)).getName());
		runner.setTimeLimit(ansCode.timeLimit);
		runner.run();
		checker.setAnswer(ansCode.out);
		
		runnedcodes=new Vector<>();
		
		for (int i=0;i<codes.size();i++){
			CodePath now=codes.get(i);
			runCodes(now.path, now.language, now.out, now.timeLimit);
		}
	}

	protected void runCodes(String path, String language, String out, long limit) {
		File codes=new File(path);
		File[] kids=codes.listFiles();
		for (int i=0;i<kids.length;i++){
			if (kids[i].isDirectory()) continue;
			
			runCode(kids[i].getAbsolutePath(), language, out, limit);
		}
	}
	
	private void runCode(String path, String language, String outFile, long limit){
		nowCode=new CodePath();
		
		nowCode.path=path;
		nowCode.language=language;
		nowCode.out=outFile;
		nowCode.timeLimit=limit;
		nowCode.status="compiling";
		
		Compile compile=CompileManager.getCompiler(language);
		nowCode.status=compile.compileCode(new File(path));
		if (!nowCode.status.equals("ok")) {
			runnedcodes.addElement(nowCode);
			return ;
		}else{
			nowCode.status="running";
		}
		
		Runner runner=compile.getRunner();
		runner.setFile(compile.getRunableFile(new File(path)).getName());
		runner.setTimeLimit(limit);
		runner.run();
		nowCode.status=runner.getExitValue();
		if (!nowCode.status.equals("ok")){
			runnedcodes.addElement(nowCode);
			return ;
		}
		
		if (checker.checkFile(outFile)){
			nowCode.status="accept";
			nowCode.runTime=runner.getUsedTime();
			runnedcodes.addElement(nowCode);
		}else{
			nowCode.status="wrong answer";
			runnedcodes.addElement(nowCode);
			return ;
		}
	}
	
	public CodePath getNowCode(){
		return new CodePath(this.nowCode);
	}
	
	public Vector<CodePath> getCodes() {
		return runnedcodes;
	}

}
