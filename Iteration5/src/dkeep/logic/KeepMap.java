package dkeep.logic;

public class KeepMap extends GameMap{
	
	private static char[][] map = {{'X','X','X','X','X','X','X','X','X'},
	 		{'I',' ',' ',' ','O',' ',' ','k','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X','A',' ',' ',' ',' ',' ',' ','X'},
	 		{'X','X','X','X','X','X','X','X','X'}};
	
	public KeepMap() {
		super(map);
	}
}
