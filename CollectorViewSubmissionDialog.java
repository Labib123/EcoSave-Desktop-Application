package ecosaveGui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CollectorViewSubmissionDialog extends JDialog {

	private ArrayList<Submission> submissions;
	JScrollPane scrollPane;
	private JPanel contentPane;
	private JTable table;
	private SubmissionsListTableModel satModel;
	private User myUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CollectorViewSubmissionDialog dialog = new CollectorViewSubmissionDialog(new JDialog());
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
	public CollectorViewSubmissionDialog(JDialog parent) {
		super(parent, true);
		//		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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

		JLabel lblNewLabel = new JLabel("View Submissions List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		submissions = new ArrayList<Submission>();

	}

	public void initSubmissionsList() {
		satModel = new SubmissionsListTableModel(getSubmissions());
		table = new JTable(satModel);
		scrollPane.setViewportView(table);
	}
	/**
	 * @return the submissions object
	 */
	public ArrayList<Submission> getSubmissions() {
		return submissions;
	}
	/**
	 * @param submissions the submissions to set
	 */
	public void setSubmissions(ArrayList<Submission> submissions) {
		this.submissions = submissions;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser ;

	}

}

