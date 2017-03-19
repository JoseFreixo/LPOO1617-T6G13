package dkeep.logic;

import java.util.Random;

public class Guard extends Unit {
	int type; // 0 -> Rookie | 1 -> Drunken | 2 -> Suspicious
	int timeOut;
	boolean front;
	char [] movement = {'A', 
			'S', 'S', 'S', 'S', 
			'A', 'A', 'A', 'A', 'A', 'A', 
			'S', 
			'D', 'D', 'D', 'D', 'D', 'D', 'D', 
			'W', 'W', 'W', 'W', 'W'};
	
	public Guard(int y, int x, char rep) {
		super(y, x, rep);
		Random rand = new Random();
		type = rand.nextInt(3);
		timeOut = 0;
		front = true;
	}
	
	public Guard(int y, int x, char rep, String Personalaty) {
		super(y, x, rep);
		
		switch(Personalaty){
		case "Rookie":
			type = 0;
			break;
		case "Drunken":
			type = 0;
			break;
		case "Suspicious":
			type = 0;
			break;
		}

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
