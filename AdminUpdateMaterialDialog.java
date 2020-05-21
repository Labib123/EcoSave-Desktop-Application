
package ecosaveGui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class AdminUpdateMaterialDialog extends JDialog{



	private Admin admin;
	private final JPanel contentPanel = new JPanel();
	private JTextField materialNameTF;
	private JTextField descriptionTF;
	private JTextField pointsPerKgTF;
	private boolean status;
	private MaterialsArrayTableModel matModel;
	private Material material;



	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Launch the application. 
	 * */
	public static void main(String[] args) {
		try {
			AdminUpdateMaterialDialog dialog = new AdminUpdateMaterialDialog(new JDialog());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdminUpdateMaterialDialog(JDialog parent) {
		setModal(true);
		//when we want to use same name
		setBounds(100, 100, 450, 300);
		setTitle("EcoSave");

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblMaterialName = new JLabel("New Material Name:");
				panel.add(lblMaterialName);
			}
			{
				materialNameTF = new JTextField();
				panel.add(materialNameTF);
				materialNameTF.setColumns(10);

			}
			{
				JLabel lblDescription = new JLabel("New Description:");
				panel.add(lblDescription);
			}
			{
				descriptionTF = new JTextField();
				panel.add(descriptionTF);
				descriptionTF.setColumns(10);
			}
			{
				JLabel lblPointsPerKg = new JLabel("New Points Per Kg:");
				panel.add(lblPointsPerKg);
			}
			{
				pointsPerKgTF = new JTextField();
				panel.add(pointsPerKgTF);
				pointsPerKgTF.setColumns(10);
			}

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton updateBtn = new JButton("Update");
				updateBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String name = materialNameTF.getText();
						String description = descriptionTF.getText();
						String pointsPerKg = pointsPerKgTF.getText();


						try {
							if (!name.isEmpty())
								material.setMaterialName(name);
							if(!description.isEmpty())
								material.setDescription(description);
							if(!pointsPerKg.isEmpty())
								material.setPointsPerKg(Integer.parseInt(pointsPerKg));

							if (name.isEmpty() && description.isEmpty() && 
									pointsPerKg.isEmpty())
								JOptionPane.showMessageDialog(null, "Update aborted");
							else
								JOptionPane.showMessageDialog(null, "Successfully Updated");

						} catch (Exception x) {
							x.printStackTrace();
						}
						status = true;
						setVisible(false);
						materialNameTF.setText("");
						descriptionTF.setText("");
						pointsPerKgTF.setText("");
					}
				});
				buttonPane.add(updateBtn);
				getRootPane().setDefaultButton(updateBtn);
			}
			{
				JButton cancelBtn = new JButton("Cancel");
				cancelBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						status = false;
						materialNameTF.setText("");
						descriptionTF.setText("");
						pointsPerKgTF.setText("");
					}
				});
				buttonPane.add(cancelBtn);
			}
		}
		{
			JLabel lblUpdateMaterial = new JLabel("Update Material");
			lblUpdateMaterial.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblUpdateMaterial.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblUpdateMaterial, BorderLayout.NORTH);
		}
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setMaterial(Material m) {
		this.material = m;
	}


}