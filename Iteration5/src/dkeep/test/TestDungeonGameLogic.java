package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.KeepMap;
import dkeep.logic.Ogre;
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
		Game game = new Game(gameMap,0,1);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s'); // move hero down.
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroIntoToWall(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,0,1);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('a'); // try to move hero to the right.
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
	}
	
	@Test
	public void testHeroIsCapturedByGuard(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,0,1);
		assertFalse(game.isGameOver());
		game.moveHero('d'); // move hero to the right.
		assertTrue(game.isGameOver());
		assertEquals(Game.DEFEAT, game.EndStatus());
	}
	
	@Test
	public void testHeroTriesToLeaveDoorsClosed(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,0,1);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		game.moveHero('a');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testHeroIntoLeverAndDoorsOpen(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,0,1);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
		assertEquals(true, game.areDoorsOpen());
	}
	
	@Test
	public void testLeaveDungeon(){
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap,0,1);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
		game.moveHero('a');
		assertEquals(new KeepMap(), game.getMap());
	}
	
	//TASK 2
	@Test
	public void testHeroIsCapturedByOgre(){
		GameMap gameMap = new GameMap(map_for_task2);
		Game game = new Game(gameMap,0,1);
		assertFalse(game.isGameOver());
		game.moveHero('d'); // move hero to the right.
		assertTrue(game.isGameOver());
		assertEquals(Game.DEFEAT, game.EndStatus());
	}
	@Test
	public void testHeroIntoKey(){
		GameMap gameMap = new GameMap(map_for_task2);
		Game game = new Game(gameMap,0,1);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		assertEquals('A', game.getHeroRepresentation());
		game.moveHero('s');
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
		assertEquals('K', game.getHeroRepresentation());
	}
	@Test
	public void testHeroWithoutAKeyOpenDoors(){
		GameMap gameMap = new GameMap(map_for_task2);
		Game game = new Game(gameMap,0,1);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		game.moveHero('a');
		assertEquals('A', game.getHeroRepresentation());
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testeHeroIntoDoorsWithKey(){
		GameMap gameMap = new GameMap(map_for_task2);
		Game game = new Game(gameMap,0,1);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		assertEquals('A', game.getHeroRepresentation());
		game.moveHero('s');
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
		assertEquals('K', game.getHeroRepresentation());
		assertEquals(false, game.areDoorsOpen());
		assertEquals('I', game.getMapChar(new CellPosition(3,0)));
		game.moveHero('a');
		assertEquals('S', game.getMapChar(new CellPosition(3,0)));
		assertEquals(true, game.areDoorsOpen());
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
	}
	
	@Test
	public void testHeroWinGame(){
		GameMap gameMap = new KeepMap();
		Game game = new Game(gameMap,0,1);
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('w');
		game.moveHero('w');
		game.moveHero('w');
		game.moveHero('w');
		game.moveHero('w');
		assertEquals('I', game.getMapChar(new CellPosition(1,0)));
		game.moveHero('w');
		assertEquals('I', game.getMapChar(new CellPosition(1,0)));
		assertEquals('K', game.getHeroRepresentation());
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('a');
		game.moveHero('a');
		game.moveHero('a');
		game.moveHero('a');
		game.moveHero('a');
		game.moveHero('a');
		game.moveHero('w');
		game.moveHero('w');
		assertEquals('I', game.getMapChar(new CellPosition(1,0)));
		game.moveHero('a');
		assertEquals('S', game.getMapChar(new CellPosition(1,0)));
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('a');
		assertEquals(false, game.EndStatus());
	}
	
		@Test
		public void testOgreMoviment(){
			GameMap gameMap = new GameMap(map_for_task2);
			Game game = new Game(gameMap,0,1);
			Ogre ogre=game.getOgres().get(0);
			game.moveOgre(ogre);
			if(!ogre.getPosition().equals(new CellPosition(1,2))&&!ogre.getPosition().equals(new CellPosition(2,3))&&!ogre.getPosition().equals(new CellPosition(1,3))){
				fail("Ogre didn't move correctly");
			}
		}
		
		@Test
		public void testOgreSwing(){
			GameMap gameMap = new GameMap(map_for_task2);
			Game game = new Game(gameMap,0,1);
			Ogre ogre=game.getOgres().get(0);
			game.ogreSwingClub(ogre);
			if(!ogre.getAttack().equals(new CellPosition(1,2))&&!ogre.getAttack().equals(new CellPosition(2,3))){
				fail("Ogre didn't swing correctly");
			}
		}
		
		
}

