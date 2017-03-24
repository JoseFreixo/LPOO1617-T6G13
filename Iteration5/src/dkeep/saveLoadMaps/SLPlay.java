package dkeep.saveLoadMaps;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dkeep.cli.Play;

public class SLPlay {
	public static void save(Play play) {
		try {
			FileOutputStream fileOut = new FileOutputStream("play.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(play);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}



	public static Play load() {

		try {
			FileInputStream fis = new FileInputStream("maps.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Play play = (Play) ois.readObject();
			ois.close();
			fis.close();
			return play;
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
