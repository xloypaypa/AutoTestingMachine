package ui.tool;

import logic.Logic;

public class StatusTable extends Table {
	
	public StatusTable() {
		super("status table");
	}
	
	public void getValue() {
		String[] title={"code", "return"};
		String[][] body;
		
		int length=Logic.getCodesName().size();
		if (length!=0) body=new String[length][2];
		else body=new String[1][2];
		
		for (int i=0;i<length;i++){
			body[i][0]=Logic.getCodesName().get(i);
			body[i][1]=Logic.getCodesReturn().get(i);
		}
		
		setTitle(title);
		setBody(body);
		build();
		
		setColumnSize(0, 300, 500);
	}

}
