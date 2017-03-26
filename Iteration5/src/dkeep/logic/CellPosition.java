package dkeep.logic;

/**
 * 
 * @author José Freixo and Ruben Torres
 *
 */
public class CellPosition implements java.io.Serializable {

	private static final long serialVersionUID = 7L;
	int x;
	int y;

	/**
	 * 
	 * @param y The coordinate of the position in the y axis.
	 * @param x The coordinate of the position in the x axis.
	 */
	public CellPosition(int y, int x){
		this.y = y;
		this.x = x;
	}

	/**
	 * 
	 * @param y The new coordinate in the y axis.
	 * @param x The new coordinate in the x axis.
	 */
	public void setCellPosition(int y, int x){
		this.y = y;
		this.x = x;
	}

	/**
	 * 
	 * @return int Returns the coordinate x of the position.
	 */
	public int getX(){
		return x;
	}

	/**
	 * 
	 * @return int Returns the coordinate y of the position.
	 */
	public int getY(){
		return y;
	}

	
	@Override
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
