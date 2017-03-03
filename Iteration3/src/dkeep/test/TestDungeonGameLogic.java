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
}
