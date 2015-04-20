package ui.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import logic.Logic;
import ui.UI;
import ui.tool.CodeChoice;
import ui.tool.FileChoice;
import ui.tool.FolderChoice;

public class ChoicePage extends AbstractPage {
	
	CodeChoice ans;
	CodeChoice codes;
	JButton run;
	
	public ChoicePage() {
		super("choice");
	}

	@Override
	public void loadItem() {
		ans=new CodeChoice("answer", new FileChoice());
		codes=new CodeChoice("codes", new FolderChoice());
		run=new JButton();
		
		ans.setBounds(10, 10, 350, 60);
		codes.setBounds(10, 80, 350, 60);
		run.setBounds(400, 10, 100, 60);
		run.setText("run");
		run.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Logic.setAns(ans.getPath(), ans.getLanguage(), ans.getOut());
				Logic.setCodes(codes.getPath(), codes.getLanguage(), codes.getOut());
				Logic.run();
				for (int i=0;i<Logic.getCodesName().size();i++){
					System.out.println(Logic.getCodesName().get(i)+" : "+Logic.getCodesReturn().get(i));
				}
				UI.showPage("status");
			}
		});
		
		show.add(run);
		show.add(ans.getTool());
		show.add(codes.getTool());
	}

	@Override
	public void repaint() {
		ans.repaint();
		codes.repaint();
		show.repaint();
	}

}
