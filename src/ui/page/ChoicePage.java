package ui.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

import logic.Logic;
import ui.tool.CodeChoice;
import ui.tool.FileChoice;
import ui.tool.FolderChoice;

public class ChoicePage extends AbstractPage {
	
	CodeChoice ans;
	Vector<CodeChoice> code;
	JButton run,add,remove;
	
	public ChoicePage() {
		super("choice");
	}

	@Override
	public void loadItem() {
		CodeChoice now;
		ans=new CodeChoice("answer", new FileChoice());
		now=new CodeChoice("codes", new FolderChoice());
		run=new JButton();
		add=new JButton();
		
		code=new Vector<>();
		code.addElement(now);
		
		ans.setBounds(10, 10, 350, 60);
		now.setBounds(10, 80, 350, 60);
		run.setBounds(400, 10, 100, 60);
		run.setText("run");
		run.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Long ansLimit;
				try{
					ansLimit=Long.valueOf(ans.getTimeLimit());
				}catch (Exception e1){
					return ;
				}
				
				Logic logic=new Logic();
				logic.setAns(ans.getPath(), ans.getLanguage(), ans.getOut(),ansLimit);
				for (int i=0;i<code.size();i++){
					CodeChoice now=code.get(i);
					Long codesLimit;
					try{
						codesLimit=Long.valueOf(now.getTimeLimit());
					}catch (Exception e1){
						return ;
					}
					logic.addCodes(now.getPath(), now.getLanguage(), now.getOut(),codesLimit);
				}
				StatusPage kid=new StatusPage();
				kid.setLogic(logic);
				kid.loadItem();
				kid.runLogic();
			}
		});
		add.setText("add folder");
		add.setBounds(400, 80, 100, 60);
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CodeChoice now=new CodeChoice("codes", new FolderChoice());
				int num=code.size();
				now.setBounds(10, (num+1)*70+10, 350, 60);
				code.addElement(now);
				show.add(now.getTool());
				repaint();
			}
		});
		
		remove=new JButton();
		remove.setText("remove folder");
		remove.setBounds(400, 150, 100, 60);
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CodeChoice last=code.lastElement();
				show.remove(last.getTool());
				code.removeElementAt(code.size()-1);
				repaint();
			}
		});
		
		show.add(run);
		show.add(remove);
		show.add(ans.getTool());
		show.add(now.getTool());
		show.add(add);
		repaint();
	}

	@Override
	public void repaint() {
		ans.repaint();
		for (int i=0;i<code.size();i++){
			code.get(i).repaint();
		}
		show.repaint();
	}

}
