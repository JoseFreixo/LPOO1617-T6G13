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
		Play play = new Play();
		play.playGame();
	}
}

