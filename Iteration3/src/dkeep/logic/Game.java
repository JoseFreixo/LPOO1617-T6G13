package dkeep.logic;

import java.util.ArrayList;

public class Game {
	GameMap map;
	Hero heroi;
	Guard guarda;
	ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	
	public Game(GameMap map){
		this.map = map;
		
		
		for (int i=0;i<map.getMap().length;i++){
			for (int j=0; j<map.getMap()[i].length;j++){
				switch(map.getMap()[i][j]){
				case 'H': heroi= new Hero(i,j);
				case 'G': guarda = new Guard(i,j);
				case 'O': ogres.add(new Ogre(i,j));
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
	
	public CellPosition getOgrePosition(int i){
		int j = 0;
		for (Ogre temp: ogres){
			if (i == j)
				return temp.getPosition();
			j++;
		}
		return null;
	}
	
	public boolean isGameOver(){
		boolean lost=false;
		
		return lost;
	}
	
	public boolean isValid(CellPosition posheroi, CellPosition posGuard){
		return true;
	}

	public void moveHero(char c) {
		Character.toUpperCase(c);
		if (c == 'W' && map.validPosition(getHeroPosition().getY() - 1, getHeroPosition().getX()))
			heroi.setPosition(getHeroPosition().getY() - 1, getHeroPosition().getX());
		else if (c == 'S' && map.validPosition(getHeroPosition().getY() + 1, getHeroPosition().getX()))
			heroi.setPosition(getHeroPosition().getY() + 1, getHeroPosition().getX());
		else if (c == 'A' && map.validPosition(getHeroPosition().getY(), getHeroPosition().getX() - 1))
			heroi.setPosition(getHeroPosition().getY(), getHeroPosition().getX() - 1);
		else if (c == 'D' && map.validPosition(getHeroPosition().getY(), getHeroPosition().getX() + 1))
			heroi.setPosition(getHeroPosition().getY(), getHeroPosition().getX() + 1);
	}
}
