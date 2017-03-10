package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.CellPosition;
	
public class TestDungeonGameLogic {
	char[][] map = {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
	
	char[][] map_for_task2 = {{'X','X','X','X','X'},
							  {'X','H',' ','O','X'},
							  {'I',' ',' ',' ','X'},
							  {'I','k',' ',' ','X'},
							  {'X','X','X','X','X'}};
	@Test
	public void testMoveHeroIntoToFreeCell(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s'); // move hero down.
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroIntoToWall(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('a'); // try to move hero to the right.
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
	}
	
	@Test
	public void testHeroIsCapturedByGuard(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		assertFalse(game.isGameOver());
		game.moveHero('d'); // move hero to the right.
		assertTrue(game.isGameOver());
		assertEquals(Game.DEFEAT, game.EndStatus());
	}
	
	@Test
	public void testHeroTriesToLeaveDoorsClosed(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		game.moveHero('a');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testHeroIntoLeverAndDoorsOpen(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
		game.moveHero('a');
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
		assertEquals(true, game.areDoorsOpen());
	}
	
	//TASK 2
	@Test
	public void testHeroIsCapturedByOgre(){
		GameMap gameMap = new GameMap(map_for_task2);
		Game game = new Game(gameMap);
		assertFalse(game.isGameOver());
		game.moveHero('d'); // move hero to the right.
		assertTrue(game.isGameOver());
		assertEquals(Game.DEFEAT, game.EndStatus());
	}
	
}
