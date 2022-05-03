package ceHash;

/**
 * MoveCounter keeps track of how often left and right have been incremented.
 * 
 * @author CSIS 2420 Starter Code
 *
 */
public class MoveCounter {
	int left;    // counts how often Choice.LEFT has been incremented
	int right;   // counts how often Choice.RIGHT has been incremented
	
	public int left() {
		return left;
	}
	public int right() {
		return right;
	}
	
	/**
	 * Increases the field corresponding to the move <code>m</code> by one.
	 * 
	 * @param m 
	 */
	public void increment(Move m) {
		if (m == null)
			throw new NullPointerException("The leftRightCounter doesn't count null.");
			
		if (m == Move.LEFT)
			left++;
		else
			right++;
	}

}
