package dkeep.logic;

public class CellPosition {
	int [] position;
	
	public CellPosition(int y, int x){
		position[0] = y;
		position[1] = x;
	}
	
	public void setCellPosition(int y, int x){
		position[0] = y;
		position[1] = x;
	}
	
	public int [] getCellPosition(){
		return position;
	}
}
