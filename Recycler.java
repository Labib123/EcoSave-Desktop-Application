package ecosaveGui;
import java.util.ArrayList;

/**
 * A concrete subclass of class User. Additional attributes of
 * a Recycler is EcoLevel and a collection of submissions.
 * @author KokChyeHock
 *
 */public class Recycler extends User {
	private EcoLevel ecoLevel;
	private ArrayList<Submission> submissions;

	/**
	 * Constructor with arguments.
	 * @param username Recycler's username
	 * @param password Recycler's password
	 * @param fullName Recycler's full name
	 */
	public Recycler(String username, String password, String fullName) {
		super(username, password, fullName);
		setEcoLevel(EcoLevel.NEWBIE);
		setSubmissions(new ArrayList<Submission>());
	}

	/**
	 * Getter for list of submissions
	 * @return List of submissions
	 */
	public ArrayList<Submission> getSubmissions() {
		return submissions;
	}

	/**
	 * Setter for list of submissions
	 * @param submissions New list of submissions
	 */
	public void setSubmissions(ArrayList<Submission> submissions) {
		this.submissions = submissions;
	}
	/**
	 * Getter for EcoLevel
	 * @return EcoLevel
	 */
	public EcoLevel getEcoLevel() {
		return ecoLevel;
	}

	/**
	 * Setter for EcoLevel
	 * @param EcoLevel to be set
	 */
	public void setEcoLevel(EcoLevel ecoLevel) {
		this.ecoLevel = ecoLevel;
	}

	/**
	 * Adding new submission to the list
	 * @param submission Submission to be added
	 */
	public void addSubmission(Submission submission) {
		getSubmissions().add(submission);
	}
	
	/**
	 * Searching submission based on submissiion id
	 * @param submissionID Submission ID
	 * @return Submission with the matched ID
	 */
	public Submission findSubmission(int submissionID) {
		for (Submission subm: getSubmissions())
			if (subm.getSubmissionID() == submissionID)
				return subm;
		return null;
	}
	
	/**
	 * Update the total points, and set the EcoLevel, if
	 * requirement met
	 * @param pointsEarned Points earned from recycling item
	 */
	public void updatePoints(int pointsEarned) {
		super.updatePoints(pointsEarned);
		if (getTotalPoints() >= 1000)
			setEcoLevel(EcoLevel.ECOWARRIOR);
		else if (getTotalPoints() >= 500)
			setEcoLevel(EcoLevel.ECOHERO);
		else if (getTotalPoints() >= 100)
			setEcoLevel(EcoLevel.ECOSAVER);
	}
	
	/**
	 * Number of submissions in the list
	 * @return Number of submissions
	 */
	public int numOfSubmissions() {
		return getSubmissions().size();
	}
	
	/**
	 * Returns a string containing the detail of a Recycler
	 * @return A string containing the detail of a Recycler
	 */
	public String toString() {
		return "Recycler [" + getEcoLevel().inWord() + "]: " + 
			super.toString();
	}
}
