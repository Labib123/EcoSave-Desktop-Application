
package ecosaveGui;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class AdminShowDeleteMaterialDialog extends JDialog {


	private ArrayList<Material> materials;
	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JTable table;
	private MaterialsArrayTableModel matModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminShowAddMaterialsDialog dialog = new AdminShowAddMaterialsDialog(new JDialog());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);					
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminShowDeleteMaterialDialog(JDialog parent) {
		super(parent, true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("EcoSave");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnBack);

		JButton btnSelectedDeleteMaterial = new JButton("Delete Selected Material");
		btnSelectedDeleteMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				try{
					int selectedRow = table.getSelectedRow();
					matModel.removeRow(selectedRow);
					JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
					matModel.fireTableDataChanged();
					initShowMaterialsDisplay();
				}catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Could not delete row");

				}

			}
		});

		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnSelectedDeleteMaterial);

		JButton btnRefreshBtn = new JButton("Refresh");
		btnRefreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matModel.fireTableDataChanged();
				initShowMaterialsDisplay();	
			}
		});

		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnRefreshBtn);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Show and Delete Materials");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		materials = new ArrayList<Material>();

	}

	public void initShowMaterialsDisplay() {
		matModel = new MaterialsArrayTableModel(ecosaveGui.ecosaveGUI.ecoSave.getMaterials());
		table = new JTable(matModel);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(350);
		table.getColumnModel().getColumn(1).setPreferredWidth(1000);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		table.getColumnModel().getColumn(3).setPreferredWidth(350);

	}

	public ArrayList<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Material> materials) {
		this.materials = materials;
	}

}
