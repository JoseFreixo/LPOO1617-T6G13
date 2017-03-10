package dkeep.logic;

public abstract class Unit {
	CellPosition posicao;
	char representation;
	
	public Unit(int y, int x, char rep){
		posicao = new CellPosition(y, x);
		representation = rep;
	}
	
	public char getRepresentation() {
		return representation;
	}

	public void setRepresentation(char representation) {
		this.representation = representation;
	}

	public void setPosition(int y, int x){
		posicao.setCellPosition(y, x);
	}
	
	public CellPosition getPosition(){
		return posicao;
	}
}
