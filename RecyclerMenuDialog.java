
package ecosaveGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class RecyclerMenuDialog extends JDialog {

	private JPanel contentPane;


	private User myUser ;
	private ArrayList<Recycler> recyclers;
	private UpdatePasswordDialog updatePassword ; 
	private UpdateFullnameDialog updateFullname ; 
	private RecyclerViewSubmissionDialog recyclerViewSubmissionDialog;
	private RecyclerShowMaterialsDialog recyclerShowMaterials;
	private String fullName = "Labib with points: 0" ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecyclerMenuDialog	 frame = new RecyclerMenuDialog(new JDialog());
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
	public RecyclerMenuDialog(JDialog parent) {
		super(parent, true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("EcoSave");

		//initialise all dialogs
		updatePassword = new UpdatePasswordDialog(this) ; 
		updateFullname  = new UpdateFullnameDialog(this) ;
		recyclerViewSubmissionDialog = new RecyclerViewSubmissionDialog(this); 	
		recyclerShowMaterials = new RecyclerShowMaterialsDialog(this);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel(fullName);
		panel.add(lblNewLabel);
		JButton btnUpdateFullname = new JButton("Update full name");
		btnUpdateFullname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFullname.setMyUser(myUser);
				updateFullname.setVisible(true);
			}
		});
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

		JButton btnRecycleMaterial = new JButton("Recycle Material");
		btnRecycleMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//show materials to select and set appointment
				Recycler r = (Recycler) myUser;
				recyclerShowMaterials.setMyUser(myUser);
				recyclerShowMaterials.initShowMaterialsDisplay();
				recyclerShowMaterials.setVisible(true);
			}

		});
		panel.add(btnRecycleMaterial);

		JButton btnViewSubmissionsHistory = new JButton("View submissions history");
		btnViewSubmissionsHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Submission> allSubmissions = new ArrayList<Submission>();
				int materialID = 1;
				//find material
				Material m = ecosaveGui.ecosaveGUI.ecoSave.findMaterial(materialID);
				Recycler r = (Recycler) myUser;
				try {
					//if material has submissions
					if(!(m.getSubmissions() == null)) {
						//create array to store them
						ArrayList<Submission> recyclerSubmission = new ArrayList<Submission>();
						//for each submission
						for (Submission s: allSubmissions) {
							//if the recycler is same as logged in recycler
							if (s.getRecycler().equals(r)) {
								//add the submission to the array
								recyclerSubmission.add(s);
							}
						}

						//after all submissions added to the new array, set submissions to new array
						recyclerViewSubmissionDialog.setSubmissions(recyclerSubmission);
						recyclerShowMaterials.setSubmissions(recyclerSubmission);
						recyclerViewSubmissionDialog.initSubmissionsList();
						recyclerViewSubmissionDialog.pack();
						recyclerViewSubmissionDialog.setMyUser(myUser); 
						recyclerViewSubmissionDialog.setVisible(true);

					}   		

				}catch(NullPointerException ne) {
					JOptionPane.showMessageDialog(null, "No submissions to display", "Warning", JOptionPane.WARNING_MESSAGE);
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

