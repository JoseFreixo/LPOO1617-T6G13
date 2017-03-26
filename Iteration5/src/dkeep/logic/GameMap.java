package dkeep.logic;

import java.util.Arrays;

/**
 * Class used to store the map of the game
 * @author Jose Freixo and Ruben Torres
 *
 */
public class GameMap implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	char[][] map;
/**
 * Constructor of class GameMap, that stores a char[][] with the information of the map
 * @param map char[][] with the information
 */
	public GameMap(char[][] map){
		this.map = copyMap(map);
	}
	
/**
 * 
 * @return char[][] with the informations of the map
 */
	public char[][] getMap(){
		return map;
	}

	/**
	 * returns char that contains the information on a given position (as a parameter) of the map
	 * @param Pos Position you want on the map
	 * @return char that contains the information on a given position of the map
	 */
	public char getCharOnPos(CellPosition Pos){
		return map[Pos.getY()][Pos.getX()];
	}
/**
 * Set a given char on a given position of the map
 * @param Pos Position on the map
 * @param Representation Which Char u want to put on given position of the map
 */
	public void setCharOnPos(CellPosition Pos,char Representation){
		map[Pos.getY()][Pos.getX()]=Representation;
	}
	
/**
 * Checks if a given position on the map is a Wall or a closed door (which means you can't move to there)
 * @param y coordinate Y of a position of the map
 * @param x coordinate X of a position of the map
 * @return boolean as true if the positions is valid, false if not
 */
	public boolean validPosition(int y, int x){
		if (map[y][x] == 'X' || map[y][x] == 'I')
			return false;
		return true;
	}

	/**
	 * Copy a given char[][] to another in different memory position
	 * @param mapa map to copy
	 * @return char[][] that contains the map copied 
	 */
	public char [][] copyMap(char[][] mapa){
		char[][] copy = new char[mapa.length][mapa[0].length];
		for(int i=0;i<mapa.length;i++){
			for(int j=0; j<mapa[0].length;j++){
				copy[i][j]=mapa[i][j];
			}
		}
		return copy;
	}
	
/**
 * Set a char on a given position of the map(position_to_set) and 
 * erases a char (substitutes to ' ') also on a given position of the map (position_to_erase)
 * @param position_to_set position of the you want to set a char on
 * @param position_to_erase position of the map you want to erase
 * @param representation char to set on given position of the map
 */
	public void setUnitPosMap(CellPosition position_to_set , CellPosition position_to_erase, char representation){
		map[position_to_erase.getY()][position_to_erase.getX()] = ' ';
		map[position_to_set.getY()][position_to_set.getX()] = representation;
	}

/**
 * If the hero try to move against a closed the door with the key, 
 * this function opens that door and all the doors adjacent to it and themselves recursively
 * @param isKey is used to know if the map is a lever or a key map, if it's a lever this functions do nothing
 * @param heroiPos position of the Hero
 */
	public void openDoors(boolean isKey, CellPosition heroiPos) {

		if (isKey){
			int x = heroiPos.getX();
			int y = heroiPos.getY();
			if (y != 0 && map[y-1][x] == 'I'){
				map[y-1][x] = 'S';
				openDoors(isKey, new CellPosition(y - 1, x));
			}
			if (y != map.length - 1 && map[y+1][x] == 'I'){
				map[y+1][x] = 'S';
				openDoors(isKey, new CellPosition(y + 1, x));
			}
			if (x != 0 && map[y][x-1] == 'I'){
				map[y][x-1] = 'S';
				openDoors(isKey, new CellPosition(y, x - 1));
			}
			if (x != map[y].length - 1 && map[y][x+1] == 'I'){
				map[y][x+1] = 'S';
				openDoors(isKey, new CellPosition(y, x + 1));
			}
		}
		else{
			for (int i = 0; i < map.length; i++){
				for (int j = 0; j < map[i].length; j++){
					if (i == 0 || i == map.length - 1 || j == 0 || j == map[i].length - 1){
						if (map[i][j] == 'I')
							map[i][j] = 'S';
					}
				}
			}
		}
	}

	/**
	 * Functions used to see if a GameMap is equal to another
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameMap other = (GameMap) obj;
		if (!Arrays.deepEquals(map, other.map))
			return false;
		return true;
	}
}
