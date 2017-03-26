package dkeep.logic;

/**
 * This class stores the position of all units.
 * @author Jose Freixo and Ruben Torres
 *
 */
public class CellPosition implements java.io.Serializable {

	private static final long serialVersionUID = 7L;
	private int x;
	private int y;

	/**
	 * Constructor of the class CellPosition.
	 * @param y The coordinate of the position in the y axis.
	 * @param x The coordinate of the position in the x axis.
	 */
	public CellPosition(int y, int x){
		this.y = y;
		this.x = x;
	}

	/**
	 * This method changes the coordinates of the position.
	 * @param y The new coordinate in the y axis.
	 * @param x The new coordinate in the x axis.
	 */
	public void setCellPosition(int y, int x){
		this.y = y;
		this.x = x;
	}

	/**
	 * This method returns the coordinate x of the position.
	 * @return int Returns the coordinate x of the position.
	 */
	public int getX(){
		return x;
	}

	/**
	 * This method returns the coordinate y of the position.
	 * @return int Returns the coordinate y of the position.
	 */
	public int getY(){
		return y;
	}

	
	@Override
	/**
	 * This method compares the current CellPosition with it's argument.
	 * @param obj The position that needs to be compared.
	 * @return boolean Returns true if the current CellPosition is the same as the argument's, false if otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CellPosition)) {
			return false;
		}
		CellPosition other = (CellPosition) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
}
