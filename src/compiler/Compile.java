package compiler;

import java.io.File;

public interface Compile {
	
	public void setUp();
	public String runCode(File code);
	public boolean isThisCompiler(String name);
	public String getOutputPath();
	
}
