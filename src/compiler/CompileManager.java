package compiler;

import java.util.Vector;

public class CompileManager {
	
	protected static Vector<Compile> compiler=new Vector<>();
	
	public static void addCompiler(Compile compiler) {
		CompileManager.compiler.addElement(compiler);
	}
	
	public static void cleanCompiler() {
		compiler.removeAllElements();
	}
	
	public static Compile getCompiler(String name) {
		for (int i=0;i<compiler.size();i++){
			if (compiler.get(i).isThisCompiler(name)) return compiler.get(i);
		}
		return null;
	}
	
	public static Vector<String> getAllCompiler() {
		Vector<String> ans=new Vector<>();
		for (int i=0;i<compiler.size();i++) {
			ans.addElement(compiler.get(i).getName());
		}
		return ans;
	}

}
