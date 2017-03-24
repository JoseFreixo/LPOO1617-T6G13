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
		save(play);
		play=null;
		play=load();
		play.playGame();
	}


public static void save(Play play){
	try {
         FileOutputStream fileOut =
         new FileOutputStream("save.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(play);
         out.close();
         fileOut.close();
         
      }catch(IOException i) {
         i.printStackTrace();
      }
}



public static Play load(){

	try
	{
		FileInputStream fis = new FileInputStream("save.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Play play = (Play) ois.readObject();
		ois.close();
		fis.close();
		return play;
	}catch(IOException ioe){
		ioe.printStackTrace();
		return null;
	}catch(ClassNotFoundException c){
		System.out.println("Class not found");
		c.printStackTrace();
		return null;
	}
}
}