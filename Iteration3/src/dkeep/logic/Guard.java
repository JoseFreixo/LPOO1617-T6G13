package dkeep.logic;

import java.util.Random;

public class Guard extends Unit {
	int type; // 0 -> Rookie | 1 -> Drunken | 2 -> Suspicious
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
	}
	
	
}
