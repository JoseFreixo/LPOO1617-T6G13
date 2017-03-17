package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
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
	
	public Game(GameMap map){
		this.map = map;
		Status = null;
		openDoors = false;
		int x = 0, y = 0;
		for (int i=0;i<map.getMap().length;i++){
			for (int j=0; j<map.getMap()[i].length;j++){
				switch(map.getMap()[i][j]){
				case 'H': heroi = new Hero(i, j, 'H');
				break;
				case 'G': 
					guarda = new Guard(i, j, 'G');
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
			Random rand = new Random();
			int n = rand.nextInt(3);
			for (int i = 0; i <= n; i++){
				ogres.add(new Ogre(y, x, 'O'));
			}
		}
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
	
	public char getMapChar(CellPosition Pos){
		return map.getCharOnPos(Pos);
	}
	
	public ArrayList<Ogre> getOgres() {
		return ogres;
	}
	
	
	public boolean isGameOver(){
		
		if(guarda!=null&&HeroCaught(getGuardPosition(),getHeroPosition())){
			Status = true;
			return true;
		}
		
		for (int i=0; i<ogres.size();i++){
			if(HeroCaught(ogres.get(i).getPosition(),getHeroPosition())){
				Status = true;
				return true;
			}
			else if(ogres.get(i).getClub()!=null&&HeroCaught(ogres.get(i).getClub().getPosition(),getHeroPosition())){
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
		return false;
	}
	
	public void MoveAllTheOgres(){
		
		for (Ogre ogre: ogres){
			moveOgre(ogre);
			ogreSwingClub(ogre);
		}
		
	}
	
	public void ogreSwingClub(Ogre ogre){
		boolean swinged=false;
		while(!swinged){
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


			if (isKey && new CellPosition(y, x).equals(lever.getPosition())&&heroi.getRepresentation()=='H'){
				Club newClub= new Club(y,x,'$');

				if(ogre.getClub()!=null)
					map.setUnitPosMap(newClub.getPosition(), ogre.getClub().getPosition(), newClub.getRepresentation());
				else
					map.setCharOnPos(newClub.getPosition(), newClub.getRepresentation());

				ogre.setClub(newClub);
				ogre.setSwingedOnKey(true);
				swinged=true;
			}		
			else if (map.validPosition(y, x)&&map.getMap()[y][x] != 'S'){	
				Club newClub= new Club(y,x,'*');

				if(ogre.getClub()!=null)
					map.setUnitPosMap(newClub.getPosition(), ogre.getClub().getPosition(), newClub.getRepresentation());
					else
					map.setCharOnPos(newClub.getPosition(), newClub.getRepresentation());

				ogre.setClub(newClub);
				swinged=true;
				if (ogre.getSwingedOnKey()&&heroi.getRepresentation()=='H'){
					ogre.setSwingedOnKey(false);
					map.setUnitPosMap(lever.getPosition(), lever.getPosition(), lever.getRepresentation());
				}
			}
		}
	}
	
	

	public void moveOgre(Ogre ogre) { 
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
		
		if (isKey && new CellPosition(y, x).equals(lever.getPosition())&&heroi.getRepresentation()=='H'){
			ogre.setRepresentation('$');
			ogre.setOgreOnKey(true);
		}	
		if (map.validPosition(y, x)&&map.getMap()[y][x] != 'S'){	
			map.setUnitPosMap(new CellPosition(y, x), ogre.getPosition(), ogre.getRepresentation());
			ogre.setPosition(y, x);
			
			if (ogre.getOgreOnKey()&&heroi.getRepresentation()=='H'){
				ogre.setOgreOnKey(false);
				map.setUnitPosMap(lever.getPosition(), lever.getPosition(), lever.getRepresentation());
			}
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
	
		if (isKey && new CellPosition(y, x).equals(lever.getPosition()))
			heroi.setRepresentation('K');
		else if (!isKey && new CellPosition(y, x).equals(lever.getPosition())){
			map.openDoors(isKey, heroi.getPosition());
			printKey = true;
			openDoors = true;
		}
	
		if (map.validPosition(y, x)) {

			if (map.getMap()[y][x] == 'S') {
				map = map.getNextLevel();
				if (map == null) {
					Status = false;
					return;
				}
				printKey = false;
				Status = null;
				openDoors = false;
				ogres = new ArrayList<Ogre>();
				for (int i = 0; i < map.getMap().length; i++) {
					for (int j = 0; j < map.getMap()[i].length; j++) {
						switch (map.getMap()[i][j]) {
						case 'H':
							heroi = new Hero(i, j, 'H');
							break;
						case 'G':
							guarda = new Guard(i, j, 'G');
							isKey = false;
							break;
						case 'O':
							ogres.add(new Ogre(i, j, 'O'));
							isKey = true;
							break;
						case 'k':
							lever = new Lever(i, j, 'k');
							break;
						}
					}
				}
				return;
			}

			map.setUnitPosMap(new CellPosition(y, x), getHeroPosition(), heroi.getRepresentation());
			heroi.setPosition(y, x);
			if (printKey) {
				map.setUnitPosMap(lever.getPosition(), lever.getPosition(), lever.getRepresentation());
				printKey = false;
			}
		}

		if (isKey && map.getMap()[y][x] == 'I' && heroi.getRepresentation() == 'K') {
			map.openDoors(isKey, heroi.getPosition());
			openDoors = true;
		}
	}
	
	public void moveGuard(int i){
		int y = guarda.getPosition().getY();
		int x = guarda.getPosition().getX();
		
		if (guarda.getMovement()[i] == 'W')
			y -= 1;
		else if (guarda.getMovement()[i] == 'S')
			y += 1;
		else if (guarda.getMovement()[i] == 'A')
			x -= 1;
		else if (guarda.getMovement()[i] == 'D')
			x += 1;
		
		map.setUnitPosMap(new CellPosition(y, x), guarda.getPosition(), guarda.getRepresentation());
		guarda.setPosition(y, x);
	}

	public boolean EndStatus() {
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
