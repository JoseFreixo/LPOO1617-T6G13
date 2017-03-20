package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dkeep.cli.*;
import dkeep.logic.*;

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
		
		JLabel lblNewLabel = new JLabel("You can start a new game.");
		lblNewLabel.setBounds(22, 341, 400, 21);
		frmDungeonKeep.getContentPane().add(lblNewLabel);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<GameMap> mapas = new ArrayList<GameMap>();
				mapas.add(new DungeonMap());
				mapas.add(new KeepMap());
				
				int nOgres;
				
				try {
					Scanner scan = new Scanner(numberOgresField.getText());
					nOgres = scan.nextInt();
					if (nOgres <= 0)
						throw new NoSuchElementException();
				}
				catch (NoSuchElementException Excp){
					lblNewLabel.setText("The number of ogres must be between 1 and 5.");
					return;
				}
				
				String guardType = ((String)guardTypeCombo.getSelectedItem());
				
				lblNewLabel.setText("Game is Running in the 90's");
				play = new Play(mapas);
				textArea.setText(play.getMapInString());
			}
		});
		btnNewGame.setBounds(311, 45, 95, 23);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		JButton btnExitGame = new JButton("Exit");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitGame.setBounds(311, 307, 95, 23);
		frmDungeonKeep.getContentPane().add(btnExitGame);
		
		JButton btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setBounds(326, 151, 59, 23);
		frmDungeonKeep.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(284, 185, 59, 23);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(363, 185, 59, 23);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(326, 219, 59, 23);
		frmDungeonKeep.getContentPane().add(btnDown);
		
	}
}
