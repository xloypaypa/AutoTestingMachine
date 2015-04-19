package ui.tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public abstract class PathChoice extends AbstractTool {
	
	JTextField path;
	JButton choice;
	
	public PathChoice(String name) {
		super(name);
		path=new JTextField();
		choice=new JButton();
		
		choice.setText("...");
		choice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choiceAction();
			}
		});
		show.add(path);
		show.add(choice);
	}

	@Override
	public void repaint() {
		show.repaint();
	}

	@Override
	protected void updateSize() {
		path.setBounds(0, 20, show.getWidth()-40, 20);
		choice.setBounds(show.getWidth()-35, 20, 35, 20);
	}
	
	public String getPath() {
		return this.path.getText();
	}
	
	protected abstract void choiceAction();

}
