package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.DropMode;

public class GameWindow {

	private JFrame frmDungeonKeep;
	private JTextField textField;

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
		
		textField = new JTextField();
		textField.setBounds(129, 27, 95, 20);
		frmDungeonKeep.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality:");
		lblGuardPersonality.setBounds(22, 65, 97, 14);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
		

		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setToolTipText("Choose the Guard Personality");
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(129, 62, 95, 20);
		frmDungeonKeep.getContentPane().add(comboBox);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewGame.setBounds(311, 45, 95, 23);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		JButton btnExitGame = new JButton("Exit");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExitGame.setBounds(311, 307, 95, 23);
		frmDungeonKeep.getContentPane().add(btnExitGame);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBounds(22, 95, 235, 235);
		frmDungeonKeep.getContentPane().add(textArea);
		
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
		btnLeft.setBounds(289, 185, 59, 23);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(358, 185, 59, 23);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(326, 219, 59, 23);
		frmDungeonKeep.getContentPane().add(btnDown);
		
		JLabel lblNewLabel = new JLabel("You can start a new game.");
		lblNewLabel.setBounds(22, 341, 235, 21);
		frmDungeonKeep.getContentPane().add(lblNewLabel);
	}
}
