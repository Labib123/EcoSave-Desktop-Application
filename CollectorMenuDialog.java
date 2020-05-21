
package ecosaveGui;

import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class CollectorMenuDialog extends JDialog {

	private JPanel contentPane;
	private User myUser;
	private ArrayList<Recycler> recyclers;
	private UpdatePasswordDialog updatePassword ; 
	private UpdateFullnameDialog updateFullname ;
	private CollectorShowMaterialDialog collectorShowMaterialDialog ;
	private CollectorViewSubmissionDialog collectorViewSubmissionDialog ; 
	private String fullName = "Ahmad with points: 0" ; 


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {


					CollectorMenuDialog frame = new CollectorMenuDialog(new JDialog());
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
	public CollectorMenuDialog(JDialog parent) {
		super(parent, true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("EcoSave");

		//initialize dialogs
		updatePassword = new UpdatePasswordDialog(this) ; 
		updateFullname = new UpdateFullnameDialog(this) ; 
		collectorShowMaterialDialog = new CollectorShowMaterialDialog(this);
		collectorViewSubmissionDialog = new CollectorViewSubmissionDialog(this); 
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnUpdateFullname = new JButton("Update full name");
		btnUpdateFullname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFullname.setMyUser(myUser);
				updateFullname.setVisible(true);
			}
		});

		JLabel lblNewLabel = new JLabel(fullName);
		panel.add(lblNewLabel);
		panel.add(btnUpdateFullname);

		JButton btnUpdatePassword = new JButton("Update password");
		btnUpdatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePassword.setMyUser(myUser);
				updatePassword.setVisible(true);
			}
		});
		panel.add(btnUpdatePassword);

		JButton btnBack = new JButton("Back to the main");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(btnBack);


		JButton btnAddMaterial = new JButton("Add Material");
		btnAddMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				collectorShowMaterialDialog.setMyUser(myUser);
				collectorShowMaterialDialog.initShowMaterialsDisplay();
				collectorShowMaterialDialog.pack();
				collectorShowMaterialDialog.setVisible(true);

			}
		});
		panel.add(btnAddMaterial);


		JButton btnCollectMaterial = new JButton("Collect Material");
		btnCollectMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panel.add(btnCollectMaterial);

		JButton btnViewSubmissionsHistory = new JButton("View submissions history");
		btnViewSubmissionsHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Submission> allSubmissions = new ArrayList<Submission>();
				int materialID = 1;
				//find material
				Material m = ecosaveGui.ecosaveGUI.ecoSave.findMaterial(materialID);
				Collector r = (Collector) myUser;
				try {
					//if material has submissions
					if(!(m.getSubmissions() == null)) {
						//create array to store them
						ArrayList<Submission> collectorSubmission = new ArrayList<Submission>();
						//for each submission
						for (Submission s: allSubmissions) {
							//if the colector is same as logged in collector
							if (s.getRecycler().equals(r)) {
								//add the submission to the array
								collectorSubmission.add(s);
							}
						}

						//after all submissions added to the new array, set submissions to new array
						collectorViewSubmissionDialog.setSubmissions(collectorSubmission);
						collectorViewSubmissionDialog.setSubmissions(collectorSubmission);
						collectorViewSubmissionDialog.initSubmissionsList();
						collectorViewSubmissionDialog.pack();
						collectorViewSubmissionDialog.setMyUser(myUser); 
						collectorViewSubmissionDialog.setVisible(true);

					}   		

				}catch(NullPointerException ne) {
					JOptionPane.showMessageDialog(null, "No submissions to display");
				}


			}
		});

		panel.add(btnViewSubmissionsHistory);
		panel.add(btnBack);


	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;

	}

	public void setFullName(String name) {
		this.fullName = name;

	}


}


