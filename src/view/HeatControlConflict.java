package view;

import heatingClasses.Structure;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeatControlConflict extends JDialog{
	
	private List<String> initialConflicts;
	
	private List<String> confirmedConflicts;
	
	private List<JCheckBox> conflictCheckboxes;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JPanel conflictPanel;

	public HeatControlConflict(List<String> conflicts, int newTemp) {
		
		JLabel conflictLabel = new JLabel("<html>The following conflicts where found.<br>Toggle those that shall be overwritten on confirmation.<br>New temperature: " + newTemp + "</html>");
		
		JButton confirmButton = new JButton("confirm selection");
		confirmButton.addActionListener(new ConfirmListener());
		
		JButton cancelButton = new JButton("cancel");
		cancelButton.addActionListener(new CancelListener());
		
		scrollPane = new JScrollPane();
		
		JButton checkAllButton = new JButton("select all");
		checkAllButton.addActionListener(new SelectAllListener());
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(conflictLabel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(cancelButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(confirmButton)
							.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
							.addComponent(checkAllButton)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(conflictLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton)
						.addComponent(confirmButton)
						.addComponent(checkAllButton))
					.addContainerGap())
		);
		
		conflictPanel = new JPanel();
		scrollPane.setViewportView(conflictPanel);
		conflictPanel.setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().setLayout(groupLayout);
		
		conflictCheckboxes = new ArrayList<JCheckBox>(); 
		initialConflicts = conflicts;
		
		for(String s : conflicts){
			String[] p = s.split(",");
			String x = Structure.heatingPlanColumns[Integer.parseInt(p[0])+1] + "," + Structure.heatingPlanTimeBlocks[Integer.parseInt(p[1])] + " - " + p[2] + "(" + p[3] + ")";
			JCheckBox cb = new JCheckBox(x);
			conflictPanel.add(cb);
			conflictCheckboxes.add(cb);
		}
	}
	
	public List<String> getResult(){
		return this.confirmedConflicts;
	}
	
	private class SelectAllListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for(JCheckBox cb : conflictCheckboxes){
				cb.setSelected(true);
			}
		}
	}
	
	private class CancelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			confirmedConflicts = null;
			dispose();
		}
	}
	
	private class ConfirmListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			confirmedConflicts = new ArrayList<String>();
			
			for(int i = 0; i < conflictCheckboxes.size(); i++){
				JCheckBox cb = conflictCheckboxes.get(i);
				
				if(cb.isSelected()){
					confirmedConflicts.add(initialConflicts.get(i));
				}
			}
			
			dispose();
		}
	}
}