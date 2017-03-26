package dkeep.logic;

/**
 * This class generates the Lever or Key.
 * @author Jose Freixo and Ruben Torres
 *
 */
public class Lever extends Unit implements java.io.Serializable{

	private static final long serialVersionUID = 4L;

	/**
	 * Constructor of the class Lever
	 * @param pos
	 * @param rep
	 */
	public Lever(int [] pos, char rep) {
		super(pos, rep);
	}
}
