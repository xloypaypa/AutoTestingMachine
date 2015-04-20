package ui.tool;

import logic.CodePath;
import logic.Logic;

public class StatusTable extends Table {
	
	public StatusTable() {
		super("status table");
	}
	
	public void setValue(Logic logic,boolean flag) {
		String[] title={"code", "language", "return", "time"};
		String[][] body;
		
		int length=logic.getCodes().size();
		if (length!=0) body=new String[length+1][4];
		else body=new String[1][4];
		
		for (int i=0;i<length;i++){
			body[i][0]=logic.getCodes().get(i).path;
			body[i][1]=logic.getCodes().get(i).language;
			body[i][2]=logic.getCodes().get(i).status;
			body[i][3]=logic.getCodes().get(i).runTime+"";
		}
		
		if (flag){
			CodePath now=logic.getNowCode();
			body[length][0]=now.path;
			body[length][1]=now.language;
			body[length][2]=now.status;
			body[length][3]=now.runTime+"";
		}
		
		
		setTitle(title);
		setBody(body);
		build();
		
		setColumnSize(0, 300, 500);
	}

}
