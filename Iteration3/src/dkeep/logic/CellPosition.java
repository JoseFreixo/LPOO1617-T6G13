package dkeep.logic;

public class CellPosition {
	int x;
	int y;
	
	public CellPosition(int y, int x){
		this.y = y;
		this.x = x;
	}
	
	public void setCellPosition(int y, int x){
		this.y = y;
		this.x = x;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
