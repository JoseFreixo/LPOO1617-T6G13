package dkeep.logic;

public class Ogre extends Unit implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	int stunTimeout;
	Boolean OgreOnKey;
	Boolean swingedOnKey;
	CellPosition attack;

	public Boolean getOgreOnKey() {
		return OgreOnKey;
	}

	public void setOgreOnKey(Boolean ogreOnKey) {
		OgreOnKey = ogreOnKey;
	}


	public Boolean getSwingedOnKey() {
		return swingedOnKey;
	}

	public void setSwingedOnKey(Boolean swingedOnKey) {
		this.swingedOnKey = swingedOnKey;
	}

	public CellPosition getAttack() {
		return attack;
	}

	public void setAttack(CellPosition attack) {
		this.attack = attack;
	}

	public Ogre(int [] pos, char rep) {
		super(pos, rep);
		attack= new CellPosition(pos[0], pos[1]);
		OgreOnKey=false;
		swingedOnKey=false;
		stunTimeout = 0;
	}

	public int getStunTimeout() {
		return stunTimeout;
	}

	public void setStunTimeout(int stunTimeout) {
		this.stunTimeout = stunTimeout;
	}

}
