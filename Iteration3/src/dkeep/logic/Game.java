package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

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
	
	if (map.validPosition(y, x)){
		
		if (map.getMap()[y][x] == 'S'){
			map = map.getNextLevel();
			if (map == null){
				Status = false;
				return;
			}
			printKey = false;
			Status = null;
			openDoors = false;
			ogres = new ArrayList<Ogre>();
			for (int i = 0; i < map.getMap().length; i++){
				for (int j = 0; j < map.getMap()[i].length; j++){
					switch (map.getMap()[i][j]){
					case 'H': heroi = new Hero(i, j, 'H');
					break;
					case 'G': 
						guarda = new Guard(i, j, 'G');
						isKey = false;
					break;
					case 'O': 
						ogres.add(new Ogre(i, j, 'O'));
						isKey = true;
					break;
					case 'k': lever = new Lever(i, j, 'k');
					break;
					}
				}
			}
			return;
		}
		
		map.setUnitPosMap(new CellPosition(y, x), getHeroPosition(), heroi.getRepresentation());
		heroi.setPosition(y, x);
		if (printKey){
			map.setUnitPosMap(lever.getPosition(), lever.getPosition(), lever.getRepresentation());
			printKey = false;
		}
	}
	
	if (isKey && map.getMap()[y][x] == 'I' && heroi.getRepresentation() == 'K'){
		map.openDoors(isKey, heroi.getPosition());
		openDoors = true;
	}
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
}
