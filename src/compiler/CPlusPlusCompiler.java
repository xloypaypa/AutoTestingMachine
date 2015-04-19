package compiler;

import java.io.File;
import java.io.IOException;

public class CPlusPlusCompiler extends AbstractCompiler {
	
	public CPlusPlusCompiler() {
		super("c++");
	}

	@Override
	public void setUp() {}
	
	@Override
	protected String compile(File code) {
		try {
			String codePath=code.getAbsolutePath();
	        String cmdStr = "cmd /c g++ "+codePath+" -o "+codePath+".exe";
	        Process compiler = Runtime.getRuntime().exec(cmdStr);
	        compiler.waitFor();
	        if (compiler.exitValue()!=0) return "compile error";
	        
	        File now=new File(codePath+".exe");
	        if (!now.exists()) return "system error"; 
	        
	        moveFile(now, aimPath);
            return "ok";
	    }catch(Exception e){
	        e.printStackTrace();
	        return "cmd error";
	    }
	}
	
	protected String getAimPath(File code) {
		return runPath+"/"+code.getName()+".exe";
	}

	@Override
	protected String run() {
		Runner run=new Runner() {
			@Override
			public Process codeStart() {
				try {
					return Runtime.getRuntime().exec(aimPath);
				} catch (IOException e) {
					return null;
				}
			}
		};
		run.setTimeLimit(1000);
		run.start();
		
		while (run.isAlive());
		
		return run.getExitValue();
	}

}
