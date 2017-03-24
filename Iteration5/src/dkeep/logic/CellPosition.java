package dkeep.logic;

public class CellPosition implements java.io.Serializable {

	private static final long serialVersionUID = 7L;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
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
