package dkeep.logic;

public abstract class Unit implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
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
