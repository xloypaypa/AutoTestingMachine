package logic;

import java.io.File;
import java.util.Vector;

public class Logic {
	
	static String ansPath, ansl, anso;
	static String codesPath, codesl, codeso;
	static Vector<String> code=new Vector<>();
	static Vector<String> ans=new Vector<>();
	
	public static void setAns(String path, String language, String outFile) {
		ansPath=path;
		ansl=language;
		anso=outFile;
	}
	
	public static void setCodes(String path, String language, String outFile) {
		codesPath=path;
		codesl=language;
		codeso=outFile;
	}
	
	public static void run() {
		CodeCheckeLogic check=new CodeCheckeLogic();
		check.setAnswerCode(ansPath, ansl, anso);
		
		File codes=new File(codesPath);
		File[] kids=codes.listFiles();
		for (int i=0;i<kids.length;i++){
			if (kids[i].isDirectory()) continue;
			
			code.addElement(kids[i].getName());
			ans.addElement(check.checkCode(kids[i].getAbsolutePath(), codesl, codeso));
		}
	}
	
	public static Vector<String> getCodesName() {
		return code;
	}
	
	public static Vector<String> getCodesReturn() {
		return ans;
	}

}
