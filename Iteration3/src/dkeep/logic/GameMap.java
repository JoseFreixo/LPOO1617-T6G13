package dkeep.logic;

import java.util.Arrays;

public class GameMap {
	char[][] map;
	
	public GameMap(char[][] map){
		this.map = map;
	}

	public char[][] getMap(){
		return map;
	}
	
	public char getCharOnPos(CellPosition Pos){
		return map[Pos.getY()][Pos.getX()];
	}
	
	public void setMap(char [][] map){
		this.map = map;
	}
	
	public boolean validPosition(int y, int x){
		if (map[y][x] == 'X' || map[y][x] == 'I')
			return false;
		return true;
	}
	
	public void setUnitPosMap(CellPosition position_to_set , CellPosition position_to_erase, char representation){
		map[position_to_erase.getY()][position_to_erase.getX()] = ' ';
		map[position_to_set.getY()][position_to_set.getX()] = representation;
	}

	public void openDoors(boolean isKey, CellPosition heroiPos) {
		if (isKey){
			int x = heroiPos.getX();
			int y = heroiPos.getY();
			if (y != 0 && map[y-1][x] == 'I'){
				map[y-1][x] = 'S';
				openDoors(isKey, new CellPosition(y - 1, x));
			}
			else if (y != map.length - 1 && map[y+1][x] == 'I'){
				map[y+1][x] = 'S';
				openDoors(isKey, new CellPosition(y + 1, x));
			}
			else if (x != 0 && map[y][x-1] == 'I'){
				map[y][x-1] = 'S';
				openDoors(isKey, new CellPosition(y, x - 1));
			}
		else if (x != map[y].length - 1 && map[y][x+1] == 'I'){
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

	public GameMap getNextLevel(){
		return new KeepMap();
	}
	
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
