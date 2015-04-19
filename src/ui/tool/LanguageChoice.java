package ui.tool;

import java.util.Vector;

import javax.swing.JComboBox;

public class LanguageChoice extends AbstractTool {
	
	static Vector<String> language=new Vector<>();
	JComboBox<String> choice;
	
	public LanguageChoice() {
		super("language");
		choice=new JComboBox<>();
		
		for (int i=0;i<language.size();i++){
			choice.addItem(language.get(i));
		}
		
		show.add(choice);
	}
	
	public static void addLanguage(String language) {
		LanguageChoice.language.addElement(language);
	}
	
	public static void cleanLanguage() {
		LanguageChoice.language.removeAllElements();
	}
	
	public String getChoice() {
		return choice.getSelectedItem().toString();
	}

	@Override
	public void repaint() {
		choice.removeAllItems();
		for (int i=0;i<language.size();i++){
			choice.addItem(language.get(i));
		}
		
		choice.repaint();
		show.repaint();
	}

	@Override
	protected void updateSize() {
		choice.setBounds(0, 20, show.getWidth(), 20);
	}
	
	
}
