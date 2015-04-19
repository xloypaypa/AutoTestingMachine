package compiler;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Runner extends Thread {
	
	Process code;
	long timeLimit;
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
		timer.schedule(tast, timeLimit);
		
		try {
			code.waitFor();
			if (code.exitValue()==0){
				setExitValue("ok");
			}else{
				setExitValue("runtime error");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setTimeLimit(long limit) {
		this.timeLimit=limit;
	}
	
	public String getExitValue(){
		return this.exitValue;
	}
	
	private void setExitValue(String value){
		if (this.exitValue==null){
			this.exitValue=value;
		}
	}
	
	public abstract Process codeStart();

}
