package runner;

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
            		code.destroy();
            		setExitValue("time limit exceed");
            	}
                timer.cancel();
            }
        };
        
		code = codeStart();
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
	
	private void setExitValue(String value){
		if (this.exitValue==null){
			this.exitValue=value;
		}
	}
	
	public abstract Process codeStart();

}
