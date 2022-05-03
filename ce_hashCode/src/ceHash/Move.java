package ceHash;

/**
 * The enum Move allows for two possible choices and provides
 * a character representation for each of the choices.
 * 
 * @author CSIS 2420 Starter Code
 *
 */
public enum Move {
	LEFT, RIGHT;
	
	/**
	 * Returns a character representation of the enum constant.
	 */
	public char asChar() {
		if(this == LEFT)
			return 'L';
		else 
			return 'R';
	}
}


