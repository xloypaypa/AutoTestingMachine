package ui;

import javax.swing.JComponent;

public interface UIPage {
	
	public boolean isThisPage(String name);
	public JComponent getPage();
	public void getInstance();
	public void repaint();

}
