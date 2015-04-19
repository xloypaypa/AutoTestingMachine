package compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractCompiler implements Compile {
	
	protected String name, aimPath;
	protected static String runPath;
	protected File father;
	protected File[] past;
	
	public AbstractCompiler(String name) {
		this.name = name;
		runPath="./run path/";
	}
	
	@Override
	public String runCode(File code) {
		saveRootPath();
		aimPath = this.getAimPath(code);
		String compileAns=compile(code);
		if (compileAns!="ok"){
			return compileAns;
		}
		String runAns=run();
		moveCodeAnswer();
		return runAns;
	}
	
	@Override
	public boolean isThisCompiler(String name) {
		return this.name.equals(name);
	}
	
	@Override
	public String getOutputPath() {
		return getRunPath();
	}
	
	public static String getRunPath() {
		return runPath;
	}
	
	public static void setRunPath(String runPath) {
		AbstractCompiler.runPath = runPath;
	}
	
	protected void moveFile(File now, String aimPath) {
		try {
			InputStream inStream;
			inStream = new FileInputStream(now);
			FileOutputStream fs = new FileOutputStream(aimPath); 
			byte[] buffer = new byte[1444];
			int byteread = 0; 
			while ( (byteread = inStream.read(buffer)) != -1) {  
			    fs.write(buffer, 0, byteread); 
			} 
			inStream.close(); 
			fs.close();
			now.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void moveToFolder(File now, String aimPath){
		try {
			InputStream inStream;
			inStream = new FileInputStream(now);
			FileOutputStream fs = new FileOutputStream(aimPath+"/"+now.getName()); 
			byte[] buffer = new byte[1444];
			int byteread = 0; 
			while ( (byteread = inStream.read(buffer)) != -1) {  
			    fs.write(buffer, 0, byteread); 
			} 
			inStream.close(); 
			fs.close();
			now.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void moveCodeAnswer() {
		File[] now=new File(father.getAbsolutePath()).listFiles();
		for (int i=0;i<now.length;i++){
			boolean flag=false;
			for (int j=0;j<past.length;j++){
				if (now[i].compareTo(past[j])==0){
					flag=true;
				}
			}
			if (!flag){
				moveToFolder(now[i], runPath);
			}
		}
	}

	protected void saveRootPath() {
		father=new File("");
		past=new File(father.getAbsolutePath()).listFiles();
	}
	
	protected abstract String compile(File code);
	protected abstract String getAimPath(File code);
	protected abstract String run();

}
