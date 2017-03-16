package dkeep.logic;

public class Ogre extends Unit{
	Boolean OgreOnKey;
	Boolean swingedOnKey;
	Club club;
	
	public Boolean getOgreOnKey() {
		return OgreOnKey;
	}

	public void setOgreOnKey(Boolean ogreOnKey) {
		OgreOnKey = ogreOnKey;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Boolean getSwingedOnKey() {
		return swingedOnKey;
	}

	public void setSwingedOnKey(Boolean swingedOnKey) {
		this.swingedOnKey = swingedOnKey;
	}

	public Ogre(int y, int x, char rep) {
		super(y, x, rep);
		OgreOnKey=false;
		club=null;
		swingedOnKey=false;
	}

}
