package dkeep.logic;

import java.util.Random;

public class Guard extends Unit {
	int type; // 0 -> Rookie | 1 -> Drunken | 2 -> Suspicious
	
	public Guard(int y, int x) {
		super(y, x);
		Random rand = new Random();
		type = rand.nextInt(3);
	}
	
	
}
