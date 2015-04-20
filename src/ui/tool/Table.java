package ui.tool;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Table extends AbstractTool {
	JTable table;
	JScrollPane js;
	String[] title;
	String[][] body;
	
	public Table(String name){
		super(name);
		table=new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int x,int y){
				return false;
			}
		};
		js=new JScrollPane();
		
		js.add(table);
		show.add(js);
	}
	
	@Override
	public void repaint() {
		table.repaint();
		js.repaint();
		show.repaint();
	}
	
	@Override
	protected void updateSize() {
		table.setBounds(0, 0, show.getWidth(), show.getHeight());
		js.setBounds(0, 0, show.getWidth(), show.getHeight());
	}
	
	@Override
	public void setBounds(int x,int y,int width,int height){
		show.setBounds(x, y, width, height);
		this.updateSize();
	}
	
	public void setColumnSize(int pos,int min,int max){
		table.getColumnModel().getColumn(pos).setMinWidth(min);
		table.getColumnModel().getColumn(pos).setMaxWidth(max);
	}
	
	protected void build() {
		show.remove(js);
		js.remove(table);
		table=new JTable(body, title){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int x,int y){
				return false;
			}
		};
		table.setBounds(0, 0, show.getWidth(), show.getHeight());
		js=new JScrollPane(table);
		js.setBounds(0, 0, show.getWidth(), show.getHeight());
		show.add(js);
	}
	
	protected void setTitle(String[] title){
		this.title=new String[title.length];
		for (int i=0;i<title.length;i++){
			this.title[i]=title[i];
		}
	}
	
	protected void setBody(String[][] body){
		if (body.length==0) return ;
		this.body=new String[body.length][body[0].length];
		for (int i=0;i<body.length;i++){
			for (int j=0;j<body[0].length;j++){
				this.body[i][j]=body[i][j];
			}
		}
	}
}
