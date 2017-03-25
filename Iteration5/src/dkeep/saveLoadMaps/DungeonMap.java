package dkeep.saveLoadMaps;

import dkeep.logic.GameMap;

public class DungeonMap {

	private static char[][] map = {{'X','X','X','X','X','X','X','X','X','X'},
			 {'X','H',' ',' ','I',' ','X',' ','G','X'},
			 {'X','X','X',' ','X','X','X',' ',' ','X'},
			 {'X',' ','I',' ','I',' ','X',' ',' ','X'},
			 {'X','X','X',' ','X','X','X',' ',' ','X'},
			 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			 {'X','X','X',' ','X','X','X','X',' ','X'},
			 {'X',' ','I',' ','I',' ','X','k',' ','X'},
			 {'X','X','X','X','X','X','X','X','X','X'}};
	
	public static GameMap getDungeonMap() {
		return new GameMap(map);
	}

}
