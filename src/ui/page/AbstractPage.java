package ui.page;

import javax.swing.JComponent;
import javax.swing.JPanel;

import ui.UI;
import ui.UIPage;

public abstract class AbstractPage extends UI implements UIPage {
	
	JPanel show;
	String name;
	
	public AbstractPage(String name) {
		createWindow(name);
		show=new JPanel();
		show.setBackground(null);
		show.setLayout(null);
		show.setVisible(true);
		show.setBounds(0, 0, 800, 600);
		this.name=name;
		show.setName(name);
		window.add(show);
	}
	
	@Override
	public JComponent getPage() {
		return this.show;
	}
	
	@Override
	public boolean isThisPage(String name) {
		return this.name.equals(name);
	}
	
	public abstract void loadItem();

}
