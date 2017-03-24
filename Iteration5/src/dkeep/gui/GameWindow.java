package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dkeep.cli.*;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToolBar;


public class GameWindow {

	private JFrame frmDungeonKeep;
	private JTextField numberOgresField;
	private Play play;
	private LevelEditorWindow levelEditor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frmDungeonKeep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setResizable(false);
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 650, 650);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres:");
		lblNumberOfOgres.setBounds(22, 38, 97, 20);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
		
		numberOgresField = new JTextField();
		numberOgresField.setBounds(129, 38, 95, 20);
		frmDungeonKeep.getContentPane().add(numberOgresField);
		numberOgresField.setColumns(10);
		numberOgresField.setText("3");
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality:");
		lblGuardPersonality.setBounds(22, 65, 97, 14);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
		
		JComboBox<String> guardTypeCombo = new JComboBox<String>();
		guardTypeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardTypeCombo.setToolTipText("Choose the Guard Personality");
		guardTypeCombo.setMaximumRowCount(3);
		guardTypeCombo.setBounds(129, 62, 95, 20);
		frmDungeonKeep.getContentPane().add(guardTypeCombo);
		
		CustomPanel gameArea = new CustomPanel();
		gameArea.setBounds(22, 95, 440, 440);
		frmDungeonKeep.getContentPane().add(gameArea);
		
		JLabel StatusLabel = new JLabel("You can start a new game.");
		StatusLabel.setBounds(29, 571, 583, 21);
		frmDungeonKeep.getContentPane().add(StatusLabel);
		
		JButton btnUp = new JButton("Up");
		JButton btnLeft = new JButton("Left");
		JButton btnRight = new JButton("Right");
		JButton btnDown = new JButton("Down");
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int status=setMapAndStatusLabel("Up.", StatusLabel);
				
				if(status==-1||status==1)
					enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);	
				
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		btnUp.setEnabled(false);
		btnUp.setBounds(513, 273, 76, 23);
		frmDungeonKeep.getContentPane().add(btnUp);
		
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status=setMapAndStatusLabel("Left.", StatusLabel);
				
				if(status==-1||status==1)
					enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);	
				
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(472, 307, 76, 23);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status=setMapAndStatusLabel("Right.", StatusLabel);
				
				if(status==-1||status==1)
					enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);	
				
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		btnRight.setEnabled(false);
		btnRight.setBounds(558, 307, 76, 23);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status=setMapAndStatusLabel("Down.", StatusLabel);
				
				if(status==-1||status==1)
					enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);	
				
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		btnDown.setEnabled(false);
		btnDown.setBounds(513, 341, 76, 23);
		frmDungeonKeep.getContentPane().add(btnDown);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nOgres;
				Scanner scan;
				try {
					scan = new Scanner(numberOgresField.getText());
					nOgres = scan.nextInt();
					if (nOgres <= 0 || nOgres > 5){
						scan.close();
						throw new NoSuchElementException();
					}
				}
				catch (NoSuchElementException Excp){
					StatusLabel.setText("The number of ogres must be between 1 and 5.");
					return;
				}
				
				String guardType = ((String)guardTypeCombo.getSelectedItem());
				
				StatusLabel.setText("Push the Lever (k) and escape the Dungeon while avoiding the guard.");
				play = new Play(nOgres, guardType);
				gameArea.updateMap(play.getMap());
				enableDisableMoves(true, btnUp, btnDown, btnLeft, btnRight);
				scan.close();
				frmDungeonKeep.requestFocus();
			}
		});
		btnNewGame.setBounds(502, 56, 95, 23);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		JButton btnExitGame = new JButton("Exit");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitGame.setBounds(502, 535, 95, 23);
		frmDungeonKeep.getContentPane().add(btnExitGame);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 644, 30);
		frmDungeonKeep.getContentPane().add(toolBar);
		
		JButton btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDungeonKeep.dispose();
				levelEditor = new LevelEditorWindow(play);
			}
		});
		toolBar.add(btnLevelEditor);
		
		frmDungeonKeep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			int status = -1;
			switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				status=setMapAndStatusLabel("Up.", StatusLabel);	
				if(status==-1||status==1)
					enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);	
				gameArea.updateMap(play.getMap());
				break;
			case KeyEvent.VK_LEFT:
				status=setMapAndStatusLabel("Left.", StatusLabel);	
				if(status==-1||status==1)
					enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);	
				gameArea.updateMap(play.getMap());
				break;
			case KeyEvent.VK_DOWN:
				status=setMapAndStatusLabel("Down.", StatusLabel);	
				if(status==-1||status==1)
					enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);	
				gameArea.updateMap(play.getMap());
				break;
			case KeyEvent.VK_RIGHT:
				status=setMapAndStatusLabel("Right.", StatusLabel);	
				if(status==-1||status==1)
					enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);	
				gameArea.updateMap(play.getMap());
				break;
			}
			}
		});

	}
	
	public JFrame getFrame(){
		return frmDungeonKeep;
	}
	
	public void enableDisableMoves(boolean isEnabled, JButton btnUp, JButton btnDown, JButton btnLeft, JButton btnRight){
		btnUp.setEnabled(isEnabled);
		btnDown.setEnabled(isEnabled);
		btnLeft.setEnabled(isEnabled);
		btnRight.setEnabled(isEnabled);
	}
	
	public int setMapAndStatusLabel(String move, JLabel StatusLabel){
		int status=-2; //does nothing
		
		switch(move){
		case "Up.": 
			status=play.moveHeroWindow('W'); 
			break;
		case "Down.": 
			status=play.moveHeroWindow('S'); 
			break;
		case "Left.": 
			status=play.moveHeroWindow('A'); 
			break;
		case "Right.": 
			status=play.moveHeroWindow('D'); 
			break;
		}
		
		if (status == 0){
			StatusLabel.setText("Hero moved " + move);
		}
		else if(status == -1){
			StatusLabel.setText("You lost.");
		}
		else if(status == 2){
			StatusLabel.setText("Next Level.");
		}
		else if (status==1){
			StatusLabel.setText("You win.");
		}
		return status;
	}
	
	
}
