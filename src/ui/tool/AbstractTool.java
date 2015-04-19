package ui.tool;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractTool {
	
	JPanel show;
	JLabel title;
	String name;
	
	public AbstractTool(String name) {
		show=new JPanel();
		show.setBackground(null);
		show.setLayout(null);
		show.setVisible(true);
		this.name=name;
		show.setName(name);
		title=new JLabel();
		title.setText(name);
		show.add(title);
	}
	
	public JPanel getTool() {
		return this.show;
	}
	
	public void setBounds(int x, int y, int width, int height) {
		show.setBounds(x, y, width, height);
		title.setBounds(0, 0, show.getWidth(), 20);
		updateSize();
	}
	
	public abstract void repaint();
	protected abstract void updateSize();

}
