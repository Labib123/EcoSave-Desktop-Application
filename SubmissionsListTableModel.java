package ecosaveGui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class SubmissionsListTableModel extends AbstractTableModel{

	//Array to display in table
	private ArrayList<Submission> submissions;


	//Names of columns
	private String[] columnHeader = {
			"Submission ID","Recycler Full Name", "Collector Full Name", "Proposed Date", "Actual Date", 
			"Weight In Kg", "Points Awarded", "Status"};

	public SubmissionsListTableModel(ArrayList<Submission> submissions) {
		this.submissions = submissions;
	}
	//Creating Table
	public int getRowCount() {
		return getSubmissions().size();
	}
	public int getColumnCount() {
		return columnHeader.length;
	}
	public Object getValueAt(int row, int column) {
		Submission v = getSubmissions().get(row);

		switch (column)
		{
		case 0:
			return v.getSubmissionID();
		case 1:
			return v.getRecycler().getFullName();
		case 2:
			return v.getCollector().getFullName();	
		case 3:
			return v.getProposedDate();
		case 4:
			return v.getActualDate();
		case 5:
			return v.getWeightInKg();
		case 6:
			return v.getPointsAwarded();
		case 7:
			return v.getStatus();	
		default:
			return "";
		}
	}

	public String getColumnName(int column) {
		return columnHeader[column];
	}

	/** Getter
	 * @return the submissions object
	 */
	public ArrayList<Submission> getSubmissions() {
		return submissions;
	}
	
	public void setSubmissions(ArrayList<Submission> submissions) {
		this.submissions = submissions;
	}

	/**
	 * 
	 * @param submission
	 */
	public void add(Submission submission) {
		getSubmissions().add(submission);
		fireTableDataChanged();
	}

}
