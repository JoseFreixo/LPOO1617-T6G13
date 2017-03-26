package dkeep.logic;

/**
 * This class handles everything directly related to the Ogre.
 * @author Jose Freixo and Ruben Torres
 *
 */
public class Ogre extends Unit implements java.io.Serializable{
	
	private static final long serialVersionUID = 3L;
	private int stunTimeout;
	private Boolean OgreOnKey;
	private Boolean swingedOnKey;
	private CellPosition attack;

	/**
	 * This method tells if the ogre is above the key or not.
	 * @return Boolean Returns true if the ogre is above the key, false if otherwise.
	 */
	public Boolean getOgreOnKey() {
		return OgreOnKey;
	}

	/**
	 * This method changes the status of the ogre being above the key or not.
	 * @param ogreOnKey True if the ogre is above the key, false if not.
	 */
	public void setOgreOnKey(Boolean ogreOnKey) {
		OgreOnKey = ogreOnKey;
	}

	/**
	 * This method returns true if the ogre swinged above the key, false if otherwise.
	 * @return Boolean Returns true if the ogre swinged above the key, false if otherwise.
	 */
	public Boolean getSwingedOnKey() {
		return swingedOnKey;
	}

	/**
	 * This method changes the status of the swing being above the key or not.
	 * @param swingedOnKey The current status of the swing being above the key.
	 */
	public void setSwingedOnKey(Boolean swingedOnKey) {
		this.swingedOnKey = swingedOnKey;
	}

	/**
	 * This method returns the ogre's last attack.
	 * @return CellPosition Returns the position of the ogre's last attack.
	 */
	public CellPosition getAttack() {
		return attack;
	}

	/**
	 * This method changes the position of the attack.
	 * @param attack The new position of the attack.
	 */
	public void setAttack(CellPosition attack) {
		this.attack = attack;
	}

	/**
	 * Constructor of class Ogre. First attack is predefined on the ogre's initial position.
	 * @param pos The ogre's initial position.
	 * @param rep The ogre's initial representation.
	 */
	public Ogre(int [] pos, char rep) {
		super(pos, rep);
		attack = new CellPosition(pos[0], pos[1]);
		OgreOnKey=false;
		swingedOnKey=false;
		stunTimeout = 0;
	}

	/**
	 * This method returns the current wait timer until the ogre stops being stunned.
	 * @return int The current timer that the ogre has to wait to be able to move again.
	 */
	public int getStunTimeout() {
		return stunTimeout;
	}

	/**
	 * This method changes the stunned timeout to a specified value.
	 * @param stunTimeout The amout of turns that the ogre will have to wait to be able to move.
	 */
	public void setStunTimeout(int stunTimeout) {
		this.stunTimeout = stunTimeout;
	}

}
