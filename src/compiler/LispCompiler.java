package compiler;

import java.io.File;
import java.io.IOException;

public class LispCompiler extends AbstractCompiler {
	
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
	        
	        moveFile(now, aimPath);
	        
	        now=new File(getLibPath(code.getAbsolutePath()));
	        now.delete();
            return "ok";
	    }catch(Exception e){
	        e.printStackTrace();
	        return "cmd error";
	    }
	}
	
	@Override
	protected String run() {
		Runner run=new Runner() {
			@Override
			public Process codeStart() {
				try {
					return Runtime.getRuntime().exec("cmd /c clisp -i "+aimPath);
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		};
		run.setTimeLimit(1000);
		run.start();
		
		while (run.isAlive());
		return run.getExitValue();
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
	
	protected String getAimPath(File code) {
		String name=code.getName().substring(0, code.getName().length()-4);
		return runPath+"/"+name+"fas";
	}

}
