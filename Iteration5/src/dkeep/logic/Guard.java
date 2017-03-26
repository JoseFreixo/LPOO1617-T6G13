package dkeep.logic;

import java.util.Random;

public class Guard extends Unit implements java.io.Serializable {

	private static final long serialVersionUID = 6L;
	int type; // 0 -> Rookie | 1 -> Drunken | 2 -> Suspicious
	int timeOut;
	boolean front;
	char [] movement = {'A', 
			'S', 'S', 'S', 'S', 
			'A', 'A', 'A', 'A', 'A', 'A', 
			'S', 
			'D', 'D', 'D', 'D', 'D', 'D', 'D', 
			'W', 'W', 'W', 'W', 'W'};

	/**
	 * 
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
	 * 
	 * @return boolean Returns true if the Guard is following his path on it's initial direction, false if otherwise.
	 */
	public boolean isFront() {
		return front;
	}

	/**
	 * 
	 * @param front The new direction that the guard is going to take from that point on.
	 */
	public void setFront(boolean front) {
		this.front = front;
	}

	/**
	 * 
	 * @param i The new guard's personality.
	 */
	public void setType(int i){
		type = i;
	}

	/**
	 * 
	 * @return int Returns the turns that the guard has to wait to wake up.
	 */
	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public int getType() {
		return type;
	}

	public char [] getMovement(){
		return movement;
	}

	public void setGuardDirection(){
		Random random = new Random();
		int dir = random.nextInt(2);
		if (dir == 0)
			front = true;
		else
			front = false;
	}
}
