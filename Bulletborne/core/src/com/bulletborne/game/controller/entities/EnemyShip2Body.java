package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.EnemyShipModel;

/**
 * Created by Ruben Torres on 26/05/2017.
 */

public class EnemyShip2Body extends EntityBody {
    public EnemyShip2Body(World world, EnemyShipModel model){
        super(world, model, BodyTypeDef.KINEMATIC);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 258, height = 170;

        //Upper Top Wing
        createFixture(body,
                new float[]{1,49, 27,7, 71,2, 78,19, 188,25, 196,36},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});

        //Upper Bottom Wing
        createFixture(body,
                new float[]{1,49, 218,38, 241,76, 1,79},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});

        //Center
        createFixture(body,
                new float[]{241,76, 1,79, 1,98, 241,93},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});

        //Bottom Bottom Wing
        createFixture(body,
                new float[]{1,79, 241,93, 219,132, 10,133},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});

        //Bottom Bottom Wing
        createFixture(body,
                new float[]{10,133, 198,132, 186,145, 77,151, 71,168, 25,163},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});
    }

    @Override
    public void setLinearVelocity(float velocity) {
        body.setLinearVelocity(velocity, 0);
    }
}
