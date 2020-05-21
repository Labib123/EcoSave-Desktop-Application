
package ecosaveGui;

import java.time.LocalDate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
public class ecosaveGUI extends JFrame {

	static EcoSave ecoSave;
	//Main Box of menu declared
	private JPanel contentPane;
	//declare sub menus

	private UserRegisterDialog registerDialog;
	private UsersLoginDialog loginDialog;
	JTextField myOutput = new JTextField(16);
	private MaterialsArray allMaterials;  
	private SubmissionsList allSubmissions;
	private UserArray allUsers;
	private ArrayList<Submission> submissions;

	/** Launch the application. 
	 * Creating new element
	 * Make it visible
	 * Load sample data
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ecoSave = new EcoSave();
					ecosaveGUI frame = new ecosaveGUI();
					frame.setVisible(true);
					sampleData();
					ecosaveGui.ecosaveGUI.ecoSave.addUser(new Admin("admin", "admin", "Administrator"));
					ecoSave.addUser(new Collector("ahmad", "ahmad", "ahmad", "1, Jalan 2"));
					ecoSave.addUser(new Recycler("labib", "labib", "labib"));
					User collector = ecoSave.findUser("ahmad");
					User labib = ecoSave.findUser("labib");

					Recycler recycler = (Recycler) labib;
					ArrayList<Submission> submissions = 
							((Collector) collector).findSubmissionsFor(recycler);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Constructor of Main menu - Create the frame.
	public ecosaveGUI() {
		setForeground(Color.BLACK);
		ecoSave = new EcoSave();

		//window settings for this menu
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("EcoSave");

		//initialise submenus and link to this menu
		registerDialog = new UserRegisterDialog(this); 
		loginDialog = new UsersLoginDialog(this);
		allMaterials = new MaterialsArray(this);
		allSubmissions = new SubmissionsList(this);
		allUsers = new UserArray(this);

		submissions = new ArrayList<Submission>();


		JPanel panelgrid = new JPanel();
		contentPane.add(panelgrid, BorderLayout.CENTER);
		panelgrid.setLayout(new GridLayout(0, 1, 0, 0));

		//Menu Items 
		JButton btnRegister = new JButton("Register User");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerDialog.pack();
				registerDialog.setVisible(true);
			}
		});
		panelgrid.add(btnRegister);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				loginDialog.pack();
				loginDialog.setVisible(true);
			}
		});
		panelgrid.add(btnLogin);

		JButton btnViewMaterials= new JButton("List Materials");
		btnViewMaterials.setForeground(Color.DARK_GRAY);
		btnViewMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allMaterials.setMaterials(ecoSave.getMaterials());
				allMaterials.initMaterialsArray();
				allMaterials.pack();
				allMaterials.setVisible(true);

			}
		});
		panelgrid.add(btnViewMaterials);


		JButton btnViewSubmissions = new JButton("List Submissions");
		btnViewSubmissions.setForeground(Color.DARK_GRAY);
		btnViewSubmissions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Material m: ecoSave.getMaterials()) {

					submissions.addAll(ecoSave.findMaterial(m.getMaterialID()).getSubmissions());

				}
				allSubmissions.setSubmissions(submissions);
				allSubmissions.initSubmissionsList();
				allSubmissions.pack();
				allSubmissions.setVisible(true);
			}
		});
		panelgrid.add(btnViewSubmissions);

		JButton btnViewUsers = new JButton("View users");
		btnViewUsers.setForeground(Color.DARK_GRAY);
		btnViewUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allUsers.setUsers(ecoSave.getUsers());
				allUsers.initUserArray();
				allUsers.pack();
				allUsers.setVisible(true);
			}
		});
		panelgrid.add(btnViewUsers);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panelgrid.add(btnQuit);

		JLabel lblEcosave = new JLabel("Main Menu");
		lblEcosave.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEcosave.setForeground(Color.BLACK);
		lblEcosave.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEcosave, BorderLayout.NORTH);
	}



	public static void sampleData() {
		ecoSave.addUser(new Admin("admin", "admin", "Administrator"));
		ecoSave.addMaterial(new Material("glass", "glass", 100));
		ecoSave.addMaterial(new Material("paper", "newspaper", 50));
		ecoSave.addMaterial(new Material("aluminium", "aluminium", 200));
		ecoSave.addUser(new Collector("james", "james", "James", "1, Jalan 2"));
		ecoSave.addUser(new Recycler("ace", "ace", "Ace"));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("james"), ecoSave.findMaterial(1));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("james"), ecoSave.findMaterial(3));
		ecoSave.addUser(new Collector("spiderman", "spiderman", "Spiderman", "2, Jalan 3"));
		ecoSave.addUser(new Recycler("bbc", "bbc", "Bbc"));
		ecoSave.addUser(new Collector("superman", "superman", "Superman", "3, Jalan 4"));
		ecoSave.addUser(new Recycler("abc", "abc", "Abc"));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("spiderman"), ecoSave.findMaterial(1));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("spiderman"), ecoSave.findMaterial(2));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("superman"), ecoSave.findMaterial(2));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("superman"), ecoSave.findMaterial(3));

	}
}
