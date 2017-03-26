package dkeep.cli;

import java.util.ArrayList;

import dkeep.logic.*;
import dkeep.saveLoadMaps.DungeonMap;
import dkeep.saveLoadMaps.KeepMap;

public class DungeonKeep {


	public static void main(String[] args) {

		ArrayList<GameMap> maps = new ArrayList<GameMap>();
		maps.add(DungeonMap.getDungeonMap());
		maps.add(KeepMap.getKeepMap());
		Play play = new Play(maps);
		play.playGame();

	}
}