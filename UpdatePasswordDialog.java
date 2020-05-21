
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

public class UpdatePasswordDialog extends JDialog {

	private JPanel contentPane;
	private User myUser;
	private ArrayList<Recycler> recyclers;
	private JTextField textField;


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
	public void alert(String msg) {
		JOptionPane.showMessageDialog(rootPane, msg);
	}
	public UpdatePasswordDialog(JDialog parent) {
		super(parent, true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(210, 92, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("New Password");
		lblNewLabel.setBounds(90, 95, 97, 14);
		contentPane.add(lblNewLabel);

		JButton submitButton = new JButton("Update Password");
		submitButton.setBounds(138, 148, 158, 23);
		contentPane.add(submitButton);

		JLabel lblUpdatePassword = new JLabel();
		lblUpdatePassword.setText("Update Password");
		lblUpdatePassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUpdatePassword.setBounds(137, 0, 174, 31);
		contentPane.add(lblUpdatePassword);
		setTitle("");
		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean valid = true;
				String password = textField.getText();
				if (valid) {
					if(!password.equals("") ) {
						User foundUser = ecosaveGui.ecosaveGUI.ecoSave.findUser(myUser.getUsername());
						foundUser.setPassword(password);
						alert("Password Updated Successfully");
						setVisible(false);
					}

					else {
						alert("please fill in all the details");
					}
				}
			}

		});

		JPanel panel = new JPanel( new SpringLayout() );


		final JTextField textfield = new JTextField(65);
		String newPassword = textfield.getText();

	}
	public User getMyUser() {
		return myUser;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
}

