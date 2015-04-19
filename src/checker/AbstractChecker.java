package checker;

public abstract class AbstractChecker implements Checker {
	
	protected String answerPath;
	
	public void setAnswer(String path){
		this.answerPath=path;
	}

}
