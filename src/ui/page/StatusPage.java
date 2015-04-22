package ui.page;

import javax.swing.JOptionPane;

import logic.CodePath;
import logic.Logic;
import ui.tool.StatusTable;

public class StatusPage extends AbstractPage {
	
	StatusTable table;
	Logic logic;
	boolean flag;
	
	public StatusPage() {
		super("status");
		table=new StatusTable();
		show.add(table.getTool());
	}

	@Override
	public void repaint() {
		table.repaint();
		show.repaint();
	}

	@Override
	public void loadItem() {
		show.remove(table.getTool());
		table=new StatusTable();
		table.setBounds(10, 10, 700, 500);
		table.setValue(logic,flag);
		show.add(table.getTool());
		repaint();
	}
	
	public void runLogic(){
		logic.start();
		new Thread(){
			@Override
			public void run(){
				flag=true;
				CodePath past=new CodePath();
				while (logic.isAlive()){
					if (!past.path.equals(logic.getNowCode().path)){
						updateData();
						past=new CodePath(logic.getNowCode());
					}else if (!past.status.equals(logic.getNowCode().status)){
						updateData();
						past=new CodePath(logic.getNowCode());
					}
				}
				flag=false;
				updateData();
				JOptionPane.showMessageDialog(null,"running finish","message",JOptionPane.INFORMATION_MESSAGE);
			}
		}.start();
	}

	protected void updateData() {
		loadItem();
		repaint();
		window.repaint();
	}
	
	public void setLogic(Logic logic){
		this.logic=logic;
	}

}
