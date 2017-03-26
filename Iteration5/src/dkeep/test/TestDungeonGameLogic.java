package dkeep.test;

import static org.junit.Assert.*;



import org.junit.Test;
import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.Ogre;
import dkeep.saveLoadMaps.DungeonMap;
import dkeep.saveLoadMaps.KeepMap;
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
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s'); // move hero down.
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		assertEquals('H', game.getMap().getMap()[2][1]);
		assertEquals(' ', game.getMap().getMap()[1][1]);
	}

	@Test
	public void testMoveHeroIntoToWall(){
		GameMap gameMap = new GameMap(map);
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('a'); // try to move hero to the right.
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
	}

	@Test
	public void testHeroIsCapturedByGuard(){
		GameMap gameMap = new GameMap(map);
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
		assertFalse(game.isGameOver());
		game.moveHero('d'); // move hero to the right.
		assertTrue(game.isGameOver());
		assertEquals(Game.DEFEAT, game.EndStatus());
	}

	@Test
	public void testHeroTriesToLeaveDoorsClosed(){
		GameMap gameMap = new GameMap(map);
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		game.moveHero('a');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
	}

	@Test
	public void testHeroIntoLeverAndDoorsOpen(){
		GameMap gameMap = new GameMap(map);
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		game.moveHero('s');
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
		assertEquals(true, game.areDoorsOpen());
	}

	@Test
	public void testHeroIsCapturedByOgre(){
		GameMap gameMap = new GameMap(map_for_task2);
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
		assertFalse(game.isGameOver());
		game.moveHero('d'); // move hero to the right.
		assertTrue(game.isGameOver());
		assertEquals(Game.DEFEAT, game.EndStatus());
	}
	@Test
	public void testHeroIntoKey(){
		GameMap gameMap = new GameMap(map_for_task2);
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
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
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
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
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
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
		GameMap gameMap = KeepMap.getKeepMap();
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
		assertEquals(game.getHeroPosition(), new CellPosition(7, 1));
		game.moveHero('d');
		assertEquals(game.getHeroPosition(), new CellPosition(7, 2));
		game.moveHero('d');
		assertEquals(game.getHeroPosition(), new CellPosition(7, 3));
		game.moveHero('d');
		assertEquals(game.getHeroPosition(), new CellPosition(7, 4));
		game.moveHero('d');
		assertEquals(game.getHeroPosition(), new CellPosition(7, 5));
		game.moveHero('d');
		assertEquals(game.getHeroPosition(), new CellPosition(7, 6));
		game.moveHero('d');
		assertEquals(game.getHeroPosition(), new CellPosition(7, 7));
		game.moveHero('w');
		assertEquals(game.getHeroPosition(), new CellPosition(6, 7));
		game.moveHero('w');
		assertEquals(game.getHeroPosition(), new CellPosition(5, 7));
		game.moveHero('w');
		assertEquals(game.getHeroPosition(), new CellPosition(4, 7));
		game.moveHero('w');
		assertEquals(game.getHeroPosition(), new CellPosition(3, 7));
		game.moveHero('w');
		assertEquals(game.getHeroPosition(), new CellPosition(2, 7));
		assertEquals('I', game.getMapChar(new CellPosition(1,0)));
		game.moveHero('w');
		assertEquals(game.getHeroPosition(), new CellPosition(1, 7));
		assertEquals('I', game.getMapChar(new CellPosition(1,0)));
		assertEquals('K', game.getHeroRepresentation());
		assertEquals(new CellPosition(0, 0), game.getLeverPosition());
		game.moveHero('s');
		assertEquals(game.getHeroPosition(), new CellPosition(2, 7));
		game.moveHero('s');
		assertEquals(game.getHeroPosition(), new CellPosition(3, 7));
		game.moveHero('a');
		assertEquals(game.getHeroPosition(), new CellPosition(3, 6));
		game.moveHero('a');
		assertEquals(game.getHeroPosition(), new CellPosition(3, 5));
		game.moveHero('a');
		assertEquals(game.getHeroPosition(), new CellPosition(3, 4));
		game.moveHero('a');
		assertEquals(game.getHeroPosition(), new CellPosition(3, 3));
		game.moveHero('a');
		assertEquals(game.getHeroPosition(), new CellPosition(3, 2));
		game.moveHero('a');
		assertEquals(game.getHeroPosition(), new CellPosition(3, 1));
		game.moveHero('w');
		assertEquals(game.getHeroPosition(), new CellPosition(2, 1));
		game.moveHero('w');
		assertEquals(game.getHeroPosition(), new CellPosition(1, 1));
		assertEquals('I', game.getMapChar(new CellPosition(1,0)));
		game.moveHero('a');
		assertEquals(game.getHeroPosition(), new CellPosition(1, 1));
		assertEquals('S', game.getMapChar(new CellPosition(1,0)));
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('a');
		assertEquals(false, game.EndStatus());
	}

	@Test
	public void testOgreMoviment() {
		GameMap gameMap = new GameMap(map_for_task2);
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
		Ogre ogre = game.getOgres().get(0);
		game.moveOgre(ogre);
		if (!ogre.getPosition().equals(new CellPosition(1, 2)) && !ogre.getPosition().equals(new CellPosition(2, 3)) && !ogre.getPosition().equals(new CellPosition(1, 3))) {
			fail("Ogre didn't move correctly");
		}
	}

	@Test
	public void testOgreSwing() {
		GameMap gameMap = new GameMap(map_for_task2);
		int [] t = { 0, 1 };
		Game game = new Game(gameMap, t);
		Ogre ogre = game.getOgres().get(0);
		game.ogreSwingClub(ogre);
		if (!ogre.getAttack().equals(new CellPosition(1, 2)) && !ogre.getAttack().equals(new CellPosition(2, 3))) {
			fail("Ogre didn't swing correctly");
		}
	}

	@Test
	public void testMoveGuardRookie() {
		int [] t = { 0, 1 };
		Game game = new Game(DungeonMap.getDungeonMap(), t);
		int i[] = { 0 };
		game.moveGuard(i);
		assertEquals(new CellPosition(1, 7), game.getGuardPosition());
		game.moveGuard(i);
		assertEquals(new CellPosition(2, 7), game.getGuardPosition());
		game.moveGuard(i);
		assertEquals(new CellPosition(3, 7), game.getGuardPosition());
		game.moveGuard(i);
		assertEquals(new CellPosition(4, 7), game.getGuardPosition());
		game.moveGuard(i);
		assertEquals(new CellPosition(5, 7), game.getGuardPosition());
		game.moveGuard(i);
		assertEquals(new CellPosition(5, 6), game.getGuardPosition());
		game.moveGuard(i);
		assertEquals(new CellPosition(5, 5), game.getGuardPosition());
		game.moveGuard(i);
		assertEquals(new CellPosition(5, 4), game.getGuardPosition());
	}

	@Test
	public void testMoveGuardDrunken(){
		int [] t = { 1, 1 };
		Game game = new Game (DungeonMap.getDungeonMap(), t);
		int i[] = { 0 };
		int m = 0;
		while (m < 100){
			game.moveGuard(i);
			if (game.getGuardRepresentation() == 'g'){
				break;
			}
			m++;
		}
		assertEquals(game.getGuardRepresentation(), 'g');
		assertEquals(game.getMap().getCharOnPos(game.getGuardPosition()), 'g');
		m = 0;
		while (m < 100){
			game.moveGuard(i);
			if (game.getGuardRepresentation() == 'G'){
				break;
			}
			m++;
		}
		assertEquals(game.getGuardRepresentation(), 'G');
		game = new Game (DungeonMap.getDungeonMap(), t);
		int j[] = { 0 };
		m = 0;
		assertEquals(game.getGuard().isFront(), true);
		while (m < 100){
			game.moveGuard(j);
			if (game.getGuardRepresentation() == 'g'){
				game.moveGuard(j);
				if (game.getGuard().isFront() == false)
					break;

			}
			m++;
		}
		assertEquals(game.getGuard().isFront(), false);
		m = 0;
		while (m < 100){
			game.moveGuard(j);
			if (game.getGuardRepresentation() == 'g'){
				game.moveGuard(j);
				if (game.getGuard().isFront() == true)
					break;

			}
			m++;
		}
		assertEquals(game.getGuard().isFront(), true);
	}

	@Test
	public void testMoveGuardSuspicious(){
		int [] t = { 2, 1 };
		Game game = new Game (DungeonMap.getDungeonMap(), t);
		int i[] = { 0 };
		boolean cond1 = false, cond2 = false;
		int m = 0;
		while (!cond1 || !cond2 || m > 100){
			game.moveGuard(i);
			if (game.getGuard().isFront()){
				cond1 = true;
				assertEquals(game.getGuard().isFront(), true);
			}
			else{
				cond2 = true;
				assertEquals(game.getGuard().isFront(), false);
			}
			m++;
		}
	}

	@Test
	public void testStunOgre(){
		GameMap map = new GameMap(map_for_task2);
		int [] t = { 0, 1 };
		Game game = new Game(map, t);
		assertEquals(game.getOgres().size(), 1);
		game.stunOgres();
		assertEquals(game.getOgres().get(0).getRepresentation(), 'O');
		game.moveHero('d');
		assertEquals(game.getHeroPosition(), new CellPosition(1, 2));
		game.stunOgres();
		assertEquals(game.getOgres().get(0).getRepresentation(), 'o');
		while(game.getOgres().get(0).getRepresentation() == 'o'){
			game.moveOgre(game.getOgres().get(0));
		}
		assertEquals(game.getOgres().get(0).getRepresentation(), 'O');
	}
}
