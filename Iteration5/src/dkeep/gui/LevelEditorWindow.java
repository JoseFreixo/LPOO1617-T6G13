package dkeep.gui;



import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import dkeep.cli.Play;
import dkeep.logic.GameMap;
import dkeep.saveLoadMaps.SLMaps;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;


public class LevelEditorWindow {

	private JFrame frame;
	private JTextField textLines;
	private JTextField textColumns;
	private GameWindow gameWindow;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public LevelEditorWindow(Play currentGame) {
		initialize(currentGame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Play currentGame) {
		gameWindow = new GameWindow();
		gameWindow.getFrame().setVisible(false);
		
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
		textField = new JTextField();
		
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
				frame.dispose();
				gameWindow.getFrame().setVisible(true);
			}
		});
		btnDone.setBounds(472, 433, 89, 23);
		frame.getContentPane().add(btnDone);
		
		JButton btnAddMap = new JButton("Add Map");
		btnAddMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.verifyMap()){
					ArrayList<GameMap> maps = SLMaps.getMaps();
					maps.add(panel.returnMap());
					SLMaps.setMaps(maps);
					lblMapStatus.setText("Map added successfuly!");
				}
				else{
					Random rand = new Random();
					int r = rand.nextInt(3);
					if (r == 0)
						lblMapStatus.setText("Map not added. Perhaps you forgot the walls/door around the map?");
					if (r == 1)
						lblMapStatus.setText("Map not added. Perhaps you added more than one ogre/hero sprite.");
					if (r == 2)
						lblMapStatus.setText("Map not added. Perhaps you added more than one key sprite.");
				}
			}
		});
		btnAddMap.setBounds(472, 312, 89, 23);
		frame.getContentPane().add(btnAddMap);
		
		lblMapStatus.setBounds(20, 34, 418, 25);
		frame.getContentPane().add(lblMapStatus);
		
		JButton btnReplaceWithLevel = new JButton("Replace");
		btnReplaceWithLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.verifyMap()){
					try {
						Scanner scan = new Scanner(textField.getText());
						int index = scan.nextInt() - 1;
						scan.close();
						ArrayList<GameMap> maps = SLMaps.getMaps();
						if (maps == null){
							JOptionPane.showMessageDialog(null, "Maps file corruped!");
							System.exit(1);
						}
						if (index > maps.size() || index < 1)
							throw new NoSuchElementException();
						maps.set(index, panel.returnMap());
						SLMaps.setMaps(maps);
						lblMapStatus.setText("Map replaced successfuly!");
					}
					catch (NoSuchElementException exception){
						lblMapStatus.setText("Not a number or the level you want to replace does not exist!");
					}
				}
				else{
					Random rand = new Random();
					int r = rand.nextInt(3);
					if (r == 0)
						lblMapStatus.setText("Map not replaced. Perhaps you forgot the walls/door around the map?");
					if (r == 1)
						lblMapStatus.setText("Map not replaced. Perhaps you added more than one ogre/hero sprite.");
					if (r == 2)
						lblMapStatus.setText("Map not replaced. Perhaps you added more than one key sprite.");
				}
			}
		});
		btnReplaceWithLevel.setBounds(472, 345, 89, 23);
		frame.getContentPane().add(btnReplaceWithLevel);
		
		textField.setText("2");
		textField.setColumns(10);
		textField.setBounds(538, 367, 23, 20);
		frame.getContentPane().add(textField);
		
		JLabel lblWithLevel = new JLabel("With level:");
		lblWithLevel.setBounds(472, 370, 58, 14);
		frame.getContentPane().add(lblWithLevel);
		
		frame.setVisible(true);
		
	}
}
