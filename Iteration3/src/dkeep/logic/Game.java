package dkeep.logic;

import java.util.ArrayList;

public class Game {
	public static final boolean DEFEAT = true;
	Boolean Status;
	GameMap map;
	Hero heroi;
	Guard guarda;
	ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	
	public Game(GameMap map){
		this.map = map;
		Status=null;
		
		for (int i=0;i<map.getMap().length;i++){
			for (int j=0; j<map.getMap()[i].length;j++){
				switch(map.getMap()[i][j]){
				case 'H': heroi= new Hero(i,j);
				break;
				case 'G': guarda = new Guard(i,j);
				break;
				case 'O': ogres.add(new Ogre(i,j));
				break;
				}
			}
		}
	}
	
	public CellPosition getHeroPosition(){
		return heroi.getPosition();
	}
	
	public CellPosition getGuardPosition(){
		return guarda.getPosition();
	}

	
	public boolean isGameOver(){
		
		if(HeroCaught(getGuardPosition(),getHeroPosition())&&guarda!=null){
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
		if (c == 'W' && map.validPosition(getHeroPosition().getY() - 1, getHeroPosition().getX())){
			map.setUnitPosMap(new CellPosition(getHeroPosition().getY() - 1,getHeroPosition().getX()), getHeroPosition(), 'H');
			heroi.setPosition(getHeroPosition().getY() - 1, getHeroPosition().getX());
		}
		else if (c == 'S' && map.validPosition(getHeroPosition().getY() + 1, getHeroPosition().getX())){
			map.setUnitPosMap(new CellPosition(getHeroPosition().getY() + 1, getHeroPosition().getX()), getHeroPosition(), 'H');
			heroi.setPosition(getHeroPosition().getY() + 1, getHeroPosition().getX());
		}
		else if (c == 'A' && map.validPosition(getHeroPosition().getY(), getHeroPosition().getX() - 1)){
			map.setUnitPosMap(new CellPosition(getHeroPosition().getY(), getHeroPosition().getX() - 1), getHeroPosition(), 'H');
			heroi.setPosition(getHeroPosition().getY(), getHeroPosition().getX() - 1);
			}
		else if (c == 'D' && map.validPosition(getHeroPosition().getY(), getHeroPosition().getX() + 1)){
			map.setUnitPosMap(new CellPosition(getHeroPosition().getY(), getHeroPosition().getX() + 1), getHeroPosition(), 'H');
			heroi.setPosition(getHeroPosition().getY(), getHeroPosition().getX() + 1);
			}
	}

	public boolean EndStatus() {
		return Status;
	}
}
