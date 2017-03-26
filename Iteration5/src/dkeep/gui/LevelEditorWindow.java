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
import java.util.Scanner;
import java.awt.event.ActionEvent;


public class LevelEditorWindow {

	private JFrame frame;
	LevelEditorPanel panel;
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

		setLvlEditPanel();

		JLabel [] Labels=new JLabel[NUMBER_OF_LABELS];
		setAllLabels(Labels);

		JButton[] Buttons= new JButton[NUMBER_OF_BUTTONS];
		initializeAllButtons(Buttons);
		setBT_NEWMAP(Buttons,Labels);
		setDrawButtonsAndDone(Buttons);
		setBT_RESET(Buttons,Labels);
		setBT_ADDMAP(Buttons,Labels);
		setBT_REPLACELVL(Buttons,Labels);

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

	private void setLvlEditPanel(){
		panel=new LevelEditorPanel();
		panel.setBounds(20, 60, 418, 418);
		frame.getContentPane().add(panel);
		return;
	}

	private void initializeAllButtons(JButton [] Buttons){
		Buttons[BT_WALL] = new JButton("Wall");
		Buttons[BT_FLOOR] = new JButton("Floor");
		Buttons[BT_HERO] = new JButton("Hero");
		Buttons[BT_OGRE] = new JButton("Ogre");
		Buttons[BT_KEY] = new JButton("Key");
		Buttons[BT_DOOR] = new JButton("Door");
		Buttons[BT_NEWMAP] = new JButton("New Map");
		Buttons[BT_ADDMAP] = new JButton("Add Map");
		Buttons[BT_REPLACELVL] = new JButton("Replace");
		Buttons[BT_RESET] = new JButton("Reset Maps");
		Buttons[BT_DONE] = new JButton("Done");
	}

	private void enableDisableButtons(boolean isEnabled, JButton []Buttons){
		Buttons[BT_WALL].setEnabled(isEnabled);
		Buttons[BT_FLOOR].setEnabled(isEnabled);
		Buttons[BT_HERO].setEnabled(isEnabled);
		Buttons[BT_OGRE].setEnabled(isEnabled);
		Buttons[BT_KEY].setEnabled(isEnabled);
		Buttons[BT_DOOR].setEnabled(isEnabled);
	}	
	
	private boolean isCorrectLinesColumns(int lines, int columns){
		return (lines < 5 || columns < 5 || lines > 15 || columns > 15);
	}
	private void setBT_NEWMAP(JButton []Buttons,JLabel[] Labels){
		Buttons[BT_NEWMAP].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int lines, columns;
				Scanner scan;
				try {
					scan = new Scanner(textLines.getText());
					lines = scan.nextInt();
					scan.close();
					scan = new Scanner(textColumns.getText());
					columns = scan.nextInt();
					if (isCorrectLinesColumns(lines,columns)){
						scan.close();
						Labels[LBL_MAPSTATUS].setText("The number of lines and columns must be between 5 and 15 (included).");
					}
				}
				catch (NoSuchElementException Excp){
					Labels[LBL_MAPSTATUS].setText("The number of lines and columns must be between 5 and 15 (included).");
					return;
				}
				Labels[LBL_MAPSTATUS].setText("");
				enableDisableButtons(true,Buttons);
				scan.close();
				panel.newMap(lines, columns);
			}
		});
		Buttons[BT_NEWMAP].setBounds(472, 11, 89, 23);
		frame.getContentPane().add(Buttons[BT_NEWMAP]);
	}

	private void setBT_WALL(JButton []Buttons){
		Buttons[BT_WALL].setEnabled(false);
		Buttons[BT_WALL].setBounds(472, 90, 89, 23);
		Buttons[BT_WALL].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.updateChar('X');
			}
		});
		frame.getContentPane().add(Buttons[BT_WALL]);
	}

	private void setBT_FLOOR(JButton []Buttons){
		Buttons[BT_FLOOR].setEnabled(false);
		Buttons[BT_FLOOR].setBounds(472, 124, 89, 23);
		Buttons[BT_FLOOR].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar(' ');
			}
		});
		frame.getContentPane().add(Buttons[BT_FLOOR]);
	}

	private void setBT_HERO(JButton []Buttons){
		Buttons[BT_HERO].setEnabled(false);
		Buttons[BT_HERO].setBounds(472, 158, 89, 23);
		Buttons[BT_HERO].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar('A');
			}
		});
		frame.getContentPane().add(Buttons[BT_HERO]);
	}

	private void setBT_OGRE(JButton []Buttons){
		Buttons[BT_OGRE].setEnabled(false);
		Buttons[BT_OGRE].setBounds(472, 192, 89, 23);
		Buttons[BT_OGRE].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar('O');
			}
		});
		frame.getContentPane().add(Buttons[BT_OGRE]);
	}

	private void setBT_KEY(JButton []Buttons){
		Buttons[BT_KEY].setEnabled(false);
		Buttons[BT_KEY].setBounds(472, 226, 89, 23);
		Buttons[BT_KEY].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar('k');
			}
		});
		frame.getContentPane().add(Buttons[BT_KEY]);
	}

	private void setBT_DOOR(JButton []Buttons){
		Buttons[BT_DOOR].setEnabled(false);
		Buttons[BT_DOOR].setBounds(472, 260, 89, 23);
		Buttons[BT_DOOR].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.updateChar('I');
			}
		});
		frame.getContentPane().add(Buttons[BT_DOOR]);
	}

	private void setBT_DONE(JButton []Buttons){
		Buttons[BT_DONE].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				gameWindow.getFrame().setVisible(true);
			}
		});
		Buttons[BT_DONE].setBounds(472, 433, 89, 23);
		frame.getContentPane().add(Buttons[BT_DONE]);
	}
	private void setBT_RESET(JButton []Buttons,JLabel[] Labels){
		Buttons[BT_RESET].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SLMaps.resetMaps();
				Labels[LBL_MAPSTATUS].setText("Maps reseted to the original ones successfully!!");
			}
		});
		Buttons[BT_RESET].setBounds(472, 45, 89, 23);
		frame.getContentPane().add(Buttons[BT_RESET]);
	}

	private void setBT_ADDMAP(JButton []Buttons,JLabel[] Labels){
		Buttons[BT_ADDMAP].addActionListener(new ActionListener() {
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
				else Labels[LBL_MAPSTATUS].setText("Map not added. Perhaps you added more than one key/Ogre/Hero sprite!");	
			}
		});
		Buttons[BT_ADDMAP].setBounds(472, 312, 89, 23);
		frame.getContentPane().add(Buttons[BT_ADDMAP]);
	}
	
	private void ReplaceLvl(JLabel[] Labels){
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
	
	private void setBT_REPLACELVL(JButton []Buttons,JLabel[] Labels){
		Buttons[BT_REPLACELVL].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.verifyMap()){
					 ReplaceLvl(Labels);
				}
				else Labels[LBL_MAPSTATUS].setText("Map not added. Perhaps you added more than one key/Ogre/Hero sprite, or you forgot the Walls/Door around the map!");	
			}
		});
		Buttons[BT_REPLACELVL].setBounds(472, 345, 89, 23);
		frame.getContentPane().add(Buttons[BT_REPLACELVL]);
	}
	
	private void setDrawButtonsAndDone(JButton[] Buttons){
		setBT_WALL(Buttons);
		setBT_FLOOR(Buttons);
		setBT_HERO(Buttons);
		setBT_OGRE(Buttons);
		setBT_KEY(Buttons);
		setBT_DOOR(Buttons);
		setBT_DONE(Buttons);
	}
}