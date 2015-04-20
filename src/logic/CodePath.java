package logic;

public class CodePath {
	
	public String path, language, out;
	public long timeLimit, runTime;
	public String status;
	
	public CodePath() {
		path=new String();
		language=new String();
		out=new String();
		status=new String();
		timeLimit=1000;
		runTime=0;
	}
	
	public CodePath(CodePath other){
		path=new String(other.path);
		language=new String(other.language);
		out=new String(other.out);
		status=new String(other.status);
		timeLimit=other.timeLimit;
		runTime=other.runTime;
	}
	
	
}
