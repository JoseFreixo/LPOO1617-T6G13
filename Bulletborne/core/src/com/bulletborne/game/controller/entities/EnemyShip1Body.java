package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.EnemyShipModel;

/**
 * Created by r_tor on 26/05/2017.
 */

public class EnemyShip1Body extends EntityBody {

    public EnemyShip1Body(World world, EnemyShipModel model){
        super(world, model, true);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 202, height = 144;

        //Upper Wing
        createFixture(body, new float[]{
                0,8, 17,0, 39,2, 160,36, 201,62, 201,68, 6,62
        }, width, height, density, friction, restitution, ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY));

        //Body
        createFixture(body, new float[]{
                6,62, 128,68, 128,75, 6,80
        }, width, height, density, friction, restitution, ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY));

        //Lower Wing
        createFixture(body, new float[]{
                0,134, 17,143, 40,142, 160,108, 201,82, 201,75, 6, 80
        }, width, height, density, friction, restitution, ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY));
    }
}
