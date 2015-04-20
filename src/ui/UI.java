package ui;

import javax.swing.JFrame;

public class UI {
	
	public JFrame window;
	
	private UIPage nowPage;
	
	protected void createWindow(String name) {
		nowPage=null;
		window=new JFrame(name);
		window.setSize(800, 600);
		window.setResizable(false);
		window.setLayout(null);
		window.setVisible(true);
	}
	
	public void setMainPage() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void repaint() {
		if (nowPage == null) return ;
		nowPage.repaint();
	}
	
	public void close() {
		window.dispose();
	}
	
}
