package dkeep.gui;



import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
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

	private static final int NUMBER_OF_BUTTONS= 11;
	private static final int BT_WALL= 0;
	private static final int BT_FLOOR= 1;
	private static final int BT_HERO= 2;
	private static final int BT_OGRE= 3;
	private static final int BT_KEY= 4;
	private static final int BT_DOOR= 5;
	private static final int BT_NEWMAP= 6;
	private static final int BT_DONE= 7;
	private static final int BT_ADDMAP= 8;
	private static final int BT_REPLACELVL= 9;
	private static final int BT_RESET= 10;
	
	private static final int NUMBER_OF_LABELS= 4;
	private static final int LBL_LINES= 0;
	private static final int LBL_COLUMNS= 1;
	private static final int LBL_LEVEL= 2;
	private static final int LBL_MAPSTATUS= 3;
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

		setGameWindowAndJFrame();
		setTextLinesAFieldAndColumns();
		
		LevelEditorPanel panel = setLvlEditPanel();

		JLabel [] Labels=new JLabel[NUMBER_OF_LABELS];
		setAllLabels(Labels);



		JButton btnWall = new JButton("Wall");
		JButton btnFloor = new JButton("Floor");
		JButton btnHero = new JButton("Hero");
		JButton btnOgre = new JButton("Ogre");
		JButton btnKey = new JButton("Key");
		JButton btnDoor = new JButton("Door");

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
					Labels[LBL_MAPSTATUS].setText("The number of lines and columns must be between 5 and 15 (included).");
					return;
				}
				Labels[LBL_MAPSTATUS].setText("");
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

					if(maps==null){
						JOptionPane.showMessageDialog(null, "Maps file corruped! \nReseting maps file to the original one\nand adding the one you created!");
						SLMaps.resetMaps();
						maps = SLMaps.getMaps();
					}

					maps.add(panel.returnMap());
					SLMaps.setMaps(maps);
					Labels[LBL_MAPSTATUS].setText("Map added successfuly!");
				}
				else{
					Random rand = new Random();
					int r = rand.nextInt(3);
					if (r == 0)
						Labels[LBL_MAPSTATUS].setText("Map not added. Perhaps you forgot the walls/door around the map?");
					if (r == 1)
						Labels[LBL_MAPSTATUS].setText("Map not added. Perhaps you added more than one ogre/hero sprite.");
					if (r == 2)
						Labels[LBL_MAPSTATUS].setText("Map not added. Perhaps you added more than one key sprite.");
				}
			}
		});
		btnAddMap.setBounds(472, 312, 89, 23);
		frame.getContentPane().add(btnAddMap);


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
							JOptionPane.showMessageDialog(null, "Maps file corruped! Reseting maps file to the original one and replace the level you choose!");
							SLMaps.resetMaps();
							maps = SLMaps.getMaps();
						}
						if (index > maps.size() || index < 1)
							throw new NoSuchElementException();
						maps.set(index, panel.returnMap());
						SLMaps.setMaps(maps);
						Labels[LBL_MAPSTATUS].setText("Map replaced successfuly!");
					}
					catch (NoSuchElementException exception){
						Labels[LBL_MAPSTATUS].setText("Not a number or the level you want to replace does not exist!");
					}
				}
				else{
					Random rand = new Random();
					int r = rand.nextInt(3);
					if (r == 0)
						Labels[LBL_MAPSTATUS].setText("Map not replaced. Perhaps you forgot the walls/door around the map?");
					if (r == 1)
						Labels[LBL_MAPSTATUS].setText("Map not replaced. Perhaps you added more than one ogre/hero sprite.");
					if (r == 2)
						Labels[LBL_MAPSTATUS].setText("Map not replaced. Perhaps you added more than one key sprite.");
				}
			}
		});
		btnReplaceWithLevel.setBounds(472, 345, 89, 23);
		frame.getContentPane().add(btnReplaceWithLevel);

		JButton btnResetMap = new JButton("Reset Maps");
		btnResetMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SLMaps.resetMaps();
				Labels[LBL_MAPSTATUS].setText("Maps reseted to the original ones successfully!!");
			}
		});
		btnResetMap.setBounds(472, 45, 89, 23);
		frame.getContentPane().add(btnResetMap);

	}


	private void setGameWindowAndJFrame(){
		gameWindow = new GameWindow();
		gameWindow.getFrame().setVisible(false);

		frame = new JFrame();
		frame.setBounds(100, 100, 615, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	}
	
	private void setTextLinesAFieldAndColumns(){
		textLines = new JTextField();
		textLines.setBounds(110, 12, 86, 20);
		textLines.setColumns(10);
		textLines.setText("5");
		frame.getContentPane().add(textLines);
		
		textField = new JTextField();
		textField.setText("2");
		textField.setColumns(10);
		textField.setBounds(538, 367, 23, 20);
		frame.getContentPane().add(textField);
		
		textColumns = new JTextField();
		textColumns.setColumns(10);
		textColumns.setBounds(296, 12, 86, 20);
		textColumns.setText("5");
		frame.getContentPane().add(textColumns);
	}
	
	private void setAllLabels(JLabel[] Labels){
		Labels[LBL_LINES] = new JLabel("Lines:");
		Labels[LBL_LINES].setBounds(65, 15, 35, 14);
		frame.getContentPane().add(Labels[LBL_LINES]);

		Labels[LBL_COLUMNS] = new JLabel("Columns:");
		Labels[LBL_COLUMNS].setBounds(237, 15, 46, 14);
		frame.getContentPane().add(Labels[LBL_COLUMNS]);

		Labels[LBL_LEVEL] = new JLabel("With level:");
		Labels[LBL_LEVEL].setBounds(472, 370, 58, 14);
		frame.getContentPane().add(Labels[LBL_LEVEL]);

		Labels[LBL_MAPSTATUS] = new JLabel("");
		Labels[LBL_MAPSTATUS].setBounds(20, 34, 418, 25);
		frame.getContentPane().add(Labels[LBL_MAPSTATUS]);
	}
	
	private LevelEditorPanel setLvlEditPanel(){
		LevelEditorPanel panel=new LevelEditorPanel();
		panel.setBounds(20, 60, 418, 418);
		frame.getContentPane().add(panel);
		return panel;
	}
}