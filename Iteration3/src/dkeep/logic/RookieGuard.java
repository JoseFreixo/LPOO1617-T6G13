package dkeep.logic;

public class RookieGuard extends Guard {
	char [] movement = {'A', 
			'S', 'S', 'S', 'S', 
			'A', 'A', 'A', 'A', 'A', 'A', 
			'S', 
			'D', 'D', 'D', 'D', 'D', 'D', 'D', 
			'W', 'W', 'W', 'W', 'W'};
	
	public RookieGuard(int y, int x) {
		super(y, x);
	}
}
