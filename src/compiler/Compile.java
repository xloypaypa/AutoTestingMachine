package compiler;

import java.io.File;

import runner.Runner;

public interface Compile {
	
	public void setUp();
	public String compileCode(File code);
	public boolean isThisCompiler(String name);
	public File getRunableFile(File code);
	public Runner getRunner();
	public String getName();
	
}
