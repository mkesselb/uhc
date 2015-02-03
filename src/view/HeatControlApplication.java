package view;

import heatingClasses.Building;
import heatingClasses.Floor;
import heatingClasses.HeatingTreeModel;
import heatingClasses.Room;
import heatingClasses.Structure;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

public class HeatControlApplication extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTree tree;
	
	private JLabel overviewInfoLabel;
	private JLabel heatingInfoLabel;
	private JLabel defaultTempLabel;

	private JTable buildingTable;
	private JTable floorTable;
	private JTable roomTable;
	private JTable heatingTable;

	private JPanel heatingPanel;
	private JPanel overviewPanel;

	private JSlider defaultTempSlider;

	private JButton defaultTempButton;
	private JButton heatingPlanButton;
	private JButton suboptButton;

	private JTabbedPane tabbedPane;
	
	public HeatControlApplication() {
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		overviewPanel = new JPanel();
		tabbedPane.addTab("Overview", null, overviewPanel, null);
		
		overviewInfoLabel = new JLabel("Information about the selected structure");
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_overviewPanel = new GroupLayout(overviewPanel);
		gl_overviewPanel.setHorizontalGroup(
			gl_overviewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_overviewPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_overviewPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
						.addComponent(overviewInfoLabel))
					.addContainerGap())
		);
		gl_overviewPanel.setVerticalGroup(
			gl_overviewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_overviewPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(overviewInfoLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		buildingTable = new JTable();
		panel_2.add(buildingTable);
		
		floorTable = new JTable();
		panel_2.add(floorTable);
		
		roomTable = new JTable();
		panel_2.add(roomTable);
		overviewPanel.setLayout(gl_overviewPanel);
		
		heatingPanel = new JPanel();
		tabbedPane.addTab("Heating plan", null, heatingPanel, null);
		
		heatingInfoLabel = new JLabel("Information about the selected structure");
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		GroupLayout gl_heatingPanel = new GroupLayout(heatingPanel);
		gl_heatingPanel.setHorizontalGroup(
			gl_heatingPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_heatingPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_heatingPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
						.addComponent(heatingInfoLabel, Alignment.LEADING))
					.addContainerGap())
		);
		gl_heatingPanel.setVerticalGroup(
			gl_heatingPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_heatingPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(heatingInfoLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		int minTemp = 0;
		int maxTemp = 40;
		int initTemp = 22;
		defaultTempSlider = new JSlider(JSlider.HORIZONTAL, minTemp, maxTemp, initTemp);
		defaultTempSlider.addChangeListener(new DefaultTempChangeListener());
		defaultTempSlider.setMajorTickSpacing(10);
		defaultTempSlider.setMinorTickSpacing(2);
		defaultTempSlider.setPaintTicks(true);
		defaultTempSlider.setPaintLabels(true);
		
		defaultTempLabel = new JLabel("Default temperature: " + initTemp);
		
		defaultTempButton = new JButton("Apply default heating");
		defaultTempButton.addActionListener(new DefaultHeatListener());
		
		heatingPlanButton = new JButton("Program heating plan");
		heatingPlanButton.addActionListener(new ProgramHeatingPlanListener());
		
		suboptButton = new JButton("Check for suboptimal heating");
		suboptButton.addActionListener(new SuboptimalCheckListener());
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(defaultTempLabel)
						.addComponent(defaultTempSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(defaultTempButton)
							.addPreferredGap(ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
							.addComponent(suboptButton)
							.addGap(18)
							.addComponent(heatingPlanButton)))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(defaultTempLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(defaultTempSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(defaultTempButton)
						.addComponent(heatingPlanButton)
						.addComponent(suboptButton)))
		);
		panel_4.setLayout(gl_panel_4);
		
		heatingTable = new JTable();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(heatingTable, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(446, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(heatingTable, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(254, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		heatingPanel.setLayout(gl_heatingPanel);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		
		tree = new JTree(new HeatingTreeModel("building.csv"));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new HeatTreeSelectionListener());
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tree, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tree, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeatControlApplication frame = new HeatControlApplication();
					frame.setTitle("Heating control");
					frame.setVisible(true);
					frame.setBounds(100, 100, 1000, 600);
					frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private class HeatTreeSelectionListener implements TreeSelectionListener{
		@Override
		public void valueChanged(TreeSelectionEvent arg0) {
			Structure struct = (Structure) tree.getLastSelectedPathComponent();
			overviewInfoLabel.setText("Selected structure: " 
					+ struct.getClass().getSimpleName() + "-" + struct.toString());
			heatingInfoLabel.setText("Selected structure: " 
					+ struct.getClass().getSimpleName() + "-" + struct.toString());
			
			tabbedPane.setSelectedComponent(overviewPanel);
			
			//TODO: fill and show the appropriate tables
			// for overview and heating tab
			if(struct instanceof Building){
				floorTable.setEnabled(false);
				roomTable.setEnabled(false);
				buildingTable.setEnabled(true);
				
			}
			if(struct instanceof Floor){
				roomTable.setEnabled(false);
				buildingTable.setEnabled(false);
				floorTable.setEnabled(true);
				
			}
			if(struct instanceof Room){
				floorTable.setEnabled(false);
				buildingTable.setEnabled(false);
				roomTable.setEnabled(true);
				
			}
		}
	}
	
	private class DefaultHeatListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//currently selected struct
			Structure struct = (Structure) tree.getLastSelectedPathComponent();
			if(struct != null){
				int temp = defaultTempSlider.getValue();
				
				List<String> conflicts = struct.getDefaultHeatingConflicts();
				if(conflicts.size() > 0){
					//TODO: show conflict popup (maybe should be a JDialog)
					//something like ...set visible of dialog
					//then after that dialog.getConflicts -> (needs this method to retrieve the purged conflicts)
				} else{
					struct.applyDefaultHeatingModel(temp, new ArrayList<String>());
					//TODO: update heat table from structure models
				}
			}
		}
	}
	
	private class SuboptimalCheckListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: check for suboptimal heating
		}
	}
	
	private class ProgramHeatingPlanListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: program heating popup
		}
	}
	
	private class DefaultTempChangeListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent arg0) {
			defaultTempLabel.setText("Default temperature: " + defaultTempSlider.getValue());
		}
	}
}