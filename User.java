package ecosaveGui;
/**
 * An abstract that represents a user in the EcoSave System. Each
 * user will have a unique username, password, full name, and total
 * points collected for recycling materials.
 * @author KokChyeHock
 *
 */
public abstract class User implements Comparable<User> {
	private String username;
	private String password;
	private String fullName;
	private int totalPoints;
	
	/**
	 * Constructor that takes three arguments that are used to initialse
	 * three attributes, username, password, and full name. Total points
	 * set to zero.
	 * @param username Unique username of a user
	 * @param password Password of a user
	 * @param fullName Full name of a user
	 */
	public User(String username, String password, String fullName) {
		setUsername(username);
		setPassword(password);
		setFullName(fullName);
		setTotalPoints(0);
	}
	
	/**
	 * Default constructor of a User
	 */
	public User() {
		this("not set", "not set", "not set");
	}

	/**
	 * Getter for username
	 * @return Username of a user
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Setter for username
	 * @param username New username 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Getter for password
	 * @return Password of a user
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Setter for password of a user
	 * @param password New password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Getter for full name
	 * @return Full name of a user
	 */
	public String getFullName() {
		return fullName;
	}
	
	/**
	 * Setter for full name
	 * @param fullName New full name of a user
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	/**
	 * Getter for total points earned
	 * @return Total points earned by a user
	 */
	public int getTotalPoints() {
		return totalPoints;
	}
	
	/**
	 * Setter for total points 
	 * @param totalPoints New total points
	 */
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	/**
	 * Checking for equality of two User objects.
	 * Two users are deemed to be equal if their usernames
	 * are the same
	 * @param obj User object to check for equality
	 * #return true if both users are the same, false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof User)
			return getUsername().equalsIgnoreCase(
				((User) obj).getUsername());
		return false;
	}
	
	/**
	 * Returns a string representation of a user
	 * @return A string representing details of a user
	 */
	public String toString() {
		return String.format("%s (%s) with points: %d", getFullName(),
			getUsername(), getTotalPoints());
	}
	
	/**
	 * Update the total points earned by a user
	 * @param pointsEarned New points earned
	 */
	public void updatePoints(int pointsEarned) {
		setTotalPoints( getTotalPoints() + pointsEarned);
	}
	
	/**
	 * Compare two uses according to their full name, lexicographically
	 * @return the value 0 if full name of parametric user is equal to this 
	 * user's full name; a value greater than 0 if full name of parametric 
	 * user is lexicographically less than the full name of this user; and a 
	 * value less than 0 if full name of parametric user is lexicographically
	 * greater than full name of this user.
	 */
	public int compareTo(User rhs) {
		return getFullName().compareTo(rhs.getFullName());
	}
}
