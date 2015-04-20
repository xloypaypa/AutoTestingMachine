package ui.tool;

import javax.swing.JComboBox;

import compiler.CompileManager;

public class LanguageChoice extends AbstractTool {
	
	JComboBox<String> choice;
	
	public LanguageChoice() {
		super("language");
		choice=new JComboBox<>();
		
		for (int i=0;i<CompileManager.getAllCompiler().size();i++){
			choice.addItem(CompileManager.getAllCompiler().get(i));
		}
		
		show.add(choice);
	}
	
	public String getChoice() {
		return choice.getSelectedItem().toString();
	}

	@Override
	public void repaint() {
		choice.removeAllItems();
		for (int i=0;i<CompileManager.getAllCompiler().size();i++){
			choice.addItem(CompileManager.getAllCompiler().get(i));
		}
		
		choice.repaint();
		show.repaint();
	}

	@Override
	protected void updateSize() {
		choice.setBounds(0, 20, show.getWidth(), 20);
	}
	
	
}
