
package ecosaveGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AdminMenuDialog extends JDialog {

	private JPanel contentPane;


	private User myUser;
	private ArrayList<Recycler> recyclers;
	private AdminViewSubmissionsDialog viewSubmissions; 
	private AdminAddMaterialDialog addMaterial;
	private AdminShowAddMaterialsDialog showAddMaterialsDialog;
	private AdminShowDeleteMaterialDialog showDeleteMaterialsDialog;
	private AdminShowUpdateMaterialDialog showUpdateMaterialsDialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenuDialog	frame = new AdminMenuDialog(new JDialog());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminMenuDialog(JDialog parent) {
		super(parent, true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("EcoSave");

		viewSubmissions = new AdminViewSubmissionsDialog(this);
		addMaterial = new AdminAddMaterialDialog(this);
		showAddMaterialsDialog = new AdminShowAddMaterialsDialog(this);
		showDeleteMaterialsDialog = new AdminShowDeleteMaterialDialog(this);
		showUpdateMaterialsDialog = new AdminShowUpdateMaterialDialog(this);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));



		JButton btnAddMaterial = new JButton("Add Material");
		btnAddMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddMaterialsDialog.initShowMaterialsDisplay();
				showAddMaterialsDialog.pack();
				showAddMaterialsDialog.setVisible(true);
			}
		});
		panel.add(btnAddMaterial);

		JButton btnDeleteMaterial = new JButton("Delete Material");
		btnDeleteMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDeleteMaterialsDialog.initShowMaterialsDisplay();
				showDeleteMaterialsDialog.pack();
				showDeleteMaterialsDialog.setVisible(true);
			}
		});
		panel.add(btnDeleteMaterial);

		JButton btnUpdateMaterial = new JButton("Update Material");
		btnUpdateMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateMaterialsDialog.initShowMaterialsDisplay();
				showUpdateMaterialsDialog.pack();
				showUpdateMaterialsDialog.setVisible(true);
			}
		});
		panel.add(btnUpdateMaterial);

		JButton btnViewSubmissionsHistory = new JButton("View submissions history");
		btnViewSubmissionsHistory.setForeground(Color.DARK_GRAY);
		btnViewSubmissionsHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewSubmissions.setSubmissions(ecosaveGui.ecosaveGUI.ecoSave.getSubmissions());
				viewSubmissions.initSubmissionsList();
				viewSubmissions.pack();
				viewSubmissions.setVisible(true);
			}
		});

		panel.add(btnViewSubmissionsHistory);

		JButton btnBack = new JButton("Back to the main");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(btnBack);


	}
	public User getMyUser() {
		return myUser;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
}


