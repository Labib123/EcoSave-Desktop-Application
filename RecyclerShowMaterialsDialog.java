package ecosaveGui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;
import javax.swing.JTextField;
public class RecyclerShowMaterialsDialog extends JDialog {


	private ArrayList<Submission> submissions;
	private ArrayList<Material> materials;
	private Material material;
	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JTable table;
	private MaterialsArrayTableModel matModel;
	private User myUser ; 
	private Recycler recycler;
	private UserArrayTableModel utModel;
	private ArrayList<User> users;//into table
	private JTextArea textArea;
	private JTextField collectorNameTF;
	private JDateChooser dateChooser;
	private SubmissionsListTableModel satModel;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecyclerShowMaterialsDialog dialog = new RecyclerShowMaterialsDialog(new JDialog());
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
	public RecyclerShowMaterialsDialog(JDialog parent) {
		super(parent, true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 695);
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
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 212, 480, 33);
		contentPane.add(panel_1);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnBack);

		JButton btnRecycleSelectedMaterial = new JButton("Recycle Selected Material");
		btnRecycleSelectedMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{

					//select row
					int selectedRow = table.getSelectedRow();
					//get column
					int column = table.getSelectedColumn();
					//get object at selected row
					material = ecosaveGui.ecosaveGUI.ecoSave.findMaterial(column);
					JOptionPane.showMessageDialog(null, "material found.");
					//get collector
					//					for(Material m: materials) {
					if(!(material.numOfCollectors() == 0)) {
						//if material has collectors, display those collectors
						String collectorNames = material.getCollectorsInfo();
						textArea.setText(collectorNames);
					}else {
						JOptionPane.showMessageDialog(null, "Material has no registered collectors for now.");

					}
				}catch(NullPointerException ne) {
					JOptionPane.showMessageDialog(null, "please select a row", "Error", JOptionPane.ERROR_MESSAGE);
				}

				initShowMaterialsDisplay();


			}
		});

		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnRecycleSelectedMaterial);

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
		scrollPane.setBounds(5, 25, 470, 176);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Show Materials");
		lblNewLabel.setBounds(103, 5, 326, 21);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(10, 287, 316, 139);
		contentPane.add(textArea);

		JLabel lblNewLabel_1 = new JLabel("List of Collectors:-");
		lblNewLabel_1.setBounds(15, 256, 108, 20);
		contentPane.add(lblNewLabel_1);

		collectorNameTF = new JTextField();
		collectorNameTF.setBounds(41, 460, 136, 21);
		contentPane.add(collectorNameTF);
		collectorNameTF.setColumns(10);

		JLabel lblCollectorNameSearch = new JLabel("Enter Collector Name:");
		lblCollectorNameSearch.setBounds(41, 437, 108, 21);
		contentPane.add(lblCollectorNameSearch);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(242, 460, 157, 34);
		getContentPane().add(dateChooser);

		JButton btnProposeAppointment = new JButton("Submit Appointment");
		btnProposeAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					//find collector based on name entered in text field
					Collector c = (Collector) ecosaveGui.ecosaveGUI.ecoSave.findUser(collectorNameTF.getText());
					Recycler r = (Recycler) ecosaveGui.ecosaveGUI.ecoSave.findUser(getMyUser().getFullName());

					//get date
					Date date = dateChooser.getDate();

					/**
					 * Converting Date to LocalDate
					 */
					//Getting the default zone id
					ZoneId defaultZoneId = ZoneId.systemDefault();
					//Converting the date to Instant
					Instant instant = date.toInstant();
					LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();

					//add submission to collector
					c.addSubmission(new Submission(material, c, (Recycler) getMyUser(), localDate));
					//add submission to collector
					r.addSubmission(new Submission(material, c, (Recycler) getMyUser(), localDate));
					JOptionPane.showMessageDialog(null, "Appointment Created Successfully");


					setVisible(false);


				}catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Please fill out the necessary details!", "Error", JOptionPane.ERROR_MESSAGE);

				} 


			}
		});
		btnProposeAppointment.setBounds(326, 590, 179, 34);
		getContentPane().add(btnProposeAppointment);

		JLabel lblProposeDate = new JLabel("Proposed Date:");
		lblProposeDate.setBounds(242, 440, 123, 14);
		contentPane.add(lblProposeDate);

		JButton btnBack_1 = new JButton("Back");
		btnBack_1.setBounds(250, 591, 66, 32);
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnBack_1);

		materials = new ArrayList<Material>();
		submissions = new ArrayList<Submission>();

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
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}

	public User getMyUser() {
		return myUser;
	}
	public void initUserArray() {
		utModel = new UserArrayTableModel(getUsers());
		table = new JTable(utModel);
		scrollPane.setViewportView(table);
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public void initSubmissionsList() {
		satModel = new SubmissionsListTableModel(getSubmissions());
		table = new JTable(satModel);
		scrollPane.setViewportView(table);
	}
	
	public ArrayList<Submission> getSubmissions() {
		return submissions;
	}
	
	public void setSubmissions(ArrayList<Submission> submissions) {
		this.submissions = submissions;
	}
}
