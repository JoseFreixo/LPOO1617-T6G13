package dkeep.saveLoadMaps;

import dkeep.logic.GameMap;

public class KeepMap{
	
	private static char[][] map = {{'X','X','X','X','X','X','X','X','X'},
	 		{'I',' ',' ',' ','O',' ',' ','k','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X',' ',' ',' ',' ',' ',' ',' ','X'},
	 		{'X','A',' ',' ',' ',' ',' ',' ','X'},
	 		{'X','X','X','X','X','X','X','X','X'}};
	
	public static GameMap getKeepMap() {
		return new GameMap(map);
	}
}
