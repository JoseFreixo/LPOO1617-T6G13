package dkeep.cli;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dkeep.logic.*;

public class DungeonKeep {

	
	public static void main(String[] args) {
		ArrayList<GameMap> maps = new ArrayList<GameMap>();
		maps.add(new DungeonMap());
		maps.add(new KeepMap());
		Play play = new Play(maps);
		play.playGame();
	}
}