package ecosaveGui;

import java.util.ArrayList;
import java.util.Collections;

/**
 * EcoSave class contains two lists, one for users, and another for 
 * materials.
 * @author KokChyeHock
 *
 */
public class EcoSave {
	private ArrayList<User> users;
	private ArrayList<Material> materials;
	private ArrayList<Submission> submissions;
	
	/**
	 * No arguments constructor.
	 * Initialised list of users and materials.
	 */
	public EcoSave() {
		setUsers(new ArrayList<User>());
		setMaterials(new ArrayList<Material>());
		setSubmissions(new ArrayList<Submission>());
	}
	
	public ArrayList<Submission> getSubmissions(){
		return submissions;
	}
	
	public void setSubmissions(ArrayList<Submission> submissions) {
		this.submissions = submissions;
	}
	
	/** 
	 * Getter for list of users.
	 * @return List of users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * Setter for list of user
	 * @param users List of users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
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
	 * @param materials List of materials
	 */
	public void setMaterials(ArrayList<Material> materials) {
		this.materials = materials;
	}
	
	/**
	 * Returns number of users in the list
	 * @return Number of users in the list
	 */
	public int numOfUsers() {
		return getUsers().size();
	}
	
	/**
	 * Returns number of materials in the list
	 * @return Number of materials in the lsit
	 */
	public int numOfMaterials() {
		return getMaterials().size();
	}
	
	public int numOfSubmissions() {
		return getSubmissions().size();
	}
	
	/**
	 * Adding a new user to the user's list
	 * @param user New user to be added
	 */
	public void addUser(User user) {
		getUsers().add(user);
	}
	
	/**
	 * Adding a new material to the material's list
	 * @param material New material to be added
	 */
	public void addMaterial(Material material) {
		getMaterials().add(material);
	}
	
	/**
	 * Search for a user, based on username
	 * @param username Username of a user
	 * @return User of the matched username, null otherwise
	 */
	public User findUser(String username) {
		for (User user: getUsers())
			if (user.getUsername().equalsIgnoreCase(username))
				return user;
		
		return null;
	}
	
	/**
	 * Searching for a material based on parametric id
	 * @param materialID ID for material
	 * @return Material corresponding to the parametric id, null
	 * otherwise.
	 */
	public Material findMaterial(int materialID) {
		for (Material material: getMaterials())
			if (material.getMaterialID() == materialID)
				return material;
		return null;
	}
	
	/**
	 * Removing a material from the system. Once removed,
	 * all collectors will not be able to collect this 
	 * material. Those submissions with this material will
	 * still be available in the collector/recycler.
	 * @param material Material to be removed
	 */
	public void removeMaterial(Material material) {
		if (material.numOfCollectors() != 0) {
			for (Collector col: material.getCollectors())
				if (col.hasMaterial(material))
					col.removeMaterial(material);
		}
		getMaterials().remove(material);
	}

	/**
	 * Returns a list containing all users, either in original
	 * order or sorted by Fullname.
	 * @param criteria Criteria for ordering the users
	 * @return list containing all users, either in original 
	 * order or sorted by Fullname.
	 */	
	public ArrayList<User> allUsers(String criteria) {
		if (criteria.equalsIgnoreCase("Fullname")) {
			ArrayList<User> copy = (ArrayList<User>) getUsers().clone();
			Collections.sort(copy);
			return copy;
		}
		else
			return getUsers();
	}
	
	/**
	 * Returns a string containing all users, either in original
	 * order or sorted by Fullname.
	 * @param criteria Criteria for ordering the users
	 * @return String containing all users, either in original 
	 * order or sorted by Fullname.
	 */
	public String allUsersAsString(String criteria) {
		String result = "";
		if (criteria.equalsIgnoreCase("Fullname")) {
			ArrayList<User> copy = (ArrayList<User>) getUsers().clone();
			Collections.sort(copy);
			for (User user: copy)
				result += user+ "\n";
		}
		else
			for (User user: getUsers())
				result += user + "\n";
		
		return result;
	}
	
	/**
	 * Returns all submissions as a string, categorised by 
	 * material
	 * @return All submissions as a string
	 */
	public String allSubmissions() {
		String result = "";
		for (Material m: getMaterials()) {
			result += m + "\n";
			if (m.numOfSubmissions() != 0) {
				for (Submission s: m.getSubmissions()) {
					result += " ... " + s + "\n";
				}
			}
		}
		return result;
	}
	
	/**
	 * Returns a string containing all materials
	 * @return A string containing all materials
	 */
	public String allMaterials() {
		String result = "";
		for (Material m: getMaterials())
			if (m.numOfSubmissions() != 0)
				result += m + " has " + m.numOfSubmissions() + " submissions.\n";
			else
				result += m + "\n";
		
		return result;
	}
	
	/**
	 * Add new material to collector, and vice versa.
	 * 
	 * @param collector Collector that the material to be added to
	 * @param material Material to be added to the collector
	 */
	public void addMaterialToCollector(Collector collector, Material material) {
			material.add(collector);
			collector.add(material);
	}
	
	/**
	 * Search for a submission based on submission ID
	 * @param submissionID Submission ID
	 * @return Submission that matched the parametric ID
	 */
	public Submission findSubmission(int submissionID) {
		for (Material m: getMaterials())
			for (Submission s: m.getSubmissions())
				if (s.getSubmissionID() == submissionID)
					return s;
		return null;
	}

}