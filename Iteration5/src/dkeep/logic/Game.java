package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class handles all of the game's logic.
 * @author José Freixo and Ruben Torres
 *
 */
public class Game implements java.io.Serializable {

	private static final long serialVersionUID = 8L;
	public static final boolean DEFEAT = true;
	Boolean Status;
	Boolean openDoors;
	Boolean isKey;
	boolean printKey = false;
	GameMap map;
	Hero heroi;
	Guard guarda;
	Lever lever;
	ArrayList<Ogre> ogres = new ArrayList<Ogre>();

	/**
	 * Constructor of class Game.
	 * @param map Map in which the game will occur.
	 * @param enemyTypes Personality of the guard and number of ogres.
	 */
	public Game(GameMap map, int [] enemyTypes){
		this.map = map;
		Status = null;
		openDoors = false;
		int xy[]= {0,0};
		for (int i=0;i<map.getMap().length;i++){
			for (int j=0; j<map.getMap()[i].length;j++){
				int [] pos = { i, j };
				createUnits(pos, enemyTypes[0],xy);
			}
		}
		if(isKey){
			heroi.setRepresentation('A');
			int [] pos = { xy[1], xy[0] };
			for (int i = 0; i < enemyTypes[1]; i++){
				ogres.add(new Ogre(pos, 'O'));
			}
		}
	}

	private void createUnits(int [] pos, int GuardType,int xy[]){
		switch(map.getMap()[pos[0]][pos[1]]){
		case 'H': heroi = new Hero(pos, 'H');
		break;
		case 'A': heroi = new Hero(pos, 'A');
		break;
		case 'G': 
			guarda = new Guard(pos, 'G', GuardType);
			isKey = false;
			break;
		case 'O':
			xy[0] = pos[1];
			xy[1] = pos[0];
			isKey = true;
			break;
		case 'k': lever = new Lever(pos, 'k');
		break;
		}
	}

	/**
	 * This method returns the lever/key's position
	 * @return CellPosition Returns the position of the lever/key.
	 */
	public CellPosition getLeverPosition(){
		return lever.getPosition();
	}

	/**
	 * This method returns the hero's position
	 * @return CellPosition Returns the position of the hero.
	 */
	public CellPosition getHeroPosition(){
		return heroi.getPosition();
	}

	/**
	 * This method returns the guard's position
	 * @return CellPosition Returns the position of the guard.
	 */
	public CellPosition getGuardPosition(){
		return guarda.getPosition();
	}

	/**
	 * This method returns the hero's representation.
	 * @return char Returns the representation of the hero.
	 */
	public char getHeroRepresentation(){
		return heroi.getRepresentation();
	}

	/**
	 * This method returns the guard's representation.
	 * @return char Returns the representation of the guard.
	 */
	public char getGuardRepresentation(){
		return guarda.getRepresentation();
	}

	/**
	 * This method returns a specific element of the map.
	 * @param pos The position of the desired char.
	 * @return char Returns the element (char) of the specefied position.
	 */
	public char getMapChar(CellPosition pos){
		return map.getCharOnPos(pos);
	}

	/**
	 * This method returns the guard.
	 * @return Guard Returns the guard.
	 */
	public Guard getGuard(){
		return guarda;
	}

	/**
	 * This method returns the list of all the ogres.
	 * @return ArrayList<Ogre> Returns the list of all the ogres.
	 */
	public ArrayList<Ogre> getOgres() {
		return ogres;
	}

	/**
	 * This method checks if the user lost the level or not.
	 * @return boolean Returns true if the user lost, false if otherwise.
	 */
	public boolean isGameOver(){

		if(guarda != null && guarda.getRepresentation() == 'G' && HeroCaught(getGuardPosition(),getHeroPosition())){
			Status = true;
			return true;
		}

		for (int i = 0; i < ogres.size(); i++){
			if(HeroCaught(ogres.get(i).getAttack(),getHeroPosition())){
				Status = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * This method checks if the hero is near a deadly enemy (ogre swing or guard).
	 * @param Guard_or_OgrePos The position of the deadly enemy.
	 * @param HeroPos The position of the hero.
	 * @return boolean Returns true if the hero was caught, false if otherwise.
	 */
	public boolean HeroCaught(CellPosition Guard_or_OgrePos, CellPosition HeroPos){
		if(Guard_or_OgrePos.getY()==HeroPos.getY())
			if(Guard_or_OgrePos.getX()==HeroPos.getX()-1||Guard_or_OgrePos.getX()==HeroPos.getX()+1||Guard_or_OgrePos.getX()==HeroPos.getX())
				return true;
		
		if(Guard_or_OgrePos.getX()==HeroPos.getX())
			if(Guard_or_OgrePos.getY()==HeroPos.getY()+1||Guard_or_OgrePos.getY()==HeroPos.getY()-1)
				return true;
		
		return false;
	}

	/**
	 * This method moves all ogres, checks if they get stunned and then swings their clubs.
	 */
	public void MoveAllTheOgres(){	
		if (ogres.size()==0)
			return;

		ClearAllOgresAndAttacks();

		for (Ogre ogre: ogres){
			moveOgre(ogre);
		}

		if (heroi.getRepresentation() != 'K')
			map.setCharOnPos(lever.getPosition(), lever.getRepresentation());

		stunOgres();

		for (Ogre ogre: ogres){
			map.setCharOnPos(ogre.getPosition(), ogre.getRepresentation());
		}

		for (Ogre ogre: ogres){
			ogreSwingClub(ogre);
		}
	}
	
	private void ClearAllOgresAndAttacks() {
		for (Ogre ogre: ogres){
			map.setCharOnPos(ogre.getPosition(), ' ');
			map.setCharOnPos(ogre.getAttack(), ' ');
		}	
	}

	/**
	 * This method moves a specific ogre, changing it's representation accordingly.
	 * @param ogre The ogre that shall be moved.
	 */
	public void moveOgre(Ogre ogre) {
		if(ogre.getStunTimeout()>0){
			ogre.setStunTimeout(ogre.getStunTimeout()-1);
			return;
		}
		else if(ogre.getStunTimeout()==0)
			ogre.setRepresentation('O');

		int moviment= ThreadLocalRandom.current().nextInt(0, 4);
		int y = ogre.getPosition().getY();
		int x = ogre.getPosition().getX();

		if (moviment  == 0)
			y -= 1;
		else if (moviment == 1)
			y += 1;
		else if (moviment == 2)
			x -= 1;
		else if (moviment == 3)
			x += 1;

		if (map.validPosition(y, x)&&map.getMap()[y][x] != 'S'){
			if (ogre.getOgreOnKey()&&heroi.getRepresentation()!='K'){
				ogre.setOgreOnKey(false);
				ogre.setRepresentation('O');
			}

			if (isKey && new CellPosition(y, x).equals(lever.getPosition())&&heroi.getRepresentation()!='K'){
				ogre.setRepresentation('$');
				ogre.setOgreOnKey(true);
			}

			ogre.setPosition(y, x);
		}
		else if (ogre.getPosition().equals(lever.getPosition())){
			ogre.setRepresentation('$');
			ogre.setOgreOnKey(true);
		}

	}

	/**
	 * This method swings a specific ogre's club. If the ogre has no valid positions to attack the club is stored on the ogre's position.
	 * @param ogre The ogre that shall attack.
	 */
	public void ogreSwingClub(Ogre ogre){

		boolean swinged=false;
		int trys=25;
		while(!swinged&&trys!=0){
			int pos= ThreadLocalRandom.current().nextInt(0, 4);
			int y = ogre.getPosition().getY();
			int x = ogre.getPosition().getX();

			if (pos  == 0)
				y -= 1;
			else if (pos == 1)
				y += 1;
			else if (pos == 2)
				x -= 1;
			else if (pos == 3)
				x += 1;

			if (map.validPosition(y, x)&&map.getMap()[y][x] != 'S'&&map.getMap()[y][x] != '$'&&map.getMap()[y][x] != 'O'&&map.getMap()[y][x] != 'o'){
				ogre.setAttack(new CellPosition(y,x));
				if(new CellPosition(y,x).equals(lever.getPosition()))
					map.setCharOnPos(ogre.getAttack(), '$');
				else
					map.setCharOnPos(ogre.getAttack(), '*');
				swinged=true;
			}
			if(!swinged)
				ogre.setAttack(ogre.getPosition());
			trys--;
		}	
	}

	private int newHeroY(char c){
		if (c == 'W')
			return -1;
		else if (c == 'S')
			return 1;
		else
			return 0;
	}

	private int newHeroX(char c){
		if (c == 'A')
			return -1;
		else if (c == 'D')
			return 1;
		else
			return 0;
	}

	private void printObjectsAfterHeroMoved(int y, int x){
		if (isKey && new CellPosition(y, x).equals(lever.getPosition())){
			heroi.setRepresentation('K');
			lever.setPosition(0, 0);
		} else if (!isKey && new CellPosition(y, x).equals(lever.getPosition())){
			map.openDoors(isKey, heroi.getPosition());
			printKey = true;
			openDoors = true;
		} else if (printKey) {
			map.setUnitPosMap(lever.getPosition(), lever.getPosition(), lever.getRepresentation());
			printKey = false;
		}
	}

	private void changeHeroLocation(int y, int x){
		if (map.validPosition(y, x)) {
			if (map.getCharOnPos(new CellPosition(y, x)) == 'S'){
				Status = false;
				return;
			}
			map.setCharOnPos(new CellPosition(y, x), heroi.getRepresentation());
			heroi.setPosition(y, x);
		} else { map.setCharOnPos(heroi.getPosition(), heroi.getRepresentation()); 
			if (!lever.getPosition().equals(new CellPosition(0, 0)))
				printKey = true; }

		if (isKey && map.getMap()[y][x] == 'I' && heroi.getRepresentation() == 'K') {
			map.openDoors(isKey, heroi.getPosition());
			openDoors = true;
		}
	}

	/**
	 * This method moves the hero, checking for special game conditions (Ex: Opening doors, lifting/pushing the key/lever).
	 * @param c The movement's direction.
	 */
	public void moveHero(char c) {
		c = Character.toUpperCase(c);
		int y = heroi.getPosition().getY() + newHeroY(c);
		int x = heroi.getPosition().getX() + newHeroX(c);
		map.setCharOnPos(heroi.getPosition(), ' ');
		printObjectsAfterHeroMoved(y, x);
		changeHeroLocation(y, x);
	}

	private void resetMovementIndex(int [] i){
		if (i[0] < 0)
			i[0] = guarda.getMovement().length - 1;
		else if (i[0] > guarda.getMovement().length - 1)
			i[0] = 0;
	}

	private int moveRookie(int [] n){
		if (guarda.getMovement()[n[0]] == 'W')
			n[1] -= 1;
		else if (guarda.getMovement()[n[0]] == 'S')
			n[1] += 1;
		else if (guarda.getMovement()[n[0]] == 'A')
			n[2] -= 1;
		else if (guarda.getMovement()[n[0]] == 'D')
			n[2] += 1;
		n[0]++;
		map.setUnitPosMap(new CellPosition(n[1], n[2]), guarda.getPosition(), guarda.getRepresentation());
		guarda.setPosition(n[1], n[2]);
		return n[0];
	}

	private int moveDrunkenMechanics(int [] n){
		Random rand = new Random();
		int r = rand.nextInt(5);
		if (r == 0){
			guarda.setTimeOut(3);
			guarda.setRepresentation('g');
			guarda.setGuardDirection();
			map.setUnitPosMap(new CellPosition(n[1], n[2]), guarda.getPosition(), guarda.getRepresentation());
			return n[0];
		}
		return moveDrunkenSuspicious(n);
	}

	private int moveSuspiciousMechanics(int [] n){
		guarda.setGuardDirection();
		return moveDrunkenSuspicious(n);
	}

	private int moveDrunkenSuspicious(int [] n){
		if(guarda.isFront()){
			return moveRookie(n);
		}
		else{
			n[0]--;
			resetMovementIndex(n);
			if (guarda.getMovement()[n[0]] == 'W') n[1] += 1;
			else if (guarda.getMovement()[n[0]] == 'S') n[1] -= 1;
			else if (guarda.getMovement()[n[0]] == 'A') n[2] += 1;
			else if (guarda.getMovement()[n[0]] == 'D') n[2] -= 1;
			map.setUnitPosMap(new CellPosition(n[1], n[2]), guarda.getPosition(), guarda.getRepresentation());
			guarda.setPosition(n[1], n[2]);
			return n[0];
		}
	}

	/**
	 * This method moves the guard, according to it's personality.
	 * @param i The index of the guard's next move.
	 */
	public void moveGuard(int [] i){
		if (guarda == null) return;

		if (guarda.getTimeOut() > 0){
			guarda.setTimeOut(guarda.getTimeOut() - 1);
			if (guarda.getTimeOut() == 0) guarda.setRepresentation('G');
			return; }

		resetMovementIndex(i);

		int y = guarda.getPosition().getY();
		int x = guarda.getPosition().getX();
		int [] n = { i[0], y, x };
		if (guarda.getType() == 0) i[0] = moveRookie(n);
		else if (guarda.getType() == 1) i[0] = moveDrunkenMechanics(n);
		else i[0] = moveSuspiciousMechanics(n); }

	/**
	 * This method stuns the ogres that are near the hero.
	 */
	public void stunOgres(){
		for (Ogre ogre: ogres){

			if (ogre.getRepresentation() != 'O')
				continue;
			if(ogre.getPosition().getY()==heroi.getPosition().getY()){
				if((ogre.getPosition().getX()==heroi.getPosition().getX()-1)||(ogre.getPosition().getX()==heroi.getPosition().getX()+1)){
					ogre.setRepresentation('o');
					ogre.setStunTimeout(3);
				}
			}
			if(ogre.getPosition().getX()==heroi.getPosition().getX()){
				if((ogre.getPosition().getY()==heroi.getPosition().getY()-1)||ogre.getPosition().getY()==heroi.getPosition().getY()+1){
					ogre.setRepresentation('o');
					ogre.setStunTimeout(3);
				}
			}
		}
	}

	/**
	 * This method returns the current game status.
	 * @return Boolean Returns true if the user lost, false if he won the level and stays null until one of the previous 2 happens.
	 */
	public Boolean EndStatus() {
		return Status;
	}

	/**
	 * This method returns the door's state.
	 * @return boolean Returns true if the doors are open, false if otherwise.
	 */
	public boolean areDoorsOpen(){
		return openDoors;
	}

	/**
	 * This method returns the current level's map.
	 * @return GameMap Returns the current level's map.
	 */
	public GameMap getMap(){
		return map;
	}
}
