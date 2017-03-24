package dkeep.logic;


public class Guard extends Unit implements java.io.Serializable {
	/**
	 * 
	 */
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
		
	public Guard(int y, int x, char rep, int personalaty) {
		super(y, x, rep);
		type=personalaty;
		timeOut = 0;
		front = true;
	}
	
	public boolean isFront() {
		return front;
	}

	public void setFront(boolean front) {
		this.front = front;
	}

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
}
