package dkeep.logic;

/**
 * This class generates the hero.
 * @author Jose Freixo and Ruben Torres
 *
 */
public class Hero extends Unit implements java.io.Serializable {

	private static final long serialVersionUID = 5L;

	/**
	 * Constructor of the class Hero.
	 * @param pos
	 * @param rep
	 */
	public Hero(int [] pos, char rep) {
		super(pos, rep);
	}

}
