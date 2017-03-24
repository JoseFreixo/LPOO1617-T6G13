package dkeep.cli;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import dkeep.logic.*;

public class Play implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;
	private Game game;
	private ArrayList<GameMap> maps = new ArrayList<GameMap>();
	private int i, numberOgres, guardType;
	int [] nextGuardMove = { 0 };
	
	public Play(ArrayList<GameMap> maps) {
		
		this.maps = maps;
		i = 0;
		numberOgres=ThreadLocalRandom.current().nextInt(1, 6); 
		guardType=ThreadLocalRandom.current().nextInt(0, 3); 
		game = new Game(maps.get(i),guardType,numberOgres);
	}
	
	public Play(int numberOgres, String guardTypeStr){
		GameMap mapa = new DungeonMap();
		maps.add(mapa);
		mapa = new KeepMap();
		maps.add(mapa);
		i = 0;
		switch(guardTypeStr){
		case "Rookie":
			guardType = 0;
			break;
		case "Drunken":
			guardType = 1;
			break;
		case "Suspicious":
			guardType = 2;
			break;
		}
		this.numberOgres=numberOgres;
		game = new Game(maps.get(i), this.guardType, this.numberOgres);
	}
	
	public GameMap getMap(){
		return game.getMap();
	}
	
	public Game getGame(){
		return game;
	}
	
	public int moveHeroWindow(char c){
		game.moveHero(c);
		
		if (game.EndStatus() != null && game.EndStatus() == false){
			i++;
			if (i >= maps.size()){
				return 1; // Victory
			}
			game = new Game(maps.get(i),guardType,numberOgres);
			return 2; // Next Level
		}
		
		if (game.isGameOver()){
			return -1; // Defeat
		}
		
		game.stunOgres();
		
		game.MoveAllTheOgres();
		game.moveGuard(nextGuardMove);
		
		if (game.isGameOver()){
			return -1; // Defeat
		}
		
		return 0; // Keep Playing
	}
	
	public void playGame(){
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
			if (game.EndStatus() != null && game.EndStatus() == false){
				i++;
				if (i >= maps.size()){
					gameIsOver = true;
					game.printMap();
					continue;
				}
				game = new Game(maps.get(i),guardType,numberOgres);
				continue;
			}
			
			if (game.isGameOver()){
				game.printMap();
				gameIsOver = true;
			}
			
			game.stunOgres();
			
			game.MoveAllTheOgres();
			game.moveGuard(nextGuardMove);
			
			if (game.isGameOver()){
				game.printMap();
				gameIsOver = true;
			}
		}
		if (i < maps.size())
			System.out.println("You lose!");
		else
			System.out.println("You win!");
	}
}
