package dkeep.logic;

/**
 * 
 * @author José Freixo and Ruben Torres
 *
 */
public abstract class Unit implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	CellPosition posicao;
	char representation;

	/**
	 * 
	 * @param pos The position where the unit will start on.
	 * @param rep The representation of the unit.
	 */
	public Unit(int [] pos, char rep){
		posicao = new CellPosition(pos[0], pos[1]);
		representation = rep;
	}

	/**
	 * 
	 * @return char Returns the unit's representation.
	 */
	public char getRepresentation() {
		return representation;
	}

	/**
	 * 
	 * @param representation The new representation of the unit.
	 */
	public void setRepresentation(char representation) {
		this.representation = representation;
	}

	/**
	 * 
	 * @param y The new coordinate in the y axis.
	 * @param x The new coordinate in the x axis.
	 */
	public void setPosition(int y, int x){
		posicao.setCellPosition(y, x);
	}

	/**
	 * 
	 * @return CellPosition Returns the position of the unit.
	 */
	public CellPosition getPosition(){
		return posicao;
	}
}
