package compiler;

import java.io.File;

import runner.NormalRunner;
import runner.Runner;

public class LispCompiler extends CompilableCompiler {
	
	public LispCompiler() {
		super("lisp");
	}

	@Override
	public void setUp() {}

	@Override
	protected String compile(File code) {
		try {
			String cmdStr = "cmd /c clisp -c "+code.getAbsolutePath();
	        Process compiler = Runtime.getRuntime().exec(cmdStr);
	        compiler.waitFor();
	        if (compiler.exitValue()!=0) return "compile error";
	        
	        File now=new File(getCompiledPath(code.getAbsolutePath()));
	        if (!now.exists()) return "system error";
	        
	        now=new File(getLibPath(code.getAbsolutePath()));
	        now.delete();
            return "ok";
	    }catch(Exception e){
	        e.printStackTrace();
	        return "cmd error";
	    }
	}

	@Override
	protected File getCompiledFile(File code) {
		return new File(getCompiledPath(code.getAbsolutePath()));
	}
	
	@Override
	public Runner getRunner() {
		return new NormalRunner();
	}
	
	protected String getCompiledPath(String path){
		String ans=new String();
		ans=path.substring(0, path.length()-4);
		ans+="fas";
		return ans;
	}
	
	protected String getLibPath(String path){
		String ans=new String();
		ans=path.substring(0, path.length()-4);
		ans+="lib";
		return ans;
	}

}
