package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dkeep.cli.*;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.DropMode;

public class GameWindow {

	private JFrame frmDungeonKeep;
	private JTextField numberOgresField;
	private Play play;

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
		frmDungeonKeep.setBounds(100, 100, 450, 412);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres:");
		lblNumberOfOgres.setBounds(22, 19, 97, 36);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
		
		numberOgresField = new JTextField();
		numberOgresField.setBounds(129, 27, 95, 20);
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
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBounds(22, 95, 235, 235);
		frmDungeonKeep.getContentPane().add(textArea);
		
		JLabel StatusLabel = new JLabel("You can start a new game.");
		StatusLabel.setBounds(22, 346, 400, 21);
		frmDungeonKeep.getContentPane().add(StatusLabel);
		
		JButton btnUp = new JButton("Up");
		JButton btnLeft = new JButton("Left");
		JButton btnRight = new JButton("Right");
		JButton btnDown = new JButton("Down");
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int status = play.moveHeroWindow('W');
				setMapAndStatusLabel(status,"Up.", StatusLabel, textArea,
						btnUp, btnDown, btnLeft, btnRight);
			}
		});
		btnUp.setEnabled(false);
		btnUp.setBounds(309, 151, 76, 23);
		frmDungeonKeep.getContentPane().add(btnUp);
		
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = play.moveHeroWindow('A');
				setMapAndStatusLabel(status,"Left.", StatusLabel, textArea,
						btnUp, btnDown, btnLeft, btnRight);
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(267, 185, 76, 23);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = play.moveHeroWindow('D');
				setMapAndStatusLabel(status,"Right.", StatusLabel, textArea,
						btnUp, btnDown, btnLeft, btnRight);
			}
		});
		btnRight.setEnabled(false);
		btnRight.setBounds(353, 185, 76, 23);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = play.moveHeroWindow('S');
				setMapAndStatusLabel(status,"Down.", StatusLabel, textArea,
						btnUp, btnDown, btnLeft, btnRight);
			}
		});
		btnDown.setEnabled(false);
		btnDown.setBounds(309, 219, 76, 23);
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
				textArea.setText(play.getMapInString());
				enableDisableMoves(true, btnUp, btnDown, btnLeft, btnRight);
				scan.close();
				
			}
		});
		btnNewGame.setBounds(300, 45, 95, 23);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		JButton btnExitGame = new JButton("Exit");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitGame.setBounds(300, 307, 95, 23);
		frmDungeonKeep.getContentPane().add(btnExitGame);

	}
	
	public void enableDisableMoves(boolean isEnabled, JButton btnUp, JButton btnDown, JButton btnLeft, JButton btnRight){
		btnUp.setEnabled(isEnabled);
		btnDown.setEnabled(isEnabled);
		btnLeft.setEnabled(isEnabled);
		btnRight.setEnabled(isEnabled);
	}
	
	public void setMapAndStatusLabel(int status,String move, JLabel StatusLabel, JTextArea textArea,
			JButton btnUp, JButton btnDown, JButton btnLeft, JButton btnRight){
		
		if (status == 0){
			StatusLabel.setText("Hero moved " + move);
		}
		else if(status == -1){
			StatusLabel.setText("You lost.");
			enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);
		}
		else if(status == 2){
			StatusLabel.setText("Next Level.");
		}
		else{
			StatusLabel.setText("You win.");
			enableDisableMoves(false, btnUp, btnDown, btnLeft, btnRight);
		}
		textArea.setText(play.getMapInString());
	}
}
