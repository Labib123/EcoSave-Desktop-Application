package ecosaveGui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Driver class that create an instance of EcoSave object, and allows
 * various methods of it to be tested.
 * @author KokChyeHock
 *
 */
public class EcoSaveConsole {
	private static final int MAX_PASSWORD_TRIES = 3;
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static EcoSave ecoSave;
	static Scanner kbd;
	static User loginUser;
	
	/**
	 * The main method that initiate the application
	 * @param args unused command prompt parameters of type string
	 */
	public static void main(String[] args) {
		ecoSave = new EcoSave();
		kbd = new Scanner(System.in);
		
		//initialised();
		ecoSave.addUser(new Admin("admin", "admin", "Administrator"));
		char choice;
		do {
			choice = menu();
			switch (choice) {
			case 'R':
				registration();
				break;
			case 'L':
				login();
				break;
			case 'Q':
				quit();
				break;
			case 'U':
				listUsers();
				break;
			case 'M': 
				listMaterials();
				break;
			case 'S':
				listSubmissions();
				break;
			default:
				error();
				break;
			}
			System.out.println();
		} while (choice != 'Q');
	}

	/**
	 * Presenting the application menu for user to choose from.
	 * @return choice User's selected option, a character.
	 */
	public static char menu() {
		System.out.println("Green Green Grass Of Home - Main");
		System.out.println("Press");
		System.out.println("R - Register User");
		System.out.println("L - Login");
		System.out.println("U - List Users");
		System.out.println("M - List Materials");
		System.out.println("S - List Submissions");
		System.out.println("Q - Quit");
		System.out.println();
		String response = getText("Your choice? ");
		char choice = response.toUpperCase().charAt(0);
		return choice;
	}

    /**
     * Register either Collector or Recycler
     */
	public static void registration() {
		System.out.println("Register as NEW EcoSave user...");
		char userType = ' ';
		do {
			String type = getText("Sign up as <C>ollector, or <R>ecycler: ");
			userType = type.toUpperCase().charAt(0);
			if (userType != 'C' && userType != 'R')
				System.out.println("Please enter either 'C' or 'R' only!");
		} while (userType != 'C' && userType != 'R');
		
		if (userType == 'C')
			signUp("Collector", null);
		else
			signUp("Recycler", null);		
	}
	
    /**
     * Helper method that create either Collector or Recycler
     * @param userType the type of user that is being created.
     * @param inUsername Either already entered username or null
     */
    public static void signUp(String userType, String inUsername) {
        System.out.println("Create " + userType + " ...");
        String username = inUsername == null? getUsername(): inUsername;
        String password = getText("Password: "); 
        String fullName = getText("Full name:"); 
        
        if (userType.equalsIgnoreCase("Collector")) {
            String address = getText("Address: "); // extra for Collector
            ecoSave.addUser(new Collector(username, password, fullName, 
            	address));
        }
        else
        	ecoSave.addUser(new Recycler(username, password, fullName));
            
        System.out.println("New user created successfully!");
    }
    
    /**
     * Method that initiate user login
     * If username is not defined, prompt user whether to create new user.
     * Once login, a different menu options will be displayed, 
     * depends on the type of login user.
     */
    public static void login() {
    	boolean finished = false;
    	do {   		
            System.out.println("\nSign in");
            String username = getText("Username: ");
            loginUser = ecoSave.findUser(username);

    		if (loginUser == null) {
            	System.out.println("User name DOES NOT exist!");
            	System.out.print("Sign up as <C>ollector, or <R>ecycler " +
            		"or Any other key to try another account.");
            	String response = kbd.nextLine();
            	if (!response.equals(""))            		
	            	switch (response.charAt(0)) {
	            	case 'C': case 'c':
	            		signUp("Collector", username);
	            		break;
	            	case 'R': case 'r':
	            		signUp("Recycler", username);
	            		break;
	            	}
    		}
    		else
    			finished = true;
    	} while (!finished);

        String inPassword = getText("Password: ");
        
        int maxTries = 1;
        while (!loginUser.getPassword().equals(inPassword)) {
            System.out.println("Invalid password!");
            if (maxTries++ == MAX_PASSWORD_TRIES) {
            	System.out.println("Please try again later!");
            	return ;
            }
            System.out.println();
            inPassword = getText("Password: ");
        }
        System.out.println("Sign in successfully!");
        System.out.println(loginUser);
        System.out.println();
        
        if (loginUser instanceof Admin)
            adminTasks();
        else if (loginUser instanceof Collector)
            collectorTasks();
        else
        	recyclerTasks();
    }
    
	/**
	 * List all users, either in original order or 
	 * sorted according to fullname
	 */
	public static void listUsers() {
		if (ecoSave.numOfUsers() == 0)
			System.out.println("Please add users first");
		else {
			char order = ' ';
			do {		
				String criteria = getText("<O>riginal order " + 
					"or sorted according to <F>ullname? ");
				order = criteria.toUpperCase().charAt(0);
				if (order != 'O' && order != 'F')
					System.out.println("Please enter either 'O' or 'F' only!");
			} while (order != 'O' && order != 'F');			
			
			ArrayList<User> allUsers;
			if (order == 'F')
				allUsers = ecoSave.allUsers("fullname");
			else
				allUsers = ecoSave.allUsers("original");
				System.out.println("All users:");
			
			// Display submission as well
			for (User u: allUsers) {
				System.out.println("---------------------------");
				System.out.println(u);
				if (u instanceof Collector) {
					Collector c = (Collector) u;
					if (c.numOfSubmissions() != 0)
						for (Submission s: c.getSubmissions())
							System.out.println("... " + s);
				}
				else if (u instanceof Recycler) {
					Recycler r = (Recycler) u;
					if (r.numOfSubmissions() != 0)
						for (Submission s: r.getSubmissions())
							System.out.println("... " + s);
				}
			}
		}
	}
	
	/**
	 * List all materials, that can be recycled.
	 */
	public static void listMaterials() {
		if (ecoSave.numOfMaterials() == 0)
			System.out.println("No material has been added yet!");
		else
			displayMaterials();
	}

	/**
	 * List of all submissions, categorised by material type.
	 * Not required in Assignment.
	 */
	public static void listSubmissions() {
		if (ecoSave.numOfMaterials() == 0)
			System.out.println("No material has been added yet!");
		else
			System.out.println("All submissions:\n" + 
				ecoSave.allSubmissions());
	}

    /**
     * Presenting the options that the Admin can do
     */
	public static char adminMenu() {
		System.out.println("Green Green Grass Of Home - " + 
			loginUser.getFullName());
		System.out.println("Press");
		System.out.println("A - Add new material");
		System.out.println("U - Update existing material");
		System.out.println("D - Delete existing material");
		System.out.println("V - View submission history");
		System.out.println("B - Back to main");
		System.out.println();
		String response = getText("Your choice? ");
		char choice = response.toUpperCase().charAt(0);
		return choice;
	}
	
    /**
     * Invoking the appropriate task that the Admin has selected.
     */
    public static void adminTasks() {
    	if (ecoSave.numOfMaterials() == 0) {
    		System.out.println("No material has been added yet!");
    		addNewMaterial();	
    	}
    	boolean done = false;
    	do {
    		char option = adminMenu();
    		switch (option) {
    		case 'A':
    			addNewMaterial();
    			break;
    		case 'U':
    			updateMaterial();
    			break;
    		case 'D':
    			deleteMaterial();
    			break;
    		default:
    			error();
    			break;
    		case 'V':
    			viewSubmissions();
    			break;
    		case 'B':
    			done = true;
    			break;
    		}
    		System.out.println();
    	} while (!done);
    }
	
    /**
     * Adding new material by Admin.
     */
    public static void addNewMaterial() {
    	String name = getText("Material name: ");
    	String description = getText("Description: ");
    	System.out.print("Points earned per Kg: ");
    	int pointsPerKg = kbd.nextInt();
    	ecoSave.addMaterial(new Material(name, description, pointsPerKg));
    	kbd.nextLine();
    	System.out.println("Addition success!");
    	System.out.println();
    }
    
	/**
	 * Display all the material in the EcoSave System
	 */
	public static void displayMaterials() {
		System.out.println("\nList of material(s)");
		System.out.println("~~~~~~~~~~~~~~~~~~~");
		System.out.println(ecoSave.allMaterials());
	}
	
    /**
     * Method that get a material based on its ID
     * @param prompt String indicating what is the material for
     * @return The material that matched id entered by user, null
     * if id entered is not valid.
     */
    private static Material getMaterial(String prompt) {
    	displayMaterials();
    	System.out.print(prompt);
    	int materialID = kbd.nextInt();
    	kbd.nextLine();
    	Material material = ecoSave.findMaterial(materialID);
    	return material;
    }
    
    /**
     * Update an existing material.
     */
    public static void updateMaterial() {
		if (ecoSave.numOfMaterials() == 0) {
			System.out.println("No materials has been added yet.");
			return ;
		}
    	Material material = getMaterial("Enter material ID to update: ");
		if (material == null)
			System.out.println("Invalid material ID!");
		else {
			System.out.print("Enter new material name (<Enter> to skip): ");
			String newName = kbd.nextLine();
			if (!newName.isEmpty())
				material.setMaterialName(newName);
			System.out.print("Enter new description (<Enter> to skip): ");
			String newDescription = kbd.nextLine();
			if (!newDescription.isEmpty())
				material.setDescription(newDescription);
			System.out.print("Enter new points per kg (<Enter> to skip): ");
			String newPointString = kbd.nextLine();
			if (!newPointString.isEmpty())
				material.setPointsPerKg(Integer.parseInt(newPointString));
			
			if (newName.isEmpty() && newDescription.isEmpty() && 
				newPointString.isEmpty())
				System.out.println("Update aborted....");
			else
				System.out.println("Successfully updated...");
    	}
    }
    
    /**
     * Delete an existing material.
     * This action will not affect previously stored submissions.
     */
    public static void deleteMaterial() {
		if (ecoSave.numOfMaterials() == 0) {
			System.out.println("No materials has been added yet.");
			return ;
		}
    	Material material = getMaterial("Enter material ID to delete: ");
    	if (material == null)
    		System.out.println("Invalid material ID!");
    	else {
    		System.out.println("Detail of material:");
    		System.out.println(material);

    		char answer = ' ';
    		do {
    			String response = getText("Confirm to delete (Y/N)" );
    			answer = response.toUpperCase().charAt(0);
    			if (answer != 'Y' && answer != 'N')
    				System.out.println("Enter 'Y' or 'N' only ");
    		} while (answer != 'Y' && answer != 'N');
    		
    		if (answer == 'Y') {
    			ecoSave.removeMaterial(material);
    			System.out.println("Successfully deleted...");
    		}
    		else
    			System.out.println("Deletion aborted...");
    	}
    }
    
    public static ArrayList<Submission> sortSubmissions(
    	ArrayList<Submission> submissions) {
    	char choice = 'O';
		do {
    		String response = getText("Display submission in\n" +
    			"<O>riginal order, <A>ctual Date or <S>tatus? ");
    		choice = response.toUpperCase().charAt(0);
    		if (choice == 'A')
    			if (!allHasSubmitted(submissions)) {
    				System.out.println("Not all submissions have been " + 
    					"submitted yet!\nSorting according to Actual " + 
    					"Date cannot be done! Try another option.");
    				choice = ' ';
    			}
		} while ("OAS".indexOf(choice) == -1);
		
		switch (choice) {
		case 'A':
			Collections.sort(submissions, new ActualDateComparator());
			break;
		case 'S':
			Collections.sort(submissions, new StatusProposedDateComparator());
			break;
		}
		return submissions;
    }
    
    /**
     * Check whether all submissions have been submitted
     * @return True if yes, false otherwise
     */
    public static boolean allHasSubmitted(ArrayList<Submission> submissions) {
    	for (Submission subm: submissions)
    		if (subm.getStatus().equalsIgnoreCase("Proposed"))
    			return false;
    	return true;
    }
    
    /**
     * Display submissions, together with the total weight and 
     * total points for the submissions
     * @param submissions List of submissions
     */
    public static void displaySubmissions(
    	ArrayList<Submission> submissions) {	
    	System.out.println("List of submissions");
    	double totalWeight = 0.0;
    	int totalPoints = 0;
    	for (Submission s: submissions) {
    		System.out.println(s);
    		if (s.getStatus().equalsIgnoreCase("Submitted")) {
    			totalWeight += s.getWeightInKg();
    			totalPoints += s.getPointsAwarded();
    		}
    	}
    	if (totalWeight != 0.0) {
    		System.out.print("Total weight: " + totalWeight);
    		System.out.println(" kg. Total Points: " + totalPoints);
    	}
    }
    
    /**
     * Viewing submission history.
     * List of submissions displayed depends on the type of 
     * login user.
     */
    public static void viewSubmissions() {
		if (ecoSave.numOfMaterials() == 0) {
			System.out.println("No material has been added yet.");
			return ;
		}
    	Material material = getMaterial("Enter material ID to view history: ");
    	if (material == null) {
    		System.out.println("Invalid material ID!");
    		return ;
    	}    	
		if ((loginUser instanceof Collector) &&
			!((Collector) loginUser).hasMaterial(material)) {
			System.out.println("Collector do not collect this type of material!");
			return ;
		}
    	ArrayList<Submission> allSubmissions = material.getSubmissions();   	
    	if (allSubmissions.isEmpty()) {
    		System.out.println("No submission for this material yet...");
    		return ;
    	}
    	if (loginUser instanceof Admin) {
    		ArrayList<Submission> allSubmissionsClone = 
    			(ArrayList<Submission>) allSubmissions.clone();
        	sortSubmissions(allSubmissionsClone);  	
        	displaySubmissions(allSubmissionsClone);
    	}
    	else if (loginUser instanceof Collector) {
    		Collector col = (Collector) loginUser;
    		ArrayList<Submission> collectorSubmission = 
    			new ArrayList<Submission>();
    		for (Submission s: allSubmissions) {
    			if (s.getCollector().equals(col))
    				collectorSubmission.add(s);
    		}  		
    		if (collectorSubmission.isEmpty()) {
    			System.out.println("No submission on this material!");
    			return ;
    		}
    		sortSubmissions(collectorSubmission);   		
    		displaySubmissions(collectorSubmission);
    	}    	
    	else if (loginUser instanceof Recycler) {
    		Recycler r = (Recycler) loginUser;
    		ArrayList<Submission> recyclerSubmission = 
    			new ArrayList<Submission>();
    		for (Submission s: allSubmissions) {
    			if (s.getRecycler().equals(r))
    				recyclerSubmission.add(s);
    		}   		
    		if (recyclerSubmission.isEmpty()) {
    			System.out.println("No submission on this material!");
    			return ;
    		}
    		sortSubmissions(recyclerSubmission);
    		displaySubmissions(recyclerSubmission);
    	}
    }
/////////////// End of Admin Tasks //////////////////////////////////

    /**
     * Presenting the options that a Collector can do
     */
    public static char collectorMenu() {
		System.out.println("Green Green Grass Of Home - " +
			loginUser.getFullName() + " with points: " +
			((Collector) loginUser).getTotalPoints());
		System.out.println("Press");
		System.out.println("F - Update full name");
		System.out.println("P - Update password");
		System.out.println("A - Add material");
		System.out.println("C - Collect material");
		System.out.println("V - View submission history");
		System.out.println("B - Back to main");
		System.out.println();
		String response = getText("Your choice? ");
		char choice = response.toUpperCase().charAt(0);
		return choice;    	
    }
    
    /**
     * Invoking the appropriate task that a Collector has selected.
     */
    public static void collectorTasks() {
    	boolean done = false;
    	do {    		
    		char option = collectorMenu();
    		switch (option) {
    		case 'F':
    			updateFullName(loginUser.getFullName());
    			break;
    		case 'P':
    			updatePassword(loginUser.getPassword());
    			break;
    		case 'A':
    			addMaterial();
    			break;
    		case 'C':
    			collectMaterial();
    			break;
    		case 'V':
    			viewSubmissions();
    			break;
    		default:
    			error();
    			break;
    		case 'B':
    			done = true;
    			break;
    		}
    		System.out.println();
    	} while (!done);
    }
   
    /**
     * Adding new material to be collected by a Collector.
     * Does not allow duplicate material.
     */
    public static void addMaterial() {
		if (ecoSave.numOfMaterials() == 0) {
			System.out.println("No material has been added yet.");
			return ;
		}    	
    	Material material = getMaterial("Enter material ID to add: ");
    	if (material == null)
    		System.out.println("Invalid material ID!");
    	else {
    		if (((Collector) loginUser).hasMaterial(material))
    			System.out.println("Duplicate material... Addition failed.");
    		else {
	    		System.out.println("Detail of material:");
	    		System.out.println(material);
	    		ecoSave.addMaterialToCollector((Collector) loginUser, material);
    		}
    	}
    }
    
    /**
     * Get material to be submitted by recycler, who
     * does not appointment or wrong material type for submission
     * @return material Material to be submitted for recycling by recycler o
     * null if invalid
     */
    private static Material getAdhocMaterial() {
    	Material material = getMaterial("Enter material ID to add: ");
    	if (material == null) {
    		System.out.println("Invalid material ID!");
    		System.out.println("Try again!");
    		return null;
    	}
    	else if (!((Collector) loginUser).hasMaterial(material)) {
    		System.out.println("Collector does not " + 
    			"collect this material!");
    		return null;
    	}
    	return material;
    }
    
    /**
     * Adding/Collecting material submitted by a Recycler, 
     * who does not make appointment for submission.
     * Invoked from collectMaterial.
     * @param recycler The Recycler who will submit recycling items.
     */
    private static void addAdhocMaterial(Recycler recycler) {
    	Material material = getAdhocMaterial();
    	if (material != null) {
    		Submission submission = new Submission(material,
    			(Collector) loginUser,recycler, LocalDate.now());
    		processSubmission(submission); 		
    	}
    }

    /**
     * Complete process a Submission object by setting appropriate
     * values for remaining attributes.
     */
    public static void processSubmission(Submission submission) {
		submission.setActualDate(LocalDate.now());
		System.out.print("Enter the weight, in kg: ");
		double weightInKg = kbd.nextDouble();
		kbd.nextLine();
		submission.setWeightInKg(weightInKg);
		submission.setStatus("Submitted");
		loginUser.updatePoints(
			submission.getPointsAwarded());
		submission.getRecycler().updatePoints(
			submission.getPointsAwarded());
    }
    
    /**
     * Collect material submitted by a Recycler, who has make an
     * appointment for submission.
     * If Recycler does not make any appointment, then invoke 
     * addAdhocMaterial for performing the collection.
     */
    public static void collectMaterial() {
		if (ecoSave.numOfMaterials() == 0) {
			System.out.println("No material has been added yet.");
			return ;
		}    	
    	String username = getText("Recycler's name: ");
    	User user = ecoSave.findUser(username);

    	if (user == null) {
    		System.out.println("No recyler with username: '" + 
    			username + "'");
    		return ;
    	}
    	if (!(user instanceof Recycler)) {
    		System.out.println("User is not a Recycler!");
    		return ;
    	}
    	Recycler recycler = (Recycler) user;
    	ArrayList<Submission> submissions = 
    		((Collector) loginUser).findSubmissionsFor(recycler);
 
    	if (submissions.isEmpty()) {
    		System.out.println("The user does not have any submissions");
    		addAdhocMaterial(recycler);
    	}
    	else {
    		System.out.println("Lists of submissions:");
    		for (Submission s: submissions)
    			System.out.println(s);
    		System.out.println();
    		System.out.print("Enter submission id: ");
    		int subID = kbd.nextInt();
    		kbd.nextLine();
    		boolean validID = validateID(subID, submissions);
    		if (!validID) {
    			System.out.println("Invalid submission ID!");
    			return ;
    		}

    		Submission submission = ecoSave.findSubmission(subID);
    		System.out.println(submission.getProposedDate() + " " + 
    			submission.getMaterial().getMaterialName() + " : " + 
    			submission.getStatus());
    		if (submission.getStatus().equals("Submitted"))
    			System.out.println("The chosen submission have " + 
    				"already been submitted!");
    		else {
    			Material material = submission.getMaterial();
    			String response = getText("Confirm submit '" +
    				material.getMaterialName() + "'? (Y/N) ");
    			char choice = response.trim().toUpperCase().charAt(0);
    			if (choice == 'N') {
    				material = getAdhocMaterial();
    				if (material != null)
    		    		submission.setMaterial(material);
    			}
    			processSubmission(submission);
    		}
    	}
    }

    /**
     * Validate the submission id in the corresponding submission list.
     * Used in collectMaterial
     * @param subID Submission id entered by user
     * @param submissions List of submissions.
     * @return true if submission id is valid, false otherwise
     */
    public static boolean validateID(int subID, ArrayList<Submission> submissions) {
    	for (Submission s: submissions)
    		if (s.getSubmissionID() == subID)
    			return true;
    	return false;
    }
/////////////// End of Collector Tasks //////////////////////////////////

    /**
     * Presenting the options that a Recycler can do
     */
    public static char recyclerMenu() {
		System.out.println("Green Green Grass Of Home - " +
			loginUser.getFullName() + " (" +
			((Recycler) loginUser).getEcoLevel().inWord() + 
			") with points " + ((Recycler) loginUser).getTotalPoints());
		System.out.println("Press");
		System.out.println("F - Update full name");
		System.out.println("P - Update password");
		System.out.println("R - Recycle material");
		System.out.println("V - View submission history");
		System.out.println("B - Back to main");
		System.out.println();
		String response = getText("Your choice? ");
		char choice = response.toUpperCase().charAt(0);
		return choice;    	
    }
    
    /**
     * Invoking the appropriate task that a Recycler has selected.
     */
    public static void recyclerTasks() {   	
    	boolean done = false;
    	do {    		
    		char option = recyclerMenu();
    		switch (option) {
    		case 'F':
    			updateFullName(loginUser.getFullName());
    			break;
    		case 'P':
    			updatePassword(loginUser.getPassword());
    			break;
    		case 'R':
    			recycleItems();
    			break;
    		case 'V':
    			viewSubmissions();
    			break;
    		default:
    			error();
    			break;
    		case 'B':
    			done = true;
    			break;
    		}
    		System.out.println();
    	} while (!done);
    }
    
    /**
     * Recycler choose what material to be recycled, and 
     * select a Collector, make an appointment on a proposed
     * date for submission.
     */
    public static void recycleItems() {
		if (ecoSave.numOfMaterials() == 0) {
			System.out.println("No materials has been added yet.");
			return ;
		}
    	Material material = getMaterial("Enter material ID to recycle: ");
    	if (material == null)
    		System.out.println("Invalid material ID!");
    	else {
    		if (material.numOfCollectors() == 0) {
    			System.out.println("No collector has signed up for this material yet!");
    			return ;
    		}
    		Collector collector = null;
    		boolean done = false;
    		String collectorNames = material.getCollectorsInfo();
    		do {
	    		System.out.println("Collector(s) of this material:");
	    		System.out.println(collectorNames);
	    		String collectorName = getText("Enter collector's username: ");
	    		collector = (Collector) ecoSave.findUser(collectorName);
	    		if (collector  == null)
	    			System.out.println("Wrong username!\n");
	    		else if (!collector.hasMaterial(material))
	    			System.out.println("This collector does not receive '" +
	    				material.getMaterialName() + "'!\n");
	    		else
	    			done = true;
    		} while (!done); 
    		LocalDate proposedDate = null;
    		do {
    			String proposedDateStr = getText("Proposed date to submit: (dd/mm/yyyy) ");
    			proposedDate = LocalDate.parse(proposedDateStr, formatter);
    		} while (proposedDate == null);
    		
    		Submission submission = new Submission(material, collector, 
    			(Recycler) loginUser, proposedDate);
    		System.out.println("New submission successfully added ...");
    		System.out.println("[" + submission + "]");
    	}
    }
/////////////// End of Recycler Tasks //////////////////////////////////

	/**
	 * Printing quit message
	 */
	public static void quit() {
		System.out.println("Thanks for using EcoSave...");
		System.out.println("See you...");
	}
	
	/**
	 * Printing invalid choice.
	 */
	public static void error() {
		System.out.println("Invalid choice! Try again...");
	}

    // The following methods are used in SignUp for getting user input, 
    // and also performing validation checks
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    /**
     * Get a valid username
     * @return String username
     */
    public static String getUsername() {
    	String username = getText("Username: ");
    	while (ecoSave.findUser(username) != null) {
            System.out.println("Username already exists. "
                    + "Please enter another username!\n");
            username = getText("Username: ");
    	}
    	return username;
    }
   
    /**
     * Update password.
     * @param currentPassword Existing password
     */
    public static void updatePassword(String currentPassword) {
    	String newPassword = getText("New password: ");
    	if (newPassword.equals(currentPassword))
    		System.out.println("Password is the same. Not updated!");
    	else {
    		loginUser.setPassword(newPassword);
    		System.out.println("Password successfully updated... ");
    	}
    }
    
    /**
     * Update full name
     * @param currentFullName Existing full name.
     */
    public static void updateFullName(String currentFullName) {
    	String newFullName = getText("New full name: ");
    	if (newFullName.equalsIgnoreCase(currentFullName))
    		System.out.println("Full name is the same. Not updated!");
    	else {
    		loginUser.setFullName(newFullName);
    		System.out.println("Full name successfully updated... ");
    	}
    }

    /**
     * Getting text input from user, with validation for empty string. 
     * Value of input depends on the prompt.
     * @param prompt The prompt showing what information is
     * being read.
     * @return text The textual value entered by user.
     */
    public static String getText(String prompt) {
        System.out.print(prompt);
        String text = kbd.nextLine().trim();
        while (text.equals("")) {
            System.out.println("Entry cannot be blank! "
                    + "Please enter again!\n");
            System.out.print(prompt);
            text = kbd.nextLine();
        }
        return text;
    }
    
    /**
     * Method to initialise some predefined data.
     */
	private static void initialised() {
		ecoSave.addUser(new Admin("admin", "admin", "Administrator"));
		ecoSave.addMaterial(new Material("glass", "glass", 100));
		ecoSave.addMaterial(new Material("paper", "newspaper", 50));
		ecoSave.addMaterial(new Material("aluminium", "aluminium", 200));
		ecoSave.addUser(new Collector("james", "james", "James", "1, Jalan 2"));
		ecoSave.addUser(new Recycler("ace", "ace", "Ace"));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("james"), ecoSave.findMaterial(1));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("james"), ecoSave.findMaterial(3));
		ecoSave.addUser(new Collector("spiderman", "spiderman", "Spiderman", "2, Jalan 3"));
		ecoSave.addUser(new Recycler("bbc", "bbc", "Bbc"));
		ecoSave.addUser(new Collector("superman", "superman", "Superman", "3, Jalan 4"));
		ecoSave.addUser(new Recycler("abc", "abc", "Abc"));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("spiderman"), ecoSave.findMaterial(1));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("spiderman"), ecoSave.findMaterial(2));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("superman"), ecoSave.findMaterial(2));
		ecoSave.addMaterialToCollector(
				(Collector) ecoSave.findUser("superman"), ecoSave.findMaterial(3));
	}    
}