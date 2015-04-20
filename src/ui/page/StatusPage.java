package ui.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.UI;
import ui.tool.StatusTable;

public class StatusPage extends AbstractPage {
	
	StatusTable table;
	JButton back;
	
	public StatusPage() {
		super("status");
	}

	@Override
	public void repaint() {
		table.repaint();
		show.repaint();
	}

	@Override
	public void loadItem() {
		table=new StatusTable();
		table.setBounds(10, 10, 700, 500);
		table.getValue();
		show.add(table.getTool());
		
		back=new JButton();
		back.setBounds(700, 550, 100, 20);
		back.setText("back");
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UI.showPage("choice");
			}
		});
		show.add(back);
	}

}
