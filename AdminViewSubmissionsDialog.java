package ecosaveGui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import java.awt.Font;
import java.awt.Rectangle;

public class AdminViewSubmissionsDialog extends JDialog{

	private ArrayList<Submission> submissions;
	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JTable table;
	private SubmissionsListTableModel satModel;
	private Submission s;
	private SubmissionsListTableModel saTM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminViewSubmissionsDialog dialog = new AdminViewSubmissionsDialog(new JDialog());
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
	public AdminViewSubmissionsDialog(JDialog parent) {
		setModal(true);
		setBounds(new Rectangle(0, 23, 0, 0));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 662, 600);
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
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnBack);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JLabel lblViewSubmissionsList = new JLabel("View Submissions List");
		lblViewSubmissionsList.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblViewSubmissionsList.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblViewSubmissionsList, BorderLayout.NORTH);

		submissions = new ArrayList<Submission>();//initialise array list

	}

	public void initSubmissionsList() {
		satModel = new SubmissionsListTableModel(getSubmissions());
		table = new JTable(satModel);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(1000);
		table.getColumnModel().getColumn(1).setPreferredWidth(1000);
		table.getColumnModel().getColumn(2).setPreferredWidth(1000);
		table.getColumnModel().getColumn(3).setPreferredWidth(1000);
		table.getColumnModel().getColumn(4).setPreferredWidth(1000);
		table.getColumnModel().getColumn(5).setPreferredWidth(1000);
		table.getColumnModel().getColumn(6).setPreferredWidth(1000);
		table.getColumnModel().getColumn(7).setPreferredWidth(1000);
	}
	
	public ArrayList<Submission> getSubmissions() {
		return submissions;
	}
	
	public void setSubmissions(ArrayList<Submission> submissions) {
		this.submissions = submissions;
	}
}
