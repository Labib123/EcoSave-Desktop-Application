package ecosaveGui;
import java.util.ArrayList;

/**
 * Class Material represent material to be recycled. The information
 * of a material include its name, description, and points per kg. 
 * Each material has a unique ID.
 * Material also has a collection of Collectors, and a collection
 * of Submission objects.
 * @author KokChyeHock
 *
 */
public class Material {
	static int genID = 1;
	private int materialID;
	private String materialName;
	private String description;
	private int pointsPerKg;
	
	private ArrayList<Collector> collectors;
	private ArrayList<Submission> submissions;
	
	/**
	 * Constructor with arguments, whose values are used to
	 * initialise the attributes' value. The constructor also
	 * set a unique id, and initialise the collections of
	 * Collector and Submission objects.
	 * @param materialName Material name
	 * @param description Description about material
	 * @param pointsPerKg Points awarded for one kg of weight
	 */
	public Material(String materialName, String description,
		int pointsPerKg) {
		setMaterialID(genID++);
		setMaterialName(materialName);
		setDescription(description);
		setPointsPerKg(pointsPerKg);
		
		setCollectors(new ArrayList<Collector>());
		setSubmissions(new ArrayList<Submission>());
	}

	/**
	 * Getter for material ID
	 * @return Material ID
	 */
	public int getMaterialID() {
		return materialID;
	}
	
	/**
	 * Setter for material ID
	 * @param materialID A unique material ID
	 */
	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}
	
	/**
	 * Getter for material name
	 * @return Material name
	 */
	public String getMaterialName() {
		return materialName;
	}
	
	/**
	 * Setter for material name
	 * @param materialName Material name
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	/**
	 * Getter for description about material
	 * @return Description about material
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter for material description
	 * @param description Description about material
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for points per kilogram
	 * @return Points per kilogram
	 */
	public int getPointsPerKg() {
		return pointsPerKg;
	}
	
	/**
	 * Setter for points per kilogram
	 * @param pointsPerKg Points awarded for per kilogram
	 */
	public void setPointsPerKg(int pointsPerKg) {
		this.pointsPerKg = pointsPerKg;
	}

	/**
	 * Getter for list of collectors
	 * @return Collectors' list
	 */
	public ArrayList<Collector> getCollectors() {
		return collectors;
	}
	
	/** 
	 * Setter for list of collectors
	 * @param collectors List of collectors
	 */
	public void setCollectors(ArrayList<Collector> collectors) {
		this.collectors = collectors;
	}
	
	/**
	 * Getter for list of submissions
	 * @return Submission list
	 */
	public ArrayList<Submission> getSubmissions() {
		return submissions;
	}
	
	/**
	 * Setter for list of submissions
	 * @param submissions New submission list
	 */
	public void setSubmissions(ArrayList<Submission> submissions) {
		this.submissions = submissions;
	}

	/**
	 * Adding a new submission
	 * @param submission New submission to be added
	 */
	public void addSubmission(Submission submission) {
		getSubmissions().add(submission);
	}
	
	/**
	 * Searching for a submission based on ID
	 * @param submissionID ID of submission
	 * @return Submission that matched the parametric ID,
	 * null otherwise
	 */
	public Submission findSubmission(int submissionID) {
		for (Submission subm: getSubmissions())
			if (subm.getSubmissionID() == submissionID)
				return subm;
		return null;
	}
	
	/**
	 * Checking whether the parametric collector accepts this material
	 * @param collector Collector to be checked
	 * @return True if collector accepts this material, false otherwise
	 */
	public boolean hasCollector(Collector collector) {
		return getCollectors().contains(collector);
	}
	
	/**
	 * Add collector to this material
	 * @param collector Collect to be added
	 */
	public void add(Collector collector) {
		getCollectors().add(collector);
	}
	
	/**
	 * Searching for collector based on username
	 * @param username Username of a collector
	 * @return Collector with the matched username, 
	 * null otherwise
	 */
	public Collector findCollector(String username) {
		for (Collector col: getCollectors())
			if (username.equalsIgnoreCase(col.getUsername()))
				return col;
		return null;
	}
	
	/**
	 * A class method that return the auto-generated ID
	 * @return The static auto-generated ID
	 */
	public static int getGenID() {
		return genID;
	}
	
	/**
	 * Setter for the static auto-generated ID
	 * @param genID New ID to be set
	 */
	public static void setGenID(int genID) {
		Material.genID = genID;
	}
	
	/**
	 * Returns details of a material
	 * @return A string containing the detail of a material
	 */
	public String toString() {
		return String.format("%03d [%20s] Point per kg: %3d\nDescription: %s",
			getMaterialID(), getMaterialName(), getPointsPerKg(),getDescription());
	}
	
	/** 
	 * Checking equality of two materials. Two materials are the same if
	 * their ID are the same
	 * @return True if the parametric material's ID are the same as this
	 * material's ID, false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Material)
			return getMaterialID() == ((Material) obj).getMaterialID();
		return false;
	}
	
	/**
	 * Number of collectors in the system.
	 * @return Number of collectors in the system
	 */
	public int numOfCollectors() {
		return getCollectors().size();
	}
	
	/**
	 * Returns the number of submissions for this material
	 * @return Number of submissions
	 */
	public int numOfSubmissions() {
		return getSubmissions().size();
	}
	
	/**
	 * Getting the names of collectors for this material
	 * @return A string containing the names of all collectors for
	 * this material. If no collector, returns an empty string.
	 */
	public String getCollectorsInfo() {
		String allCollectors = "";
		for (Collector aCollector: getCollectors())
			allCollectors += aCollector.getFullName() + " (username: " +
				aCollector.getUsername() + ")\n";
		return allCollectors;
	}
	
	/**
	 * Remove a submission from this material
	 * @param submission Submission to be removed from the
	 * material
	 */
	public void removeSubmission(Submission submission) {
		getSubmissions().remove(submission);
	}
}
