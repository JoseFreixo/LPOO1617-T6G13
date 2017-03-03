package dkeep.logic;

public class Game {
	GameMap map;
	Hero heroi;
	Guard guarda;
	
	public Game(GameMap map){
		this.map = map;
		
		for (int i=0;i<map.getMap().length;i++){
			for (int j=0; j<map.getMap()[i].length;j++){
				switch(map.getMap()[i][j]){
				case 'H': heroi= new Hero(i,j);
				case 'G': guarda = new Guard(i,j);
				}
			}
		}
	}
	
	public CellPosition getHeroPosition(){
		return heroi.getPosition();
	}
	
	public boolean isGameOver(){
		boolean lost=false;
		//.......
		return lost;
	}
}
