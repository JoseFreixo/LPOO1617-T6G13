package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import dkeep.logic.GameMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class LevelEditorWindow {

	private JFrame frame;
	private JTextField textLines;
	private JTextField textColumns;
	private GameMap map;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelEditorWindow window = new LevelEditorWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LevelEditorWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 615, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		LevelEditorPanel panel = new LevelEditorPanel();

		JButton btnWall = new JButton("Wall");
		JButton btnFloor = new JButton("Floor");
		JButton btnHero = new JButton("Hero");
		JButton btnOgre = new JButton("Ogre");
		JButton btnKey = new JButton("Key");
		JButton btnDoor = new JButton("Door");
		
		JLabel lblMapStatus = new JLabel("");
		
		JButton btnNewMap = new JButton("New Map");
		btnNewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int lines, columns;
				Scanner scan;
				try {
					scan = new Scanner(textLines.getText());
					lines = scan.nextInt();
					scan.close();
					scan = new Scanner(textColumns.getText());
					columns = scan.nextInt();
					if (lines < 5 || columns < 5 || lines > 15 || columns > 15){
						scan.close();
						throw new NoSuchElementException();
					}
				}
				catch (NoSuchElementException Excp){
					lblMapStatus.setText("The number of lines and columns must be between 5 and 15 (included).");
					return;
				}
				lblMapStatus.setText("");
				btnWall.setEnabled(true);
				btnFloor.setEnabled(true);
				btnHero.setEnabled(true);
				btnOgre.setEnabled(true);
				btnKey.setEnabled(true);
				btnDoor.setEnabled(true);
				scan.close();
				panel.newMap(lines, columns);
			}
		});
		btnNewMap.setBounds(472, 11, 89, 23);
		frame.getContentPane().add(btnNewMap);
		
		textLines = new JTextField();
		textLines.setBounds(110, 12, 86, 20);
		frame.getContentPane().add(textLines);
		textLines.setColumns(10);
		textLines.setText("5");
		
		JLabel lblLines = new JLabel("Lines:");
		lblLines.setBounds(65, 15, 35, 14);
		frame.getContentPane().add(lblLines);
		
		JLabel lblColumns = new JLabel("Columns:");
		lblColumns.setBounds(237, 15, 46, 14);
		frame.getContentPane().add(lblColumns);
		
		textColumns = new JTextField();
		textColumns.setColumns(10);
		textColumns.setBounds(296, 12, 86, 20);
		frame.getContentPane().add(textColumns);
		textColumns.setText("5");
		
		btnWall.setEnabled(false);
		btnWall.setBounds(472, 90, 89, 23);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.updateChar('X');
			}
		});
		frame.getContentPane().add(btnWall);
		
		btnFloor.setEnabled(false);
		btnFloor.setBounds(472, 124, 89, 23);
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar(' ');
			}
		});
		frame.getContentPane().add(btnFloor);
		
		btnHero.setEnabled(false);
		btnHero.setBounds(472, 158, 89, 23);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar('A');
			}
		});
		frame.getContentPane().add(btnHero);

		btnOgre.setEnabled(false);
		btnOgre.setBounds(472, 192, 89, 23);
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar('O');
			}
		});
		frame.getContentPane().add(btnOgre);

		btnKey.setEnabled(false);
		btnKey.setBounds(472, 226, 89, 23);
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar('k');
			}
		});
		frame.getContentPane().add(btnKey);
		
		btnDoor.setEnabled(false);
		btnDoor.setBounds(472, 260, 89, 23);
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar('I');
			}
		});
		frame.getContentPane().add(btnDoor);

		panel.setBounds(20, 60, 418, 418);
		frame.getContentPane().add(panel);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnDone.setBounds(472, 433, 89, 23);
		frame.getContentPane().add(btnDone);
		
		JButton btnSaveMap = new JButton("Save Map");
		btnSaveMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSaveMap.setBounds(472, 349, 89, 23);
		frame.getContentPane().add(btnSaveMap);
		
		lblMapStatus.setBounds(20, 34, 418, 25);
		frame.getContentPane().add(lblMapStatus);
	}
}
