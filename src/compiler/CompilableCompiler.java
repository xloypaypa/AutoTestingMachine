package compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class CompilableCompiler extends AbstractCompiler {
	
	public CompilableCompiler(String name) {
		super(name);
	}
	
	@Override
	public String compileCode(File code) {
		String compileReturn = compile(code);
		moveToFolder(getCompiledFile(code), new File(""));
		return compileReturn;
	}
	
	@Override
	public File getRunableFile(File code) {
		return new File(new File("").getAbsolutePath()+"/"+getCompiledFile(code).getName());
	}
	
	protected abstract String compile(File code);
	protected abstract File getCompiledFile(File code);
	
	protected static void drainInBackground(final InputStream is) {
		new Thread(new Runnable() {
			public void run() {
				try {
					while (is.read() >= 0);
				} catch (IOException e) {
				}
			}
		}).start();
	}
	
	private void moveToFolder(File now, File aimPath) {
		if (now.getAbsolutePath().equals(aimPath.getAbsolutePath()+"/"+now.getName())){
			return ;
		}
		
		try {
			InputStream inStream;
			inStream = new FileInputStream(now);
			FileOutputStream fs = new FileOutputStream(aimPath.getAbsolutePath()+"/"+now.getName()); 
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

}
