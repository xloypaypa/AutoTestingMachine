package runner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Runner extends Thread {
	
	String filePath;
	Process code;
	long timeLimit, usedTime;
	String exitValue;
	
	@Override
	public void run() {
		exitValue=null;
		Timer timer = new Timer();
        TimerTask tast=new TimerTask() { 
            @Override
            public void run() {
            	if (code.isAlive()){
            		code.destroyForcibly();
            		setExitValue("time limit exceed");
            	}
                timer.cancel();
            }
        };
        
		code = codeStart();
		
		drainInBackground(code.getErrorStream());
		drainInBackground(code.getInputStream());
		
		usedTime=System.currentTimeMillis();
		timer.schedule(tast, timeLimit);
		
		try {
			code.waitFor();
			usedTime=System.currentTimeMillis()-usedTime;
			if (code.exitValue()==0){
				setExitValue("ok");
			}else{
				setExitValue("runtime error");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			code.destroyForcibly();
		}
	}
	
	public void setTimeLimit(long limit) {
		this.timeLimit=limit;
	}
	
	public String getExitValue(){
		return this.exitValue;
	}
	
	public long getUsedTime() {
		return this.usedTime;
	}
	
	public void setFile(String path){
		this.filePath=path;
	}
	
	protected static void drainInBackground(final InputStream is) {
		new Thread(new Runnable() {
			public void run() {
				try {
					while (true){
						int value=is.read();
						if (value<0) break;
						System.out.print(value);
					}
				} catch (IOException e) {
				}
			}
		}).start();
	}
	
	private void setExitValue(String value){
		if (this.exitValue==null){
			this.exitValue=value;
		}
	}
	
	public abstract Process codeStart();

}
