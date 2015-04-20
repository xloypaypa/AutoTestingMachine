package ui.tool;

import javax.swing.JTextField;

public class Input extends AbstractTool {
	
	JTextField message;
	
	public Input(String title) {
		super(title);
		message=new JTextField();
		show.add(message);
	}

	@Override
	public void repaint() {
		message.repaint();
		show.repaint();
	}

	@Override
	protected void updateSize() {
		message.setBounds(0, 20, show.getWidth(), 20);
	}
	
	public String getMessage() {
		return message.getText();
	}
	
	public void cleanMessage(){
		message.setText("");
	}

}
