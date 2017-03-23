package dkeep.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

import dkeep.logic.GameMap;

public class LevelEditorPanel extends JPanel implements MouseListener{
	private BufferedImage heroArmedImage;
	private BufferedImage ogreImage;
	private BufferedImage wallImage;
	private BufferedImage floorImage;
	private BufferedImage keyImage;
	private BufferedImage doorImage;
	private GameMap map;
	
	public LevelEditorPanel(int lines, int columns){
		super();
		addMouseListener(this);
		try {
			heroArmedImage = ImageIO.read(new File("src/resources/player_armed.png"));
			ogreImage = ImageIO.read(new File("src/resources/ogre.png"));
			wallImage = ImageIO.read(new File("src/resources/wall.png"));
			floorImage = ImageIO.read(new File("src/resources/floor.png"));
			keyImage = ImageIO.read(new File("src/resources/key.png"));
			doorImage = ImageIO.read(new File("src/resources/door.png"));
			char [][] mapa = new char [lines][columns];
			for (int i = 0; i < mapa.length; i++){
				Arrays.fill(mapa[i], ' ');
			}
			map = new GameMap(mapa);
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
					if (paint != wallImage && paint != doorImage)
						arg0.drawImage(floorImage, j * x_size, i * y_size, x_size, y_size, null);
					else if (paint == doorImage)
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
		if(map.getMap()[y][x] == 'A')
			return heroArmedImage;
		if(map.getMap()[y][x] == 'k')
			return keyImage;
		if(map.getMap()[y][x] == 'O')
			return ogreImage;
		return wallImage;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	public void mouseDragged(MouseEvent e) {
		
	}
}
