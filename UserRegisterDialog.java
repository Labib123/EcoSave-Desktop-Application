

package ecosaveGui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class UserRegisterDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usernameTF;
	private JTextField passwordTF;
	private JTextField fullnameTF;
	private JTextField addressTF;
	private final ButtonGroup userTypeGroup = new ButtonGroup();
	private JRadioButton rdbtnRecycler;
	private JRadioButton rdbtnCollector;
	private boolean status;
	static EcoSave ecoSave;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			UserRegisterDialog dialog = new UserRegisterDialog(new JFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UserRegisterDialog(JFrame parent) {
		super(parent, true);
		setModal(true);
		
		//when we want to use same name
		setBounds(100, 100, 450, 300);
		setTitle("Recycling App");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0)); 
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				rdbtnRecycler = new JRadioButton("Recycler");
				rdbtnRecycler.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						blockTextField();
					}
				});
				
				userTypeGroup.add(rdbtnRecycler);
				panel.add(rdbtnRecycler);
			}
			{
				rdbtnCollector = new JRadioButton("Collector");
				rdbtnCollector.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						blockTextField();
					}
				});
				userTypeGroup.add(rdbtnCollector);
				panel.add(rdbtnCollector);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblName = new JLabel("Username");
				panel.add(lblName);
			}
			{
				usernameTF = new JTextField();
				panel.add(usernameTF);
				usernameTF.setColumns(10);
			}
			{
				JLabel lblRegistrationNumber = new JLabel("Password");
				panel.add(lblRegistrationNumber);
			}
			{
				passwordTF = new JTextField();
				panel.add(passwordTF);
				passwordTF.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("Full Name");
				panel.add(lblNewLabel);
			}
			{
				fullnameTF = new JTextField();
				panel.add(fullnameTF);
				fullnameTF.setColumns(10);
			}
			{
				JLabel lblLoadInKg = new JLabel("Address");
				panel.add(lblLoadInKg);
			}
			{
				addressTF = new JTextField();
				panel.add(addressTF);
				addressTF.setColumns(10);
			}
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Register");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String username = usernameTF.getText();
						String password = passwordTF.getText();
						String fullname = fullnameTF.getText();
						String address = addressTF.getText();
						
						try {
						if(rdbtnRecycler.isSelected()) {
							ecosaveGui.ecosaveGUI.ecoSave.addUser(new Recycler(username, password, fullname));
							
						}
						else if (rdbtnCollector.isSelected()) {
							ecosaveGui.ecosaveGUI.ecoSave.addUser(new Collector(username, password, fullname, 
					            	address));
						}
						} catch (Exception x) {
							x.printStackTrace();
						}
						status = true;
						JOptionPane.showMessageDialog(null, "User Added Successfully");
						setVisible(false);
						usernameTF.setText("");
						passwordTF.setText("");
						fullnameTF.setText("");
						addressTF.setText("");
					}
				});
				okButton.setActionCommand("Register 2");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						usernameTF.setText("");
						passwordTF.setText("");
						fullnameTF.setText("");
						addressTF.setText("");
						status = false;
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		{
			JLabel lblRegisterAUser = new JLabel("Register a User");
			lblRegisterAUser.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblRegisterAUser.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblRegisterAUser, BorderLayout.NORTH);
		}
	}
	
	//method to disable address field according to user type
	private void blockTextField() {
		if (rdbtnRecycler.isSelected()){
			addressTF.setText("");
			addressTF.setEnabled(false);
		}
		else {
			addressTF.setText("");
			addressTF.setEnabled(true);
		}
		
	}
}