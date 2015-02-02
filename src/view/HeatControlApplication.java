package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
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
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class HeatControlApplication extends JFrame{
	public HeatControlApplication() {
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel overviewPanel = new JPanel();
		tabbedPane.addTab("Overview", null, overviewPanel, null);
		
		JLabel informationLabel = new JLabel("New label");
		
		JPanel panel = new JPanel();
		GroupLayout gl_overviewPanel = new GroupLayout(overviewPanel);
		gl_overviewPanel.setHorizontalGroup(
			gl_overviewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_overviewPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_overviewPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(informationLabel)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(516, Short.MAX_VALUE))
		);
		gl_overviewPanel.setVerticalGroup(
			gl_overviewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_overviewPanel.createSequentialGroup()
					.addGap(31)
					.addComponent(informationLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(361, Short.MAX_VALUE))
		);
		
		floorTable = new JTable();
		panel.add(floorTable);
		
		buildingTable = new JTable();
		panel.add(buildingTable);
		
		roomTable = new JTable();
		panel.add(roomTable);
		overviewPanel.setLayout(gl_overviewPanel);
		
		JPanel heatPanel = new JPanel();
		tabbedPane.addTab("Heating Control", null, heatPanel, null);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_heatPanel = new GroupLayout(heatPanel);
		gl_heatPanel.setHorizontalGroup(
			gl_heatPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_heatPanel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_heatPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		gl_heatPanel.setVerticalGroup(
			gl_heatPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_heatPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(311, Short.MAX_VALUE))
		);
		
		JLabel heatInfoLabel = new JLabel("New label");
		panel_2.add(heatInfoLabel);
		
		heatTable = new JTable();
		panel_2.add(heatTable);
		
		JButton suboptButton = new JButton("New button");
		panel_1.add(suboptButton);
		
		JButton defaultTempButton = new JButton("New button");
		panel_1.add(defaultTempButton);
		
		JButton heatControlButton = new JButton("New button");
		panel_1.add(heatControlButton);
		
		JLabel defaultTempLabel = new JLabel("New label");
		panel_1.add(defaultTempLabel);
		
		JSlider defaultTempSlider = new JSlider();
		panel_1.add(defaultTempSlider);
		heatPanel.setLayout(gl_heatPanel);
		
		JTree tree = new JTree();
		splitPane.setLeftComponent(tree);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable floorTable;
	private JTable buildingTable;
	private JTable roomTable;
	private JTable heatTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeatControlApplication frame = new HeatControlApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}