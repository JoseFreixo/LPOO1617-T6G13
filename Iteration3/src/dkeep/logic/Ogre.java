package dkeep.logic;

public class Ogre extends Unit{
	Boolean OgreOnKey;
	
	public Boolean getOgreOnKey() {
		return OgreOnKey;
	}

	public void setOgreOnKey(Boolean ogreOnKey) {
		OgreOnKey = ogreOnKey;
	}

	public Ogre(int y, int x, char rep) {
		super(y, x, rep);
		OgreOnKey=false;
	}

}
