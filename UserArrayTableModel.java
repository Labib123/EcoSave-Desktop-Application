package ecosaveGui;



import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class UserArrayTableModel extends AbstractTableModel{

	//Array to display in table
	private ArrayList<User> users;
	//Names of columns
	private String[] columnHeader = {"Username", "Password", 
			"Full Name", "Total Points", "Eco-Level", "Address", "Type"};

	public UserArrayTableModel(ArrayList<User> users) {
		this.users = users;
	}
	//Creating Table
	public int getRowCount() {
		return getUsers().size();
	}
	public int getColumnCount() {
		return columnHeader.length;
	}
	public Object getValueAt(int row, int column) {
		User v = (User) getUsers().get(row);
		switch (column)
		{
		case 0:
			return v.getUsername();
		case 1:
			return v.getPassword();
		case 2:
			return v.getFullName();
		case 3:
			if (v instanceof Recycler || v instanceof Collector)
				return v.getTotalPoints();
			else 
				return "N/A";
		case 4:
			if (v instanceof Recycler)
				return ((Recycler) v).getEcoLevel();
			else 
				return "N/A";
		case 5:
			if (v instanceof Collector)
				return ((Collector) v).getAddress();
			else 
				return "N/A";
		case 6:
			if (v instanceof Recycler)
				return "Recycler";
			else if (v instanceof Collector)
				return "Collector";
			else
				return "Admin";

		default:
			return "";

		}
	}


	public String getColumnName(int column)
	{
		return columnHeader[column];
	}

	/** Getter
	 * @return the users object
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	/** Setter
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/**
	 * method to add user
	 * @param user
	 */
	public void add(User user)
	{

		if (user instanceof Recycler)
			getUsers().add((Recycler) user);
		else if (user instanceof Collector)
			getUsers().add((Collector) user);
		else
			getUsers().add((Admin) user);

		fireTableDataChanged();
	}
}
