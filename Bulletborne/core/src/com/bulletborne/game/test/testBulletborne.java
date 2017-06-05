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
        GameModel model = GameModel.getInstance();
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
        GameModel model = GameModel.getInstance();
        GameController controller = GameController.getInstance();
        assertEquals(2,controller.getShipNumber());
    }

    @Test
    public void enemyTakingDamage(){
        GameController.getInstance().delete();
        GameController.setShipNumber(2);
        GameModel model = GameModel.getInstance();
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
}
