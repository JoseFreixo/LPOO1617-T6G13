package dkeep.cli;

import java.util.ArrayList;

import dkeep.logic.*;

public class main {

	public static void main(String[] args) {
		ArrayList<GameMap> maps = new ArrayList<GameMap>();
		maps.add(new DungeonMap());
		maps.add(new KeepMap());
		Play play = new Play(maps);
		play.playGame();
	}
}

