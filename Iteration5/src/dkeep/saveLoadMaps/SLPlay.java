package dkeep.saveLoadMaps;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dkeep.cli.Play;

public class SLPlay {
	public static boolean save(Play play) {
		try {
			FileOutputStream fileOut = new FileOutputStream("play.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(play);
			out.close();
			fileOut.close();
			return true;
		} catch (IOException i) {
			return false;
		}
	}



	public static Play load() {

		try {
			FileInputStream fis = new FileInputStream("play.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Play play = (Play) ois.readObject();
			ois.close();
			fis.close();
			return play;
		} catch (IOException ioe) {
			return null;
		} catch (ClassNotFoundException c) {
			return null;
		}
	}
}
