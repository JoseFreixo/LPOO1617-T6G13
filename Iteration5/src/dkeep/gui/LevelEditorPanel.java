package dkeep.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

import dkeep.logic.CellPosition;
import dkeep.logic.GameMap;

@SuppressWarnings("serial")
public class LevelEditorPanel extends JPanel implements MouseListener, MouseMotionListener{
	private BufferedImage heroArmedImage;
	private BufferedImage ogreImage;
	private BufferedImage wallImage;
	private BufferedImage floorImage;
	private BufferedImage keyImage;
	private BufferedImage doorImage;
	private char toPaintChar;
	private GameMap map;

	public LevelEditorPanel(){
		super();
		try {
			heroArmedImage = ImageIO.read(new File("src/resources/player_armed.png"));
			ogreImage = ImageIO.read(new File("src/resources/ogre.png"));
			wallImage = ImageIO.read(new File("src/resources/wall.png"));
			floorImage = ImageIO.read(new File("src/resources/floor.png"));
			keyImage = ImageIO.read(new File("src/resources/key.png"));
			doorImage = ImageIO.read(new File("src/resources/door.png"));
			toPaintChar = 'X';
			addMouseListener(this);
			addMouseMotionListener(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Some unexpected error ocurred"); 
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

	public void newMap(int lines, int columns){		
		char [][] mapa = new char [lines][columns];
		for (int i = 0; i < mapa.length; i++){
			Arrays.fill(mapa[i], ' ');
		}
		map = new GameMap(mapa);
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
		updateStuff(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		updateStuff(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		updateStuff(arg0);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}


	public void updateChar(char carater){
		toPaintChar = carater;
	}

	public void updateStuff(MouseEvent arg0){
		try{
			int x_size = this.getWidth() / map.getMap()[0].length;
			int y_size = this.getHeight() / map.getMap().length;
			int l = arg0.getY() / y_size;
			int c = arg0.getX() / x_size;
			map.setCharOnPos(new CellPosition(l, c), toPaintChar);
			repaint();
		}
		catch (ArrayIndexOutOfBoundsException e){
			//DO NOTHING
		}
	}

	public boolean verifyMap(){
		int heroCounter = 0, ogreCounter = 0, keyCounter = 0, doorCounter = 0;
		int y = 0, x = 0;
		for (int i = 0; i < map.getMap().length; i++){
			for (int j = 0; j < map.getMap()[1].length; j++){
				if (mapVerificationCondition(i, j))
					return false;
				if (map.getMap()[i][j] == 'A'){
					heroCounter++;
					y = i; x = j;
				}
				else if(map.getMap()[i][j] == 'O')
					ogreCounter++;
				else if (map.getMap()[i][j] == 'k')
					keyCounter++;
				else if (map.getMap()[i][j] == 'I')
					doorCounter++;
			}
		}
		if (heroCounter != 1 || ogreCounter != 1 || keyCounter != 1 || doorCounter == 0)
			return false;
		char [][] visited = map.copyMap(map.getMap()), visited2 = map.copyMap(visited);
		return findExit(visited, y, x, 'I') && findExit(visited2, y, x, 'k');
	}
	
	public boolean mapVerificationCondition(int i, int j){
		return (i == 0 || j == 0 || i == map.getMap().length - 1 || j == map.getMap()[i].length - 1) && (map.getMap()[i][j] != 'X' && map.getMap()[i][j] != 'I');
	}
	
	public boolean findExit(char [][] visited, int y, int x, char goal){
		try {
			if (map.getMap()[y][x] != 'X' && visited[y][x] != 'V'){
				if (map.getMap()[y][x] == goal)
					return true;
				else {
					visited[y][x] = 'V';
					return findExit(visited, y + 1, x, goal) || findExit(visited, y - 1, x, goal)
							|| findExit(visited, y, x + 1, goal) || findExit(visited, y, x - 1, goal);
				}
			}
			return false;
		}
		catch (ArrayIndexOutOfBoundsException excp){
			return false;
		}
	}

	public GameMap returnMap(){
		return map;
	}
}
