package dkeep.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import dkeep.logic.GameMap;

@SuppressWarnings("serial")
public class CustomPanel extends JPanel{
	private static BufferedImage heroImage;
	private static BufferedImage heroArmedImage;
	private static BufferedImage heroArmedKeyImage;
	private static BufferedImage guardImage;
	private static BufferedImage guardSleepingImage;
	private static BufferedImage ogreImage;
	private static BufferedImage ogreStunnedImage;
	private static BufferedImage wallImage;
	private static BufferedImage floorImage;
	private static BufferedImage keyImage;
	private static BufferedImage doorImage;
	private static BufferedImage doorOpenImage;
	private static BufferedImage clubImage;
	private static BufferedImage cifraoImage;
	private GameMap map;
	
	private static Map<Character,BufferedImage> CharToImage =
			new HashMap<Character,BufferedImage>();

	static {
		try {
			heroImage = ImageIO.read(new File("src/resources/player.png")); // mesmo processo para o resto das imagens
			heroArmedImage = ImageIO.read(new File("src/resources/player_armed.png"));
			heroArmedKeyImage = ImageIO.read(new File("src/resources/player_armed_key.png"));
			guardImage = ImageIO.read(new File("src/resources/guard.png"));
			guardSleepingImage = ImageIO.read(new File("src/resources/guard_sleeping.png"));
			ogreImage = ImageIO.read(new File("src/resources/ogre.png"));
			ogreStunnedImage = ImageIO.read(new File("src/resources/ogre_stunned.png"));
			wallImage = ImageIO.read(new File("src/resources/wall.png"));
			floorImage = ImageIO.read(new File("src/resources/floor.png"));
			keyImage = ImageIO.read(new File("src/resources/key.png"));
			doorImage = ImageIO.read(new File("src/resources/door.png"));
			doorOpenImage = ImageIO.read(new File("src/resources/open_door.png"));
			clubImage = ImageIO.read(new File("src/resources/club.png"));
			cifraoImage = ImageIO.read(new File("src/resources/cifrao.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CharToImage.put('H',heroImage);
		CharToImage.put('A',heroArmedImage);
		CharToImage.put('K',heroArmedKeyImage);
		CharToImage.put('G',guardImage);
		CharToImage.put('g',guardSleepingImage);
		CharToImage.put('O',ogreImage);
		CharToImage.put('o',ogreStunnedImage);
		CharToImage.put('X',wallImage);
		CharToImage.put(' ',floorImage);
		CharToImage.put('k',keyImage);
		CharToImage.put('I',doorImage);
		CharToImage.put('S',doorOpenImage);
		CharToImage.put('*',clubImage);
		CharToImage.put('$',cifraoImage);
	} 


	public CustomPanel(){
		super();
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		if (map != null){
			int x_size = this.getWidth() / map.getMap()[0].length;
			int y_size = this.getHeight() / map.getMap().length;
			for (int i = 0; i < map.getMap().length; i++){
				for (int j = 0; j < map.getMap()[i].length; j++){
					BufferedImage paint = getImage(i, j);
					if (paint != wallImage && paint != doorImage && paint != doorOpenImage)
						arg0.drawImage(floorImage, j * x_size, i * y_size, x_size, y_size, null);
					else if (paint == doorImage || paint == doorOpenImage)
						arg0.drawImage(wallImage, j * x_size, i * y_size, x_size, y_size, null);
					arg0.drawImage(paint, j * x_size, i * y_size, x_size, y_size, null);
				}
			}
		}
	}

	public void updateMap(GameMap map){
		this.map = map;
		repaint();
	}

	public BufferedImage getImage(int y, int x){
		return CharToImage.get(map.getMap()[y][x]);
	}
}
