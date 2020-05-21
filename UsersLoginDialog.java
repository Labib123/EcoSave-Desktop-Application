
package ecosaveGui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;

public class UsersLoginDialog  extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usernameTF;
	private JPasswordField passwordTF;
	private boolean valid ;
	static EcoSave ecoSave;
	private RecyclerMenuDialog recyclerMenu ;
	private CollectorMenuDialog collectorMenu ;
	private AdminMenuDialog adminMenu;  
	JLabel lblErrMsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UsersLoginDialog  dialog = new UsersLoginDialog(new JFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UsersLoginDialog(JFrame parent) {
		super(parent, true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setTitle("EcoSave");
		recyclerMenu=  new RecyclerMenuDialog(this);
		collectorMenu = new CollectorMenuDialog(this) ;
		adminMenu = new AdminMenuDialog(this); 



		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblUsername = new JLabel("Username:");
				lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblUsername);
			}
			{
				usernameTF = new JTextField();
				panel.add(usernameTF);
				usernameTF.setColumns(10);
			}
			{
				JLabel lblPassword = new JLabel("Password:");
				lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblPassword);
			}
			{
				passwordTF = new JPasswordField();
				panel.add(passwordTF);
				passwordTF.setColumns(10);
			}
		}
		{
			JLabel lblLogin = new JLabel("Login a user");
			lblLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblLogin, BorderLayout.NORTH);
		}
		{
			lblErrMsg = new JLabel("");
			lblErrMsg.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblErrMsg, BorderLayout.SOUTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton submitButton = new JButton("Login");
				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						boolean valid = true;
						String username = usernameTF.getText();
						String password = passwordTF.getText();

						lblErrMsg.setText("");
						String errMessage = "";

						if (username.equalsIgnoreCase("")) {
							errMessage += "Username is required! \n";
							lblErrMsg.setText(errMessage);
							valid = false;
						}

						if (password.equalsIgnoreCase("")) {
							errMessage += "Password is required! \n";
							lblErrMsg.setText(errMessage);
							valid = false;
						}
						if (valid) {

							User foundUser = ecosaveGui.ecosaveGUI.ecoSave.findUser(username); 
							
							if (foundUser != null) {
								if(foundUser.getPassword().equals(password)) {
									if(foundUser instanceof Recycler) {
										//go to Recycler dialog
										recyclerMenu.setMyUser(((User) foundUser));//send user to next menu
										recyclerMenu.setVisible(true);//turn next menu on
//										recyclerMenu.logged(true); 
									}
									if (foundUser instanceof Admin)	{
										adminMenu.setVisible(true);
									}
									if(foundUser instanceof Collector)	 {
										collectorMenu.setMyUser(((User) foundUser));
										collectorMenu.setVisible(true);
										collectorMenu.setFullName("testing fine"); 

									}
								}
							}
							else {
									JOptionPane.showMessageDialog( null, "Incorrect username/password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
							}

						}


					}

				});


				submitButton.setActionCommand("Submit");
				buttonPane.add(submitButton);
				getRootPane().setDefaultButton(submitButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						usernameTF.setText("");
						passwordTF.setText("");
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
