package dkeep.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CustomPanel extends JPanel{
	private BufferedImage heroImage;
	private BufferedImage heroArmedImage;
	private BufferedImage heroArmedKeyImage;
	private BufferedImage guardImage;
	private BufferedImage ogreImage;
	private BufferedImage wallImage;
	private BufferedImage floorImage;
	private BufferedImage keyImage;
	private BufferedImage leverImage;
	private BufferedImage doorImage;
	private BufferedImage doorOpenImage;
	
	public CustomPanel(){
		try {
			heroImage = ImageIO.read(new File("../../resources/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
	}

}
