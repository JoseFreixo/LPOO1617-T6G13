package dkeep.gui;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import dkeep.logic.GameMap;

@SuppressWarnings("serial")
public class CustomPanel extends JPanel{
	private BufferedImage heroImage;
	private BufferedImage heroArmedImage;
	private BufferedImage heroArmedKeyImage;
	private BufferedImage guardImage;
	private BufferedImage guardSleepingImage;
	private BufferedImage ogreImage;
	private BufferedImage ogreStunnedImage;
	private BufferedImage wallImage;
	private BufferedImage floorImage;
	private BufferedImage keyImage;
	private BufferedImage doorImage;
	private BufferedImage doorOpenImage;
	private BufferedImage clubImage;
	private BufferedImage cifraoImage;
	private GameMap map;
	
	public CustomPanel(){
		super();
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
		if(map.getMap()[y][x] == ' ')
			return floorImage;
		if(map.getMap()[y][x] == 'I')
			return doorImage;
		if(map.getMap()[y][x] == 'S')
			return doorOpenImage;
		if(map.getMap()[y][x] == 'H')
			return heroImage;
		if(map.getMap()[y][x] == 'A')
			return heroArmedImage;
		if(map.getMap()[y][x] == 'K')
			return heroArmedKeyImage;
		if(map.getMap()[y][x] == 'k')
			return keyImage;
		if(map.getMap()[y][x] == 'O')
			return ogreImage;
		if(map.getMap()[y][x] == 'o')
			return ogreStunnedImage;
		if(map.getMap()[y][x] == '*')
			return clubImage;
		if(map.getMap()[y][x] == 'G')
			return guardImage;
		if(map.getMap()[y][x] == 'g')
			return guardSleepingImage;
		if(map.getMap()[y][x] == '$')
			return cifraoImage;
		return wallImage;
	}
}
