package dkeep.logic;

public class GameMap {
	char[][] map;
	
	public GameMap(char[][] map){
		this.map = map;
	}
	
	public char[][] getMap(){
		return map;
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

	public void openDoors(boolean isKey) {

	}
	
}
