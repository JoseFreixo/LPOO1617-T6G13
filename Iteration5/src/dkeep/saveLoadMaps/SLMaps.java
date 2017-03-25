package dkeep.saveLoadMaps;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dkeep.logic.GameMap;

public class SLMaps {
	
	
	public static void setMaps(ArrayList<GameMap> maps) {
		try {
			FileOutputStream fileOut = new FileOutputStream("maps.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(maps);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}



	public static ArrayList<GameMap> getMaps() {

		try {
			FileInputStream fis = new FileInputStream("maps.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			@SuppressWarnings("unchecked")
			ArrayList<GameMap> maps = (ArrayList<GameMap>) ois.readObject();
			ois.close();
			fis.close();
			return maps;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Maps not found");
			c.printStackTrace();
			return null;
		}
	}
}