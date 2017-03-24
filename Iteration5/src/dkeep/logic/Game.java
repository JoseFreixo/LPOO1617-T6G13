package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
	
	public Game(GameMap map, int guardPersonalaty, int numberOfOgres){
		this.map = map;
		Status = null;
		openDoors = false;
		int x = 0, y = 0;
		for (int i=0;i<map.getMap().length;i++){
			for (int j=0; j<map.getMap()[i].length;j++){
				switch(map.getMap()[i][j]){
				case 'H': heroi = new Hero(i, j, 'H');
				break;
				case 'A': heroi = new Hero(i, j, 'A');
				break;
				case 'G': 
					guarda = new Guard(i, j, 'G', guardPersonalaty);
					isKey = false;
				break;
				case 'O':
					x = j;
					y = i;
					isKey = true;
				break;
				case 'k': lever = new Lever(i, j, 'k');
				break;
				}
			}
		}
		if(isKey){
			heroi.setRepresentation('A');
			int n = numberOfOgres;
			for (int i = 0; i < n; i++){
				ogres.add(new Ogre(y, x, 'O'));
			}
		}
	}
	
	public CellPosition getLeverPosition(){
		return lever.getPosition();
	}

	public CellPosition getHeroPosition(){
		return heroi.getPosition();
	}
	
	public CellPosition getGuardPosition(){
		return guarda.getPosition();
	}

	public char getHeroRepresentation(){
		return heroi.getRepresentation();
	}
	
	public char getGuardRepresentation(){
		return guarda.getRepresentation();
	}
	
	public char getMapChar(CellPosition Pos){
		return map.getCharOnPos(Pos);
	}
	
	public Guard getGuard(){
		return guarda;
	}
	
	public ArrayList<Ogre> getOgres() {
		return ogres;
	}
	
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
	
	public boolean HeroCaught(CellPosition Guard_or_OgrePos, CellPosition HeroPos){
		
		if(Guard_or_OgrePos.getX()==HeroPos.getX()-1&& Guard_or_OgrePos.getY()==HeroPos.getY()){
			return true;
		}
		if(Guard_or_OgrePos.getX()==HeroPos.getX()+1&& Guard_or_OgrePos.getY()==HeroPos.getY()){	
			return true;
		}
		if(Guard_or_OgrePos.getX()==HeroPos.getX()&& Guard_or_OgrePos.getY()==HeroPos.getY()+1){
			return true;
		}
		if(Guard_or_OgrePos.getX()==HeroPos.getX()&& Guard_or_OgrePos.getY()==HeroPos.getY()-1){
			return true;
		}		
		if(Guard_or_OgrePos.getX()==HeroPos.getX()&& Guard_or_OgrePos.getY()==HeroPos.getY()){
			return true;
		}
		return false;
	}
	
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
		
	}
	
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
				trys--;
			}
			if(!swinged)
				ogre.setAttack(ogre.getPosition());
		}	
	}

	public void moveHero(char c) {
		c = Character.toUpperCase(c);
		int y = heroi.getPosition().getY();
		int x = heroi.getPosition().getX();
	
		if (c == 'W')
			y -= 1;
		else if (c == 'S')
			y += 1;
		else if (c == 'A')
			x -= 1;
		else if (c == 'D')
			x += 1;
		else
			return;
		
		map.setCharOnPos(heroi.getPosition(), ' ');
		
		if (isKey && new CellPosition(y, x).equals(lever.getPosition())){
			heroi.setRepresentation('K');
			lever.setPosition(0, 0);
		}
		else if (!isKey && new CellPosition(y, x).equals(lever.getPosition())){
			map.openDoors(isKey, heroi.getPosition());
			printKey = true;
			openDoors = true;
		}
		else if (printKey) {
			map.setUnitPosMap(lever.getPosition(), lever.getPosition(), lever.getRepresentation());
			printKey = false;
		}
		
	
		if (map.validPosition(y, x)) {
			if (map.getCharOnPos(new CellPosition(y, x)) == 'S'){
				Status = false;
				return;
			}
			map.setCharOnPos(new CellPosition(y, x), heroi.getRepresentation());
			heroi.setPosition(y, x);
		}
		else{ 
			map.setCharOnPos(heroi.getPosition(), heroi.getRepresentation());
		}
		
		if (isKey && map.getMap()[y][x] == 'I' && heroi.getRepresentation() == 'K') {
			map.openDoors(isKey, heroi.getPosition());
			openDoors = true;
		}
	}
	
	public void moveGuard(int [] i){
		if (guarda == null)
			return;
		
		if (guarda.getTimeOut() > 0){
			guarda.setTimeOut(guarda.getTimeOut() - 1);
			if (guarda.getTimeOut() == 0)
				guarda.setRepresentation('G');
			return;
		}
		
		if (i[0] < 0)
			i[0] = guarda.getMovement().length - 1;
		else if (i[0] > guarda.getMovement().length - 1)
			i[0] = 0;
		
		int y = guarda.getPosition().getY();
		int x = guarda.getPosition().getX();
		if (guarda.getType() == 0){ // Rookie
			if (guarda.getMovement()[i[0]] == 'W')
				y -= 1;
			else if (guarda.getMovement()[i[0]] == 'S')
				y += 1;
			else if (guarda.getMovement()[i[0]] == 'A')
				x -= 1;
			else if (guarda.getMovement()[i[0]] == 'D')
				x += 1;
			i[0]++;
		}
		else if (guarda.getType() == 1){ // Drunken
			Random rand = new Random();
			int r = rand.nextInt(5);
			if (r == 0){
				guarda.setTimeOut(3);
				guarda.setRepresentation('g');
				r = rand.nextInt(2);
				if (r == 0)
					guarda.setFront(true);
				else
					guarda.setFront(false);
			}
			else if(guarda.isFront()){
				if (guarda.getMovement()[i[0]] == 'W')
					y -= 1;
				else if (guarda.getMovement()[i[0]] == 'S')
					y += 1;
				else if (guarda.getMovement()[i[0]] == 'A')
					x -= 1;
				else if (guarda.getMovement()[i[0]] == 'D')
					x += 1;
				i[0]++;
			}
			else{
				i[0]--;
				if (i[0] < 0)
					i[0] = guarda.getMovement().length - 1;
				else if (i[0] > guarda.getMovement().length - 1)
					i[0] = 0;
				if (guarda.getMovement()[i[0]] == 'W')
					y += 1;
				else if (guarda.getMovement()[i[0]] == 'S')
					y -= 1;
				else if (guarda.getMovement()[i[0]] == 'A')
					x += 1;
				else if (guarda.getMovement()[i[0]] == 'D')
					x -= 1;
			}
			
		}
		else { // Suspicious
			Random rand = new Random();
			int r = rand.nextInt(2);
			if (r == 0)
				guarda.setFront(true);
			else
				guarda.setFront(false);
			
			if(guarda.isFront()){
				if (guarda.getMovement()[i[0]] == 'W')
					y -= 1;
				else if (guarda.getMovement()[i[0]] == 'S')
					y += 1;
				else if (guarda.getMovement()[i[0]] == 'A')
					x -= 1;
				else if (guarda.getMovement()[i[0]] == 'D')
					x += 1;
				i[0]++;
			}
			else{
				i[0]--;
				if (i[0] < 0)
					i[0] = guarda.getMovement().length - 1;
				else if (i[0] > guarda.getMovement().length - 1)
					i[0] = 0;
				if (guarda.getMovement()[i[0]] == 'W')
					y += 1;
				else if (guarda.getMovement()[i[0]] == 'S')
					y -= 1;
				else if (guarda.getMovement()[i[0]] == 'A')
					x += 1;
				else if (guarda.getMovement()[i[0]] == 'D')
					x -= 1;
			}
		}
		
		map.setUnitPosMap(new CellPosition(y, x), guarda.getPosition(), guarda.getRepresentation());
		guarda.setPosition(y, x);
	}
	
	public void stunOgres(){
		for (Ogre ogre: ogres){
			if (ogre.getRepresentation() == 'O'){
				if((ogre.getPosition().getX()==heroi.getPosition().getX()-1 && ogre.getPosition().getY()==heroi.getPosition().getY()) ||
				   (ogre.getPosition().getX()==heroi.getPosition().getX()+1 && ogre.getPosition().getY()==heroi.getPosition().getY()) ||
				   (ogre.getPosition().getX()==heroi.getPosition().getX() && ogre.getPosition().getY()==heroi.getPosition().getY()-1) ||
				   (ogre.getPosition().getX()==heroi.getPosition().getX() && ogre.getPosition().getY()==heroi.getPosition().getY()+1)){
					ogre.setRepresentation('o');
					ogre.setStunTimeout(3);
				}
			}
		}
	}
	
	public Boolean EndStatus() {
		return Status;
	}
	
	public boolean areDoorsOpen(){
		return openDoors;
	}
	
	public GameMap getMap(){
		return map;
	}
	
	public void printMap(){
		for (int i = 0; i < map.getMap().length; i++){
			for (int j = 0; j < map.getMap()[i].length; j++){
				System.out.print(map.getMap()[i][j] + " ");
			}
			System.out.println();
		}
	}
}
