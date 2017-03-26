package dkeep.logic;

import java.util.Random;

/**
 * This class handles everything directly related to the guards.
 * @author Jose Freixo and Ruben Torres
 *
 */
public class Guard extends Unit implements java.io.Serializable {

	private static final long serialVersionUID = 6L;
	private int type; // 0 -> Rookie | 1 -> Drunken | 2 -> Suspicious
	private int timeOut;
	private boolean front;
	private static char [] movement = {'A', 
			'S', 'S', 'S', 'S', 
			'A', 'A', 'A', 'A', 'A', 'A', 
			'S', 
			'D', 'D', 'D', 'D', 'D', 'D', 'D', 
			'W', 'W', 'W', 'W', 'W'};

	/**
	 * Constructor of the class Guard.
	 * @param pos The guard's starting position.
	 * @param rep The guard's initial representation.
	 * @param personality The guard's personality.
	 */
	public Guard(int [] pos, char rep, int personality) {
		super(pos, rep);
		type=personality;
		timeOut = 0;
		front = true;
	}

	/**
	 * This method returns the current direction that the guard is taking.
	 * @return boolean Returns true if the guard is following his path on it's initial direction, false if otherwise.
	 */
	public boolean isFront() {
		return front;
	}

	/**
	 * This method changes the direction that the guard is taking on the next move.
	 * @param front The new direction that the guard is going to take from that point on.
	 */
	public void setFront(boolean front) {
		this.front = front;
	}

	/**
	 * This method changes the guard's personality to a specified one.
	 * @param i The new guard's personality.
	 */
	public void setType(int i){
		type = i;
	}

	/**
	 * This method returns the turns that the guard has to wait to wake up.
	 * @return int Returns the turns that the guard has to wait to wake up.
	 */
	public int getTimeOut() {
		return timeOut;
	}

	/**
	 * This method changes the guard's timeOut timer to wake up to a specified amount.
	 * @param timeOut The new guard's timeOut to wake up.
	 */
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * This method returns the guard's personality number.
	 * @return int Returns the guard's personality.
	 */
	public int getType() {
		return type;
	}

	/**
	 * This method returns the array of movements of the guard.
	 * @return char [] Returns the array of movements of the guard.
	 */
	public char [] getMovement(){
		return movement;
	}

	/**
	 * Randomizes the guard's direction.
	 */
	public void setGuardDirection(){
		Random random = new Random();
		int dir = random.nextInt(2);
		if (dir == 0)
			front = true;
		else
			front = false;
	}
}
