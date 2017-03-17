package dkeep.cli;

import java.util.ArrayList;
import java.util.Scanner;

import dkeep.logic.*;

public class Play {
	private Game game;
	private ArrayList<GameMap> maps = new ArrayList<GameMap>();
	private int i;
	
	public Play(ArrayList<GameMap> maps) {
		this.maps = maps;
		i = 0;
		game = new Game(maps.get(i));
	}
	
	public void playGame(){
		Integer nextGuardMove = 0;
		char c;
		boolean gameIsOver = false;
		Scanner scan;
		while(!gameIsOver){
			game.printMap();
			System.out.print("To which direction would you like to move: ");
			scan = new Scanner(System.in);
			c = scan.next().charAt(0);
			c = Character.toUpperCase(c);
			game.moveHero(c);
			
			if (game.EndStatus() == false){
				i++;
				game = new Game(maps.get(i));
				gameIsOver = true;
				continue;
			}
			
			if (game.EndStatus() == true){
				gameIsOver = true;
			}
			
			game.MoveAllTheOgres();
			game.moveGuard(nextGuardMove);
			
			if (game.EndStatus() == true){
				gameIsOver = true;
			}
		}
	}
}
