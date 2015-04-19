package ui.tool;

import javax.swing.JFileChooser;

public class FolderChoice extends PathChoice {
	
	public FolderChoice() {
		super("folder choice");
	}

	@Override
	protected void choiceAction() {
		JFileChooser chooser=new JFileChooser();
		String ans;
		int ret;
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		ret=chooser.showOpenDialog(chooser);
		if (ret==JFileChooser.APPROVE_OPTION){
			ans=chooser.getSelectedFile().getPath();
			path.setText(ans);
		}
	}

}
