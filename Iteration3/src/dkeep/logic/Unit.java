package dkeep.logic;

public abstract class Unit {
	CellPosition posicao;
	
	public Unit(int y, int x){
		posicao = new CellPosition(y, x);
	}
	
	public void setPosition(int y, int x){
		posicao.setCellPosition(y, x);
	}
	
	public CellPosition getPosition(){
		return posicao;
	}
}
