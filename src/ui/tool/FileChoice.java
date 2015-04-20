package ui.tool;

import java.io.File;

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
		File file=new File("");
		chooser.setCurrentDirectory(new File(file.getAbsolutePath()));
		ret=chooser.showOpenDialog(chooser);
		if (ret==JFileChooser.APPROVE_OPTION){
			ans=chooser.getSelectedFile().getPath();
			path.setText(ans);
		}
	}
	
}
