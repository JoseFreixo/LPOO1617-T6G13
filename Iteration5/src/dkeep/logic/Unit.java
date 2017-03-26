package dkeep.logic;

public abstract class Unit implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	CellPosition posicao;
	char representation;

	public Unit(int [] pos, char rep){
		posicao = new CellPosition(pos[0], pos[1]);
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
