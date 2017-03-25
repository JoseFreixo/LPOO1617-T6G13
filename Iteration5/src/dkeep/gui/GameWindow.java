package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dkeep.cli.*;
import dkeep.logic.GameMap;
import dkeep.saveLoadMaps.SLPlay;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
	private ArrayList<GameMap> maps = new ArrayList<GameMap>(); 
	private Play play;
	private LevelEditorWindow levelEditor;
	
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
		//JButton []buttonsToEdit= new JButton[5];
		Boolean[] stopGame={true};
		
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setResizable(false);
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 650, 650);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres:");
		lblNumberOfOgres.setBounds(22, 38, 116, 20);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
		
		numberOgresField = new JTextField();
		numberOgresField.setBounds(148, 38, 95, 20);
		frmDungeonKeep.getContentPane().add(numberOgresField);
		numberOgresField.setColumns(10);
		numberOgresField.setText("3");
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality:");
		lblGuardPersonality.setBounds(22, 65, 116, 14);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
		
		JComboBox<String> guardTypeCombo = new JComboBox<String>();
		guardTypeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardTypeCombo.setToolTipText("Choose the Guard Personality");
		guardTypeCombo.setMaximumRowCount(3);
		guardTypeCombo.setBounds(148, 62, 95, 20);
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
		JButton btnLevelEditor = new JButton("Level Editor");
		JButton btnSave = new JButton("Save");
		JButton []buttonsToEdit={btnUp,btnLeft,btnRight,btnDown,btnLevelEditor,btnSave};

		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setMapAndStatusLabel("Up.", StatusLabel, buttonsToEdit,stopGame);			
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		btnUp.setEnabled(false);
		btnUp.setBounds(513, 273, 76, 23);
		frmDungeonKeep.getContentPane().add(btnUp);
		
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMapAndStatusLabel("Left.", StatusLabel, buttonsToEdit,stopGame);			
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(472, 307, 76, 23);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMapAndStatusLabel("Right.", StatusLabel, buttonsToEdit, stopGame);			
				gameArea.updateMap(play.getMap());
				frmDungeonKeep.requestFocus();
			}
		});
		btnRight.setEnabled(false);
		btnRight.setBounds(558, 307, 76, 23);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMapAndStatusLabel("Down.", StatusLabel, buttonsToEdit, stopGame);			
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
				enableDisableMoves(true, buttonsToEdit, stopGame);
				scan.close();
				frmDungeonKeep.requestFocus();
			}
		});
		btnNewGame.setBounds(502, 56, 95, 23);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(SLPlay.save(play))
					StatusLabel.setText("Game Saved with success.");
				else 
					StatusLabel.setText("Some error ocurred game didn't save.");
				frmDungeonKeep.requestFocus();
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(502, 95, 95, 23);
		frmDungeonKeep.getContentPane().add(btnSave);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play=SLPlay.load();
				if(play==null){
					StatusLabel.setText("Some erro ocurred, make sure you already saved a game once!");
					enableDisableMoves(false, buttonsToEdit, stopGame);
					return;
				}
				StatusLabel.setText("Game Loaded with success.");
				gameArea.updateMap(play.getMap());
				enableDisableMoves(true, buttonsToEdit, stopGame);
				frmDungeonKeep.requestFocus();
			}
		});
		btnLoad.setBounds(502, 132, 95, 23);
		frmDungeonKeep.getContentPane().add(btnLoad);
		
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
			if(stopGame[0])	
				return;
			String move=keysToType.get(e.getKeyCode());	
			if(move==null)
				return;
			
			setMapAndStatusLabel(move, StatusLabel, buttonsToEdit, stopGame);	
			gameArea.updateMap(play.getMap());
			frmDungeonKeep.requestFocus();
			return;
			}
		});

	}
	
	public JFrame getFrame(){
		return frmDungeonKeep;
	}
	
	public void enableDisableMoves(boolean isEnabled, JButton []Buttons, Boolean []stopGame){
		Buttons[0].setEnabled(isEnabled);
		Buttons[1].setEnabled(isEnabled);
		Buttons[2].setEnabled(isEnabled);
		Buttons[3].setEnabled(isEnabled);
		Buttons[4].setEnabled(!isEnabled);
		Buttons[5].setEnabled(isEnabled);
		stopGame[0]=!isEnabled;
	}
	
	public void setMapAndStatusLabel(String move, JLabel StatusLabel, JButton [] Buttons, Boolean []stopGame){
		int status;
		
		char keyTyped = moves.get(move);
		
		status=play.moveHeroWindow(keyTyped);
		
		if (status == 0){
			StatusLabel.setText("Hero moved " + move);
		}
		else if(status == -1){
			StatusLabel.setText("You lost.");
			enableDisableMoves(false, Buttons, stopGame);	
		}
		else if(status == 2){
			StatusLabel.setText("Next Level.");
		}
		else if (status==1){
			StatusLabel.setText("You win.");
			enableDisableMoves(false, Buttons, stopGame);	
		}
		return;
	}
	
	
}
