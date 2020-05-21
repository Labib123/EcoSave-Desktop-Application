
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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class AdminSearchMaterialByNameDialog extends JDialog {

	private JPanel contentPane;


	private User myUser;
	private ArrayList<Recycler> recyclers;
	private JTextField searchTF;
	private AdminUpdateMaterialDialog adminUpdateMaterialDialog;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSearchMaterialByNameDialog frame = new AdminSearchMaterialByNameDialog(new JDialog());
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
	public void alert(String msg) {
		JOptionPane.showMessageDialog(rootPane, msg);
	}
	public AdminSearchMaterialByNameDialog(JDialog parent) {
		super(parent, true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("EcoSave");
		adminUpdateMaterialDialog = new AdminUpdateMaterialDialog(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		searchTF = new JTextField();
		searchTF.setBounds(210, 92, 86, 20);
		contentPane.add(searchTF);
		searchTF.setColumns(20);

		JLabel lblSearch = new JLabel("Material ID");
		lblSearch.setBounds(90, 95, 97, 14);
		contentPane.add(lblSearch);

		JButton searchBtn = new JButton("Search");
		searchBtn.setBounds(138, 148, 158, 23);
		contentPane.add(searchBtn);

		JLabel SearchTitle = new JLabel();
		SearchTitle.setHorizontalAlignment(SwingConstants.CENTER);
		SearchTitle.setText("Search Material By ID");
		SearchTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SearchTitle.setBounds(113, 22, 233, 31);
		contentPane.add(SearchTitle);
		setTitle("");
		searchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean valid = true;
				String materialID = searchTF.getText();
				if (valid) {
					if(!materialID.equals("")) {
						Material foundMaterial = ecosaveGui.ecosaveGUI.ecoSave.findMaterial(Integer.parseInt(materialID));
						if(foundMaterial == null)
							JOptionPane.showMessageDialog(null, "Successfully Updated");
						alert("Material Found");

						setVisible(false);
						adminUpdateMaterialDialog.setMaterial(foundMaterial);
						adminUpdateMaterialDialog.pack();
						adminUpdateMaterialDialog.setVisible(true);
					}				
					else {
						JOptionPane.showMessageDialog(null, "Material ID not found", "Alert", JOptionPane.WARNING_MESSAGE);
					}

				}
			}

		});

		JPanel panel = new JPanel( new SpringLayout() );
	}
	public User getMyUser() {
		return myUser;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
}


