package view;

import heatingClasses.Structure;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;

@SuppressWarnings("rawtypes")
public class HeatControlPopup extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox dayComboBox;
	private JComboBox fromComboBox;
	private JComboBox toComboBox;
	private JLabel newTempLabel;
	private JSlider tempSlider;
	
	private List<Integer> selectedHeating;
	private JLabel infoLabel;
	private JLabel lblUntil;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	@SuppressWarnings("unchecked")
	public HeatControlPopup() {
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton cancelButton = new JButton("cancel");
		cancelButton.addActionListener(new CancelListener());
		panel_2.add(cancelButton);
		
		JButton confirmButton = new JButton("confirm heating program");
		confirmButton.addActionListener(new ConfirmListener());
		panel_2.add(confirmButton);
		
		int minTemp = 0;
		int maxTemp = 40;
		int initTemp = 22;
		tempSlider = new JSlider(JSlider.HORIZONTAL, minTemp, maxTemp, initTemp);
		tempSlider.addChangeListener(new TempChangeListener());
		tempSlider.setMajorTickSpacing(10);
		tempSlider.setMinorTickSpacing(2);
		tempSlider.setPaintTicks(true);
		tempSlider.setPaintLabels(true);
		
		newTempLabel = new JLabel("Temperature: " + initTemp);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(newTempLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tempSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(186))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(tempSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(newTempLabel))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		String[] daySels = Arrays.copyOfRange(Structure.heatingPlanColumns, 1, Structure.heatingPlanColumns.length); 
		
		lblNewLabel = new JLabel("Day:");
		dayComboBox = new JComboBox(daySels);
		
		lblNewLabel_1 = new JLabel("Blocks:");
		
		fromComboBox = new JComboBox(Structure.heatingPlanTimeBlocks);
		
		lblUntil = new JLabel("until");
		
		toComboBox = new JComboBox(Structure.heatingPlanTimeBlocks);
		
		infoLabel = new JLabel("Heating info");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(infoLabel, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dayComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fromComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUntil)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(toComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(dayComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(fromComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUntil)
						.addComponent(toComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infoLabel)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
	
	public List<Integer> getResults(){
		return selectedHeating;
	}
	
	private class CancelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedHeating = null;
			dispose();
		}
	}
	
	private class ConfirmListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(fromComboBox.getSelectedIndex() <= toComboBox.getSelectedIndex()){
				selectedHeating = new ArrayList<Integer>();
				
				//compute selected heating
				selectedHeating.add(dayComboBox.getSelectedIndex());
				selectedHeating.add(fromComboBox.getSelectedIndex());
				selectedHeating.add(toComboBox.getSelectedIndex());
				selectedHeating.add(tempSlider.getValue());
				
				dispose();
			} else{
				infoLabel.setText("'To' heating time has to be equal or later compared to 'From' heating time!");
			}
		}
	}
	
	private class TempChangeListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent arg0) {
			newTempLabel.setText("Temperature: " + tempSlider.getValue());
		}
	}
}