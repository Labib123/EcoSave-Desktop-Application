package ecosaveGui;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A concrete subclass of class User. Additional attributes of
 * a Collector is an address, a collection of materials, and 
 * submissions.
 * @author KokChyeHock
 *
 */
public class Collector extends User implements Serializable{
	private String address;
	private ArrayList<Material> materials;
	private ArrayList<Submission> submissions;
	
	/**
	 * Constructor with found arguments
	 * @param username Username of a user
	 * @param password Password of a user
	 * @param fullName Full name of a user
	 * @param address Address of a collector
	 */
	public Collector(String username, String password, String fullName,
		String address) {
		super(username, password, fullName);
		setAddress(address);
		
		setMaterials(new ArrayList<Material>());
		setSubmissions(new ArrayList<Submission>());
	}

	/**
	 * Default constructor for Collector
	 */
	public Collector() {
		this("not set", "not set", "not set", "not set");
	}
	
	/**
	 * Getter for address
	 * @return Address of a Collector 
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter for address
	 * @param address New address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Getter for list of submissions
	 * @return
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
	 * Getter for list of materials
	 * @return List of materials
	 */
	public ArrayList<Material> getMaterials() {
		return materials;
	}

	/**
	 * Setter for list of materials
	 * @param materials New list of materials
	 */
	public void setMaterials(ArrayList<Material> materials) {
		this.materials = materials;
	}

	/**
	 * Method to check whether the collector collect
	 * the parametric material
	 * @param material Material to check
	 * @return true if collector receives the parametric type
	 * of material, false otherwise
	 */
	public boolean hasMaterial(Material material) {
		return getMaterials().contains(material);
	}
	
	/**
	 * Adding material to the list
	 * @param material New material to be added
	 */
	public void add(Material material) {
		getMaterials().add(material);
	}
	
	/**
	 * Adding new submission to the list
	 * @param submission New submission to be added
	 */
	public void addSubmission(Submission submission) {
		getSubmissions().add(submission);
	}
	
	/**
	 * Find submission based on id
	 * @param submissionID id of submission
	 * @return Submission that matched the parametric id, 
	 * null otherwise
	 */
	public Submission findSubmission(int submissionID) {
		for (Submission subm: getSubmissions())
			if (subm.getSubmissionID() == submissionID)
				return subm;
		return null;
	}
	
	/**
	 * Find all submission of 'Proposed' status submitted by the
	 * parametric Recycler
	 * @param recycler Recycler to search for the required submission
	 * @return All 'Proposed' submissions by the parametric Recycler
	 */
	public ArrayList<Submission> findSubmissionsFor(Recycler recycler) {
		ArrayList<Submission> submission4Recycler = new ArrayList<Submission>();
		for (Submission s: getSubmissions())
			if (s.getRecycler().equals(recycler) &&
					s.getStatus().equalsIgnoreCase("Proposed"))
				submission4Recycler.add(s);
		
		return submission4Recycler;
	}

	/** 
	 * Number of materials collected by this collector
	 * @return Number of materials collected by this collector
	 */
	public int numOfCollectedMaterials() {
		return getMaterials().size();
	}
	
	/**
	 * Returns a string containing the details of a collector, 
	 * together with the materials the collector accept.
	 * @return A string containing details of a collector
	 */
	public String toString() {
		String first = "Collector: " + super.toString();
		if (numOfCollectedMaterials() != 0) {
			ArrayList<String> materialNames = new ArrayList<>();
			for (Material material: getMaterials())
				materialNames.add(material.getMaterialName());
			return first + "\nCollecting " + materialNames;
		}
		else
			return first;
	}
	
	/**
	 * Returns a string containing all the materials collected
	 * by a collector, together with submissions, if any
	 * @return A string containing all the materials collected
	 * by a collector, together with submissions, if any
	 */
	public String allMaterials() {
		String result = "";
		for (Material m: getMaterials()) {
			if (m.numOfSubmissions() != 0)
				result += m + " has " + m.numOfSubmissions() + " submission(s)\n";
			else
				result += m + "\n";
		}
		return result;
	}
	
	/**
	 * Number of submissions received by a collector
	 * @return Number of submissions received by a collector
	 */
	public int numOfSubmissions() {
		return getSubmissions().size();
	}
	
	/**
	 * Remove the parametric material from a collector. The 
	 * material exists in the collector's material list.
	 * @param material Material to be removed
	 */
	public void removeMaterial(Material material) {
		getMaterials().remove(material);
	}
}
