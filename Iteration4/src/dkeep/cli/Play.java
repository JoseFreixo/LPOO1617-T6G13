package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.Game;

public class Play {
	private Game game;
	
	public Play(Game game) {
		this.game = game;
	}
	
	public boolean playGame(){
		char c;
		Scanner scan;
		while(true){
			game.printMap();
			System.out.print("To which direction would you like to move: ");
			scan = new Scanner(System.in);
			c = scan.next().charAt(0);
			c = Character.toUpperCase(c);
			game.moveHero(c);
		}
		
	}
}
