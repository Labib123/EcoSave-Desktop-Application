package ecosaveGui;

import java.time.LocalDate;

/**
 * Class Submission represents a submission of material by a recycler
 * to a collector. The information recorded include material, collector
 * recycler, proposed date, actual date, weight in kg, and points awarded
 * for each submission. Each submission has a unique id, as well as
 * a status of either "Proposed" or "Submitted"
 * @author KokChyeHock
 *
 */
public class Submission {
	static int getID = 1;
	private Collector collector;
	private Material material;
	private Recycler recycler;
	
	private int submissionID;
	private LocalDate proposedDate;
	private LocalDate actualDate;
	private double weightInKg;
	private int pointsAwarded;
	private String status; // "Proposed" or "Submitted"
	
	/**
	 * Constructor with arguments, whose values are used to initialise
	 * the attributes' values. A submission id is auto-generated, and
	 * default status is "Proposed".
	 * @param material Material recycled by recycler
	 * @param collector Collector of the submitted material
	 * @param recycler Recylcer who recycle the material
	 * @param proposedDate Proposed date for submission
	 */
	public Submission(Material material, Collector collector, 
		Recycler recycler, LocalDate proposedDate) {
		setSubmissionID(getID++);
		setProposedDate(proposedDate);
		setCollector(collector);
		setRecycler(recycler);
		setMaterial(material);
		setStatus("Proposed");
	}
	
	/**
	 * Returns the details of a submission
	 * @return The details of a submission
	 */
	public String toString() {
		return getSubmissionID() + " : " + getProposedDate() + " : " + 
			getActualDate() + " : " +
			getMaterial().getMaterialName() + " : " + 
			getWeightInKg() + " : " + getPointsAwarded() + " : " +
			getCollector().getFullName() + " : " +
			getRecycler().getFullName() + " : " + 
			getStatus();
	}

	/**
	 * Getter for the collector
	 * @return Collector who accepts the material
	 */
	public Collector getCollector() {
		return collector;
	}
	
	/**
	 * Setter for the collector who accepts the material
	 * The method also set this submission to the parametric collector
	 * @param collector Collector who accepts the material
	 */
	public void setCollector(Collector collector) {
		this.collector = collector;
		this.collector.addSubmission(this);
	}
	
	/**
	 * Getter for the material in this submission
	 * @return Material recycled by the recycler
	 */
	public Material getMaterial() {
		return material;
	}
	
	/**
	 * Setter for material in this submission
	 * This method also associated this submission to the material
	 * @param material Material to be recycled
	 */
	public void setMaterial(Material material) {
		if (this.material == null) {
			this.material = material;
			material.addSubmission(this);
		}
		else {
			this.material.removeSubmission(this);
			this.material = material;
			material.addSubmission(this);
		}
	}
	
	/**
	 * Getter for recycler of this submission
	 * @return The recycler of this submission
	 */
	public Recycler getRecycler() {
		return recycler;
	}
	
	/**
	 * Setter for the recycler of this submission
	 * This method also add this submission to the recycler
	 * @param recycler Recycler who proposed this submission
	 */
	public void setRecycler(Recycler recycler) {
		this.recycler = recycler;
		this.recycler.addSubmission(this);
	}
	
	/**
	 * Getter for the submission ID
	 * @return Submission ID
	 */
	public int getSubmissionID() {
		return submissionID;
	}
	
	/**
	 * Setter for the submission ID
	 * @param submissionID The unique ID to be set for this submission
	 */
	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}
	
	/**
	 * Getter for the proposed date of this submission
	 * @return The proposed date for this submission
	 */
	public LocalDate getProposedDate() {
		return proposedDate;
	}
	
	/**
	 * Setter for the proposed date of this submission
	 * @param proposedDate The proposed date
	 */
	public void setProposedDate(LocalDate proposedDate) {
		this.proposedDate = proposedDate;
	}
	
	/**
	 * Getter for the actual date of this submission
	 * @return The actual date for this submission
	 */	
	public LocalDate getActualDate() {
		return actualDate;
	}

	/**
	 * Setter for the actual date of this submission
	 * @param actualDate The actual date
	 */
	public void setActualDate(LocalDate actualDate) {
		this.actualDate = actualDate;
	}
	
	/**
	 * Getter for the weight in kg of the material in this
	 * submission
	 * @return The weight in kg of the material
	 */
	public double getWeightInKg() {
		return weightInKg;
	}
	
	/**
	 * Setter for the weight in kg of the material in this
	 * submission. This method also set the points awarded
	 * for this submission.
	 * @param weightInKg Weight in kg of the material
	 */
	public void setWeightInKg(double weightInKg) {
		this.weightInKg = weightInKg;
		setPointsAwarded((int) (getWeightInKg() * 
			getMaterial().getPointsPerKg()));
	}
	
	/**
	 * Getter for the points awarded for the material in this submission
	 * @return The points awarded for the material in this submission
	 */
	public int getPointsAwarded() {
		return pointsAwarded;
	}
	
	/**
	 * Setter for the points awareded for this submission
	 * @param pointsAwarded Points awarded
	 */
	public void setPointsAwarded(int pointsAwarded) {
		this.pointsAwarded = pointsAwarded;
	}
	
	/**
	 * Getter for the status of this submission
	 * @return Either "Proposed" or "Submitted"
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Setter for the status of this submission
	 * @param status Either the string "Proposed" or "Submitted"
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Class method for getting the auto-generated ID used
	 * in creating a submission ID
	 * @return The static auto-generated ID used
	 */
	public static int getGetID() {
		return getID;
	}
	
	/**
	 * Setter for the static auto-generated ID used
	 * @param getID An integer, which is auto-generated, to be
	 * used in assign to the submission ID 
	 */
	public static void setGetID(int getID) {
		Submission.getID = getID;
	}
	
	/**
	 * Checking equality of two Submission objects. Two submissions are
	 * equal if their submission ID are equal.
	 * @return True if both submissions have the same submission ID, false
	 * otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Submission)
			return getSubmissionID() == ((Submission) obj).getSubmissionID();
		return false;
	}
}
