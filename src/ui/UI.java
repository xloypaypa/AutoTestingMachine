package ui;

import java.util.Vector;

import javax.swing.JFrame;

public class UI {
	
	public static JFrame window;
	
	private static Vector<UIPage> pages=new Vector<>();
	private static UIPage nowPage;
	
	public static void createWindow() {
		nowPage=null;
		window=new JFrame("auto testing machine");
		window.setBounds(200, 100, 800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLayout(null);
		window.setVisible(true);
	}
	
	public static void addPage(UIPage page) {
		pages.addElement(page);
	}
	
	public static void cleanPage() {
		pages.removeAllElements();
	}
	
	public static void showPage(String page) {
		
		if (nowPage!=null) {
			window.remove(nowPage.getPage());
		}
		
		for (int i=0;i<pages.size();i++){
			if (pages.get(i).isThisPage(page)){
				nowPage=pages.get(i);
				nowPage.getInstance();
				window.add(nowPage.getPage());
				repaint();
			}
		}
	}
	
	public static void repaint() {
		if (nowPage == null) return ;
		nowPage.repaint();
	}
	
}
