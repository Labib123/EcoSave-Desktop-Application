package ecosaveGui;

import java.util.Comparator;

/**
 * A class used to order the Submission objects according
 * to status, followed by Proposed Date of submission.
 * @author KokChyeHock
 *
 */
public class StatusProposedDateComparator 
	implements Comparator<Submission> {
	
	/**
	 * Comparing Submission objects according to Status (of type String),
	 * followed by Proposed Date of submission.
	 * @return positive if lhs is alphabetically 'larger' than rhs, 
	 * negative if lhs is alphabetically 'smaller' than rhs, if equal,
	 * then the criteria is based on Proposed Date. Return positive if
	 * lhs's Proposed Date is 'earlier' than rhs's Proposed Date, negative
	 * if lhs's Proposed Date is 'laer' than rhs's Proposed Date, zero 
	 * otherwise. 
	 */
	public int compare(Submission lhs, Submission rhs) {
		int firstCriteria = lhs.getStatus().compareTo(rhs.getStatus());
		if (firstCriteria != 0)
			return firstCriteria;
		return lhs.getProposedDate().compareTo(rhs.getProposedDate());
	}

}
