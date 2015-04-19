package ui.tool;

import javax.swing.JFileChooser;

public class FileChoice extends PathChoice {

	public FileChoice() {
		super("file choice");
	}

	@Override
	protected void choiceAction() {
		JFileChooser chooser=new JFileChooser();
		String ans;
		int ret;
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		ret=chooser.showOpenDialog(chooser);
		if (ret==JFileChooser.APPROVE_OPTION){
			ans=chooser.getSelectedFile().getPath();
			path.setText(ans);
		}
	}
	
}
