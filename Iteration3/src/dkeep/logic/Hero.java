package dkeep.logic;

public class Hero {
	CellPosition posicao;
	
	public Hero(int y, int x){
		posicao = new CellPosition(y, x);
	}
	
	public void setHeroPosition(int y, int x){
		posicao = new CellPosition(y, x);
	}
	
	public CellPosition getHeroPosition(){
		return posicao;
	}
}
