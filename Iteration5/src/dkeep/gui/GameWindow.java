package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dkeep.cli.*;
import dkeep.saveLoadMaps.SLPlay;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToolBar;


public class GameWindow {

	private static final int NUMBER_OF_BUTTONS= 9;
	private static final int BT_UP= 0;
	private static final int BT_LEFT= 1;
	private static final int BT_RIGHT= 2;
	private static final int BT_DOWN= 3;
	private static final int BT_NEWGAME= 4;
	private static final int BT_EXIT= 5;
	private static final int BT_SAVE= 6;
	private static final int BT_LOAD= 7;
	private static final int BT_LVLEDIT= 8;

	private JFrame frmDungeonKeep;
	private JTextField numberOgresField;
	private Play play;


	private static Map<String, Character> moves =
			new HashMap<String, Character>();
	private static Map<Integer,String> keysToType =
			new HashMap<Integer,String>();

	static {
		moves.put("Up.", 'W');
		moves.put("Down.", 'S');
		moves.put("Left.", 'A');
		moves.put("Right.", 'D');
		keysToType.put(KeyEvent.VK_UP,"Up.");
		keysToType.put(KeyEvent.VK_DOWN,"Down.");
		keysToType.put(KeyEvent.VK_LEFT,"Left.");
		keysToType.put(KeyEvent.VK_RIGHT,"Right.");
	} 

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
		Boolean[] stopGame={true};
		
		setBasicWindow();
		
		JComboBox<String> guardTypeCombo = new JComboBox<String>(); 
		setGuardTypeCombo(guardTypeCombo);

		CustomPanel gameArea = new CustomPanel(); setGameArea(gameArea);

		JLabel StatusLabel = new JLabel("You can start a new game."); 
		setStatusLabel(StatusLabel);
		
		JToolBar toolBar = new JToolBar();setToolBar(toolBar);

		JButton []Buttons= new JButton[NUMBER_OF_BUTTONS]; 
		initializeAllButtons(Buttons);
		
		setAllButtonsAndKeys(Buttons, StatusLabel, stopGame, gameArea,guardTypeCombo, toolBar);

//		setBT_UP(Buttons,StatusLabel,stopGame,gameArea);
//		setBT_LEFT(Buttons,StatusLabel,stopGame,gameArea);
//		setBT_RIGHT(Buttons,StatusLabel,stopGame,gameArea);
//		setBT_DOWN(Buttons,StatusLabel,stopGame,gameArea);
//		setBT_NEWGAME(Buttons,StatusLabel,stopGame,gameArea,guardTypeCombo);
//		setBT_SAVE(Buttons,StatusLabel);
//		setBT_LOAD(Buttons,StatusLabel,stopGame,gameArea);
//		setBT_EXIT(Buttons);
//		setBT_LVLEDIT(toolBar,Buttons);
//		setAddKeyListener(Buttons,StatusLabel,stopGame,gameArea);
	}


	public JFrame getFrame(){
		return frmDungeonKeep;
	}

	private void enableDisableMoves(boolean isEnabled, JButton []Buttons, Boolean []stopGame){
		Buttons[BT_UP].setEnabled(isEnabled);
		Buttons[BT_LEFT].setEnabled(isEnabled);
		Buttons[BT_RIGHT].setEnabled(isEnabled);
		Buttons[BT_DOWN].setEnabled(isEnabled);
		Buttons[BT_SAVE].setEnabled(isEnabled);
		Buttons[BT_LVLEDIT].setEnabled(!isEnabled);
		stopGame[0]=!isEnabled;
	}

	private void setMapAndStatusLabel(String move, JLabel StatusLabel, JButton [] Buttons, Boolean []stopGame){
		int status;

		char keyTyped = moves.get(move);

		status=play.moveHeroWindow(keyTyped);

		if (status == 0) StatusLabel.setText("Hero moved " + move);
	
		else if(status == -1){
			StatusLabel.setText("You lost.");
			enableDisableMoves(false, Buttons, stopGame);	
		}
		
		else if(status == 2) StatusLabel.setText("Next Level.");

		else if (status==1){
			StatusLabel.setText("You win.");
			enableDisableMoves(false, Buttons, stopGame);	
		}
	}

	private void setFrmDungeonKeep(){
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setResizable(false);
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 650, 650);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
	}

	private void setlblNumberOfOgres(){
		JLabel lblNumberOfOgres= new JLabel("Number of Ogres:"); 
		lblNumberOfOgres.setBounds(22, 38, 116, 20);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
	}

	private void setNumberOgresField(){
		numberOgresField = new JTextField();
		numberOgresField.setBounds(148, 38, 95, 20);
		frmDungeonKeep.getContentPane().add(numberOgresField);
		numberOgresField.setColumns(10);
		numberOgresField.setText("3");
	}

	private void setStatusLabel(JLabel statusLabel) {
		statusLabel.setBounds(29, 571, 583, 21);
		frmDungeonKeep.getContentPane().add(statusLabel);
	}

	private void setlblGuardPersonality(){
		JLabel lblGuardPersonality = new JLabel("Guard Personality:");
		lblGuardPersonality.setBounds(22, 65, 116, 14);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
	}

	private void setGuardTypeCombo(JComboBox<String> guardTypeCombo){
		guardTypeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardTypeCombo.setToolTipText("Choose the Guard Personality");
		guardTypeCombo.setMaximumRowCount(3);
		guardTypeCombo.setBounds(148, 62, 95, 20);
		frmDungeonKeep.getContentPane().add(guardTypeCombo);
	}

	private void setGameArea(CustomPanel gameArea){
		gameArea.setBounds(22, 95, 440, 440);
		frmDungeonKeep.getContentPane().add(gameArea);
	}
	
	private void setToolBar(JToolBar toolBar) {
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 644, 30);
		frmDungeonKeep.getContentPane().add(toolBar);
	}

	private void initializeAllButtons(JButton [] Buttons){
		Buttons[BT_UP] = new JButton("Up");
		Buttons[BT_LEFT] = new JButton("Left");
		Buttons[BT_RIGHT] = new JButton("Right");
		Buttons[BT_DOWN] = new JButton("Down");
		Buttons[BT_NEWGAME] = new JButton("New Game");
		Buttons[BT_EXIT] = new JButton("Exit");
		Buttons[BT_SAVE] = new JButton("Save");
		Buttons[BT_LOAD] = new JButton("Load");
		Buttons[BT_LVLEDIT] = new JButton("Level Editor");
	}

	private void setBT_UP(JButton [] Buttons,JLabel StatusLabel, Boolean[] stopGame, CustomPanel gameArea){
		Buttons[BT_UP].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setMapAndStatusLabel("Up.", StatusLabel, Buttons,stopGame);			
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		Buttons[BT_UP].setEnabled(false);
		Buttons[BT_UP].setBounds(513, 273, 76, 23);
		frmDungeonKeep.getContentPane().add(Buttons[BT_UP]);
	}

	private void setBT_LEFT(JButton [] Buttons,JLabel StatusLabel, Boolean[] stopGame, CustomPanel gameArea){
		Buttons[BT_LEFT].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMapAndStatusLabel("Left.", StatusLabel, Buttons,stopGame);			
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		Buttons[BT_LEFT].setEnabled(false);
		Buttons[BT_LEFT].setBounds(472, 307, 76, 23);
		frmDungeonKeep.getContentPane().add(Buttons[BT_LEFT]);
	}
	
	private void setBT_RIGHT(JButton [] Buttons,JLabel StatusLabel, Boolean[] stopGame, CustomPanel gameArea){
		Buttons[BT_RIGHT].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMapAndStatusLabel("Right.", StatusLabel, Buttons, stopGame);			
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		Buttons[BT_RIGHT].setEnabled(false);
		Buttons[BT_RIGHT].setBounds(558, 307, 76, 23);
		frmDungeonKeep.getContentPane().add(Buttons[BT_RIGHT]);
	}

	private void setBT_DOWN(JButton [] Buttons,JLabel StatusLabel, Boolean[] stopGame, CustomPanel gameArea){
		Buttons[BT_DOWN].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMapAndStatusLabel("Down.", StatusLabel, Buttons, stopGame);			
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		Buttons[BT_DOWN].setEnabled(false);
		Buttons[BT_DOWN].setBounds(513, 341, 76, 23);
		frmDungeonKeep.getContentPane().add(Buttons[BT_DOWN]);
	}
	
	private int scanNumberOgres(JLabel StatusLabel){
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
			return -1;
		}
		scan.close();
		return nOgres;
	}
	
	private void setBT_NEWGAME(JButton [] Buttons,JLabel StatusLabel, Boolean[] stopGame, CustomPanel gameArea, JComboBox<String> guardTypeCombo){
		Buttons[BT_NEWGAME].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nOgres=scanNumberOgres(StatusLabel);
				if(nOgres==-1)
					return;
				
				String guardType = ((String)guardTypeCombo.getSelectedItem());

				StatusLabel.setText("Push the Lever (k) and escape the Dungeon while avoiding the guard.");
				play = new Play(nOgres, guardType);
				gameArea.updateMap(play.getMap());
				enableDisableMoves(true, Buttons, stopGame);
				frmDungeonKeep.requestFocus();
			}
		});
		Buttons[BT_NEWGAME].setBounds(502, 56, 95, 23);
		frmDungeonKeep.getContentPane().add(Buttons[BT_NEWGAME]);
	}
	
	private void setBT_SAVE(JButton [] Buttons,JLabel StatusLabel){
	Buttons[BT_SAVE].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(SLPlay.save(play))
				StatusLabel.setText("Game Saved with success.");
			else 
				StatusLabel.setText("Some error occurred game didn't save.");
			frmDungeonKeep.requestFocus();
		}
	});
	Buttons[BT_SAVE].setEnabled(false);
	Buttons[BT_SAVE].setBounds(502, 95, 95, 23);
	frmDungeonKeep.getContentPane().add(Buttons[BT_SAVE]);
	}
	
	private void setBT_LOAD(JButton [] Buttons,JLabel StatusLabel, Boolean stopGame[],CustomPanel gameArea){
		Buttons[BT_LOAD].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play=SLPlay.load();
				if(play==null){
					StatusLabel.setText("Some erro occurred, make sure you already saved a game once!");
					enableDisableMoves(false, Buttons, stopGame);
					return;
				}
				StatusLabel.setText("Game Loaded with success.");
				gameArea.updateMap(play.getMap());
				enableDisableMoves(true, Buttons, stopGame);
				frmDungeonKeep.requestFocus();
			}
		});
		Buttons[BT_LOAD].setBounds(502, 132, 95, 23);
		frmDungeonKeep.getContentPane().add(Buttons[BT_LOAD]);
	}
	
	private void setBT_EXIT(JButton [] Buttons){
		Buttons[BT_EXIT].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Buttons[BT_EXIT].setBounds(502, 535, 95, 23);
		frmDungeonKeep.getContentPane().add(Buttons[BT_EXIT]);
	}
	
	private void setBT_LVLEDIT(JToolBar toolBar,JButton [] Buttons){
		Buttons[BT_LVLEDIT].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDungeonKeep.dispose();
				new LevelEditorWindow();
			}
		});
		toolBar.add(Buttons[BT_LVLEDIT]);
	}
	
	private void setAddKeyListener(JButton [] Buttons,JLabel StatusLabel, Boolean stopGame[],CustomPanel gameArea){
		frmDungeonKeep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {		
				if(stopGame[0])	
					return;
				String move=keysToType.get(e.getKeyCode());	
				if(move==null)
					return;

				setMapAndStatusLabel(move, StatusLabel, Buttons, stopGame);	
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
				return;
			}
		});
	}
	
	private void setBasicWindow(){
		setFrmDungeonKeep();
		setlblNumberOfOgres();
		setNumberOgresField();
		setlblGuardPersonality();
	}
	
	private void setAllButtonsAndKeys(JButton [] Buttons,JLabel StatusLabel, Boolean[] stopGame, CustomPanel gameArea, JComboBox<String> guardTypeCombo,JToolBar toolBar){
		setBT_UP(Buttons,StatusLabel,stopGame,gameArea);
		setBT_LEFT(Buttons,StatusLabel,stopGame,gameArea);
		setBT_RIGHT(Buttons,StatusLabel,stopGame,gameArea);
		setBT_DOWN(Buttons,StatusLabel,stopGame,gameArea);
		setBT_NEWGAME(Buttons,StatusLabel,stopGame,gameArea,guardTypeCombo);
		setBT_SAVE(Buttons,StatusLabel);
		setBT_LOAD(Buttons,StatusLabel,stopGame,gameArea);
		setBT_EXIT(Buttons);
		setBT_LVLEDIT(toolBar,Buttons);
		setAddKeyListener(Buttons,StatusLabel,stopGame,gameArea);
	}
}
