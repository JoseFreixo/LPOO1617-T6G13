package com.bulletborne.game.test;

import com.bulletborne.game.Bulletborne;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.bulletborne.game.controller.*;
import com.bulletborne.game.controller.entities.EnemyShip1Body;
import com.bulletborne.game.controller.entities.Player2Body;
import com.bulletborne.game.model.*;
import com.bulletborne.game.Bulletborne;
import com.bulletborne.game.model.entities.EnemyShipModel;
import com.bulletborne.game.model.entities.EntityModel;


/**
 * Created by Zé on 05/06/2017.
 */

public class testBulletborne {

    @Test
    public void testLose(){
        GameController.getInstance().delete();
        GameController.setShipNumber(1);
        GameController controller = GameController.getInstance();
        boolean lose = false;
        while (!lose){
            controller.update(0.25f);
            if (controller.getLost())
                lose = true;
            if (controller.getTimePast() > 5)
                assertTrue(false);
        }
        assertTrue(lose);
    }
    @Test
    public void useShip2(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameController controller = GameController.getInstance();
        assertEquals(2,controller.getShipNumber());
    }

    @Test
    public void enemyTakingDamage(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameController controller = GameController.getInstance();

        EnemyShipModel enem1Model = new EnemyShipModel(50f, 12f, 0, EntityModel.ModelType.ENEMY_SHIP_NORMAL);
        EnemyShipModel enem2Model = new EnemyShipModel(50f, 12f, 0, EntityModel.ModelType.ENEMY_SHIP_GLASSCANNON);
        EnemyShipModel enem3Model = new EnemyShipModel(50f, 12f, 0, EntityModel.ModelType.ENEMY_SHIP_TANK);

        assertEquals(3, enem1Model.getHP());
        controller.enemyKilled(enem1Model);
        assertEquals(2, enem1Model.getHP());

        assertEquals(5, enem3Model.getHP());
        controller.enemyKilled(enem3Model);
        assertEquals(4, enem3Model.getHP());

        assertEquals(1, enem2Model.getHP());
        controller.enemyKilled(enem2Model);
        assertEquals(0, enem2Model.getHP());

        assertTrue(enem2Model.isFlaggedToBeRemoved());
    }

    @Test
    public void generateAllKindsOfEnemies(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameModel model = GameModel.getInstance();
        GameController controller = GameController.getInstance();
        int tries = 0;
        boolean enemy1spawned = false, enemy2spawned = false, enemy3spawned = false;
        do {
            tries++;
            controller.setTimeToNextEnemy(-1);
            int i = controller.generateEnemy();
            if (i == 1)
                enemy1spawned = true;
            else if (i == 2)
                enemy2spawned = true;
            else
                enemy3spawned = true;
        } while (tries <= 10);
        assertTrue(enemy1spawned);
        assertTrue(enemy2spawned);
        assertTrue(enemy3spawned);
    }

    @Test
    public void testPlayerShoot(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);

        assertEquals(GameController.getInstance().getPlayerBody().shoot().size(),3);

        GameController.getInstance().delete();
        GameController.setShipNumber(1);

        assertEquals(GameController.getInstance().getPlayerBody().shoot().size(),2);
    }

    @Test
    public void testPlayerGoUp(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameController.getInstance().update(3f);
        float X_before = GameController.getInstance().getPlayerBody().getX();
        float Y_before = GameController.getInstance().getPlayerBody().getY();
        float Ang_before = GameController.getInstance().getPlayerBody().getAngle();

        GameController.getInstance().update(0.25f);
        GameController.getInstance().goUp(0.5f);
        GameController.getInstance().update(0.25f);
        assertTrue(X_before==GameController.getInstance().getPlayerBody().getX());
        assertTrue(Y_before<GameController.getInstance().getPlayerBody().getY());
        assertTrue(Ang_before<GameController.getInstance().getPlayerBody().getAngle());
    }

    @Test
    public void testPlayerGoingDown(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameController.getInstance().update(3f);
        float X_before = GameController.getInstance().getPlayerBody().getX();
        float Y_before = GameController.getInstance().getPlayerBody().getY();
        float Ang_before = GameController.getInstance().getPlayerBody().getAngle();

        GameController.getInstance().update(0.25f);
        assertTrue(X_before==GameController.getInstance().getPlayerBody().getX());
        assertTrue(Y_before>GameController.getInstance().getPlayerBody().getY());
        assertTrue(Ang_before>GameController.getInstance().getPlayerBody().getAngle());
    }

    @Test
    public void setAndGetTimetoNextEnemy(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameController.getInstance().setTimeToNextEnemy(-1);
        assertEquals(-1,(int)GameController.getInstance().getTimeToNextEnemy());
    }

    @Test
    public void testEnemyKilled(){
        EnemyShipModel ship= GameModel.getInstance().createEnemy();
        int hp_before=ship.getHP();
        GameController.getInstance().enemyKilled(ship);
        assertEquals(hp_before-1,ship.getHP());
        ship.DamageTaken(100);
        hp_before=hp_before-1-100;
        GameController.getInstance().enemyKilled(ship);
        assertEquals(hp_before-1,ship.getHP());
        assertTrue(ship.isFlaggedToBeRemoved());
    }

    @Test
    public void testLost(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameController.getInstance().update(3f);
        assertTrue(!GameController.getInstance().getLost());
        GameController.getInstance().update(4f);
        assertTrue(GameController.getInstance().getLost());
    }

    @Test
    public void testIncreasingDifficulty(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameController.getInstance().update(3f);
        float time1=GameController.getTimeBetweenEnemies();
        GameController.getInstance().update(10f);
        float time2=GameController.getTimeBetweenEnemies();
        assertTrue(time1>time2);
        GameController.getInstance().update(100f);
        time1=GameController.getTimeBetweenEnemies();
        assertTrue(time2>time1);
    }

    @Test
    public void testTimePassed(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameController.getInstance().update(3f);
        assertEquals(3,(int)GameController.getInstance().getTimePast());
        GameController.getInstance().update(100f);
        assertEquals(103,(int)GameController.getInstance().getTimePast());
    }

}
