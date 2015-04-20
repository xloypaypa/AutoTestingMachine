package ui.tool;

import java.io.File;

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
		File file=new File("");
		chooser.setCurrentDirectory(new File(file.getAbsolutePath()));
		ret=chooser.showOpenDialog(chooser);
		if (ret==JFileChooser.APPROVE_OPTION){
			ans=chooser.getSelectedFile().getPath();
			path.setText(ans);
		}
	}

}
