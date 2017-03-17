package dkeep.cli;

import dkeep.logic.Game;
import dkeep.logic.GameMap;

public class main {

	public static void main(String[] args) {
		char [][][] maps = {{{'X','X','X','X','X','X','X','X','X','X'},
							 {'X','H',' ',' ','I',' ','X',' ','G','X'},
							 {'X','X','X',' ','X','X','X',' ',' ','X'},
							 {'X',' ','I',' ','I',' ','X',' ',' ','X'},
							 {'X','X','X',' ','X','X','X',' ',' ','X'},
							 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
							 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
							 {'X','X','X',' ','X','X','X','X',' ','X'},
							 {'X',' ','I',' ','I',' ','X','k',' ','X'},
							 {'X','X','X','X','X','X','X','X','X','X'}},
							
							{{'X','X','X','X','X','X','X','X','X'},
							 {'I',' ',' ',' ','O',' ',' ','k','X'},
							 {'X',' ',' ',' ',' ',' ',' ',' ','X'},
							 {'X',' ',' ',' ',' ',' ',' ',' ','X'},
							 {'X',' ',' ',' ',' ',' ',' ',' ','X'},
							 {'X',' ',' ',' ',' ',' ',' ',' ','X'},
							 {'X',' ',' ',' ',' ',' ',' ',' ','X'},
							 {'X','H',' ',' ',' ',' ',' ',' ','X'},
							 {'X','X','X','X','X','X','X','X','X'}}};
		int i = 0;
		while (i < maps.length){
			GameMap gameMap = new GameMap(maps[i]);
			Game game = new Game(gameMap);
			Play play = new Play(game);
			if (play.playGame()){
				if (i == maps.length - 1)
					System.out.println("You Win!");
				else
					System.out.println("Next Level!");
			}
			else {
				System.out.println("You Lose!");
				return;
			}
			i++;
		}
	}
}

