package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.EnemyShipModel;

/**
 * Created by Ruben Torres on 26/05/2017.
 */

public class EnemyShip3Body extends EntityBody {

    public EnemyShip3Body(World world, EnemyShipModel model){
        super(world, model, true);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 238, height = 89;

        createFixture(body, new float[]{
                10,9, 49,4, 98,3, 156,10, 195,14, 221,20, 229,41, 164,41, 164,48, 228,48,
                222,69, 193,76, 157,79, 97,85, 51,84, 10,80
        }, width, height, density, friction, restitution, ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY));

    }
}
