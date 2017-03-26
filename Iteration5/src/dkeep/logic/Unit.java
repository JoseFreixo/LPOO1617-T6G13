package dkeep.logic;

/**
 * This class handles everything that all entities have in common, such as position and representation.
 * @author José Freixo and Ruben Torres
 *
 */
public abstract class Unit implements java.io.Serializable {

	private static final long serialVersionUID = 2L;
	CellPosition posicao;
	char representation;

	/**
	 * Constructor of the class Unit.
	 * @param pos The position where the unit will start on.
	 * @param rep The representation of the unit.
	 */
	public Unit(int [] pos, char rep){
		posicao = new CellPosition(pos[0], pos[1]);
		representation = rep;
	}

	/**
	 * This method returns the unit's representation.
	 * @return char Returns the unit's representation.
	 */
	public char getRepresentation() {
		return representation;
	}

	/**
	 * This method changes the unit's representation.
	 * @param representation The new representation of the unit.
	 */
	public void setRepresentation(char representation) {
		this.representation = representation;
	}

	/**
	 * This method changes the unit's position.
	 * @param y The new coordinate in the y axis.
	 * @param x The new coordinate in the x axis.
	 */
	public void setPosition(int y, int x){
		posicao.setCellPosition(y, x);
	}

	/**
	 * This method returns the unit's position.
	 * @return CellPosition Returns the position of the unit.
	 */
	public CellPosition getPosition(){
		return posicao;
	}
}
