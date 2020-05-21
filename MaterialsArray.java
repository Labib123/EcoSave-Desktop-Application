package ecosaveGui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


import java.awt.Font;
import java.awt.Rectangle;

public class MaterialsArray extends JDialog implements Serializable{

	private ArrayList<Material> materials;

	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JTable table;
	private MaterialsArrayTableModel matModel;
	private Material m;
	private MaterialsArrayTableModel maTM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MaterialsArray dialog = new MaterialsArray(new JFrame());
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);					
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
	public MaterialsArray(final JFrame parent) {
		super(parent, true);
		setBounds(new Rectangle(0, 23, 0, 0));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 662, 600);
		setTitle("EcoSave");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileHandling.readFromFile(parent);

			}
		});
		mnFile.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileHandling.saveToFile(parent,table);

			}
		});
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
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnBack);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JLabel lblViewMaterialsArray = new JLabel("View Materials Array");
		lblViewMaterialsArray.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblViewMaterialsArray.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblViewMaterialsArray, BorderLayout.NORTH);

		materials = new ArrayList<Material>();//initialise array list

	}

	public void initMaterialsArray() {
		matModel = new MaterialsArrayTableModel(getMaterials());
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
