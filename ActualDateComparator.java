package ecosaveGui;

import java.util.Comparator;

/**
 * A class used to order the Submission objects according
 * to the Actual Date of submission.
 * @author KokChyeHock
 *
 */
public class ActualDateComparator implements Comparator<Submission> {
	/**
	 * Comparing Submisson objects according to Actual Date of submisson
	 * @return positive if lhs is 'later' than rhs, zero if both are
	 * equal, and negative if lhs is 'earlier' than rhs
	 */
	public int compare(Submission lhs, Submission rhs) {
		return lhs.getActualDate().compareTo(rhs.getActualDate());
	}
}
