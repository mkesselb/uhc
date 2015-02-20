package view;

import heatingClasses.Building;
import heatingClasses.Floor;
import heatingClasses.HeatingTreeModel;
import heatingClasses.Room;
import heatingClasses.Structure;
import heatingClasses.Window;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
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
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import java.awt.CardLayout;

import javax.swing.JTextArea;

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
	
	private JPanel panel_2;
	private JPanel panel_3;

	private JSlider defaultTempSlider;

	private JButton defaultTempButton;
	private JButton heatingPlanButton;
	private JButton suboptButton;

	private JTabbedPane tabbedPane;
	private JTextArea suboptTextArea;
	private JScrollPane scrollPane_1;
	private JTextArea suboptTextAreaOverview;
	private JButton suboptButtonOverview;
	
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
		
		panel_2 = new JPanel();
		
		scrollPane_1 = new JScrollPane();
		
		suboptButtonOverview = new JButton("Check for suboptimal heating");
		suboptButtonOverview.addActionListener(new SuboptimalCheckListener());
		GroupLayout gl_overviewPanel = new GroupLayout(overviewPanel);
		gl_overviewPanel.setHorizontalGroup(
			gl_overviewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_overviewPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_overviewPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
						.addComponent(overviewInfoLabel, Alignment.LEADING)
						.addComponent(suboptButtonOverview))
					.addContainerGap())
		);
		gl_overviewPanel.setVerticalGroup(
			gl_overviewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_overviewPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(overviewInfoLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(suboptButtonOverview)
					.addContainerGap())
		);
		
		suboptTextAreaOverview = new JTextArea();
		suboptTextAreaOverview.setLineWrap(true);
		suboptTextAreaOverview.setEditable(false);
		suboptTextAreaOverview.append("Information regarding suboptimal conditions are shown in this window.\n");
		scrollPane_1.setViewportView(suboptTextAreaOverview);
		
		panel_2.setLayout(new CardLayout(0,0));
		
		overviewPanel.setLayout(gl_overviewPanel);
		
		heatingPanel = new JPanel();
		tabbedPane.addTab("Heating plan", null, heatingPanel, null);
		
		heatingInfoLabel = new JLabel("Information about the selected structure");
		
		panel_3 = new JPanel();
		
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
		panel_3.setLayout(new CardLayout(0, 0));
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(defaultTempButton)
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addComponent(suboptButton)
							.addGap(18)
							.addComponent(heatingPlanButton))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(defaultTempLabel)
								.addComponent(defaultTempSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(defaultTempLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(defaultTempSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(defaultTempButton)
						.addComponent(heatingPlanButton)
						.addComponent(suboptButton)))
		);
		
		suboptTextArea = new JTextArea();
		suboptTextArea.setLineWrap(true);
		suboptTextArea.setEditable(false);
		suboptTextArea.append("Information regarding suboptimal conditions are shown in this window.\n");
		scrollPane.setViewportView(suboptTextArea);
		panel_4.setLayout(gl_panel_4);
		
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
			
			updateHeatingTable(struct);
			
			Component[] components = panel_2.getComponents();
			for(Component c: components){
				panel_2.remove(c);
			}
			if(struct instanceof Building){
				buildingTable = new JTable(struct.getOverviewTableContent() , struct.getOverviewColumnNames()){
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {                
		                return false;               
					};
				};
				buildingTable.setColumnSelectionAllowed(false);
				buildingTable.setRowSelectionAllowed(false);
				
				panel_2.add(new JScrollPane(buildingTable), "name_2297568392586");
			}
			if(struct instanceof Floor){
				floorTable = new JTable(struct.getOverviewTableContent() , struct.getOverviewColumnNames()){
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {                
		                return false;               
					};
				};
				floorTable.setColumnSelectionAllowed(false);
				floorTable.setRowSelectionAllowed(false);
				panel_2.add(new JScrollPane(floorTable), "name_2297796567242");
			}
			if(struct instanceof Room){
				overviewInfoLabel.setText("<html>" + overviewInfoLabel.getText()+"<br> Temperature: "+((Room) struct).getCurrentTemperature() + "</html>");
				heatingInfoLabel.setText("<html>" + heatingInfoLabel.getText()+"<br> Temperature: "+((Room) struct).getCurrentTemperature() + "</html>");
				updateRoomTable((Room) struct);
			}
		}
	}
	
	private class WindowSelectionListener implements ListSelectionListener {
	    public void valueChanged(ListSelectionEvent e) {
	        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

	        if (!lsm.isSelectionEmpty()) {
	            // Find out which indexes are selected.
	            int minIndex = lsm.getMinSelectionIndex();
	            int maxIndex = lsm.getMaxSelectionIndex();
	            for (int i = minIndex; i <= maxIndex; i++) {
	                if (lsm.isSelectedIndex(i)) {
	                	//toggle window on index i
	                	Structure struct = (Structure) tree.getLastSelectedPathComponent();
	                	Room r = (Room) struct;
	                	Window w = (Window) r.getSubStruct(i);
	                	w.toggleOpen();
	                	updateRoomTable(r);
	                }
	            }
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
				
				List<String> conflicts = struct.getDefaultHeatingConflicts(temp);
				
				if(conflicts.size() > 0){
					HeatControlConflict hcc = new HeatControlConflict(conflicts);
					hcc.setTitle("Heating Conflicts");
					hcc.setBounds(120, 140, 600, 480);
					hcc.setModal(true);
					hcc.setVisible(true);
					List<String> rConflicts = hcc.getResult();
					
					if(rConflicts != null){
						struct.applyDefaultHeatingModel(temp, rConflicts);
					}
				} else{
					struct.applyDefaultHeatingModel(temp, conflicts);
				}
				
				updateHeatingTable(struct);
			}
		}
	}
	
	private void updateRoomTable(Room room){
		Component[] components = panel_2.getComponents();
		for(Component c: components){
			panel_2.remove(c);
		}
		
		roomTable = new JTable(room.getOverviewTableContent(), room.getOverviewColumnNames()){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		roomTable.setColumnSelectionAllowed(false);
		roomTable.setRowSelectionAllowed(false);

		ListSelectionModel listSelectionModel = roomTable.getSelectionModel();
        listSelectionModel.addListSelectionListener(new WindowSelectionListener());
        roomTable.setSelectionModel(listSelectionModel);
		
		panel_2.add(new JScrollPane(roomTable), "name_2297869342430");
		panel_2.updateUI();
	}
	
	private void updateHeatingTable(Structure struct){
		Component[] components = panel_3.getComponents();
		for(Component c: components){
			panel_3.remove(c);
		}
		
		heatingTable = new JTable(struct.getHeatingPlan(), Structure.heatingPlanColumns){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		heatingTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		
		for(int i = 1; i < struct.getHeatingPlan().length; i++){
			heatingTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		heatingTable.setColumnSelectionAllowed(false);
		heatingTable.setRowSelectionAllowed(false);
		panel_3.add(new JScrollPane(heatingTable), "name_2297869453487");
		panel_3.updateUI();
	}
	
	private class SuboptimalCheckListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//currently selected struct
			Structure struct = (Structure) tree.getLastSelectedPathComponent();
			if(struct != null){
				List<String> subopt = struct.getSuboptimalConditions();
				String in = struct.getClass().getSimpleName() + "-" + struct.getName() 
						+ " suboptimal conditions:\n";
				if(subopt.size() > 0){
					for(String s : subopt){
						in += "\t" + s + "\n";
					}
				} else{
					in += "\tNo suboptimal conditions detected!\n";
				}
				
				suboptTextArea.append(in);
				suboptTextAreaOverview.append(in);
			}
		}
	}
	
	private class ProgramHeatingPlanListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//currently selected struct
			Structure struct = (Structure) tree.getLastSelectedPathComponent();
			if(struct != null){
				HeatControlPopup hcp = new HeatControlPopup();
				hcp.setTitle("Program Heating");
				hcp.setBounds(120, 140, 500, 260);
				hcp.setModal(true);
				hcp.setVisible(true);
				
				List<Integer> programHeating = hcp.getResults();
				if(programHeating  != null){
					List<String> conflicts = struct.getHeatingConflicts(programHeating.get(0), programHeating.get(1), programHeating.get(2), programHeating.get(3));
				
					if(conflicts.size() > 0){
						HeatControlConflict hcc = new HeatControlConflict(conflicts);
						hcc.setTitle("Heating Conflicts");
						hcc.setBounds(120, 140, 600, 480);
						hcc.setModal(true);
						hcc.setVisible(true);
						List<String> rConflicts = hcc.getResult();
						
						if(rConflicts != null){
							struct.applyHeatingModel(programHeating.get(0), programHeating.get(1), programHeating.get(2), programHeating.get(3), rConflicts);
						}
					} else{
						struct.applyHeatingModel(programHeating.get(0), programHeating.get(1), programHeating.get(2), programHeating.get(3), conflicts);
					}	
				}
				
				updateHeatingTable(struct);
			}
		}
	}
	
	private class DefaultTempChangeListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent arg0) {
			defaultTempLabel.setText("Default temperature: " + defaultTempSlider.getValue());
		}
	}
}