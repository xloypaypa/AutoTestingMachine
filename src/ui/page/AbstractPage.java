package ui.page;

import javax.swing.JComponent;
import javax.swing.JPanel;

import ui.UIPage;

public abstract class AbstractPage implements UIPage {
	
	JPanel show;
	String name;
	
	public AbstractPage(String name) {
		show=new JPanel();
		show.setBackground(null);
		show.setLayout(null);
		show.setVisible(true);
		this.name=name;
		show.setName(name);
	}
	
	@Override
	public JComponent getPage() {
		return this.show;
	}
	
	@Override
	public void getInstance() {
		show=new JPanel();
		show.setLayout(null);
		show.setBackground(null);
		show.setBounds(0, 0, 800, 600);
		loadItem();
	}
	
	@Override
	public boolean isThisPage(String name) {
		return this.name.equals(name);
	}
	
	public abstract void loadItem();

}
