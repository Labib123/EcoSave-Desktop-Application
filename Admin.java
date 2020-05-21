package ecosaveGui;

import java.io.Serializable;

/**
 * Admin class for EcoSave System
 * @author KokChyeHock
 *
 */
public class Admin extends User implements Serializable{
	/**
	 * Constructor with arguments, whose values are used to 
	 * initialise corresponding attributes.
	 * @param username User's unique username
	 * @param password User's password
	 * @param fullName User's full name
	 */
	public Admin(String username, String password, String fullName) {
		super(username, password, fullName);
	}	

	/**
	 * Returns a string consists of the detail of a user.
	 * @return A string consists of the detail of a user
	 */
	public String toString() {
		return String.format("Admin: %s (%s)", getFullName(),
			getUsername());
	}
}
