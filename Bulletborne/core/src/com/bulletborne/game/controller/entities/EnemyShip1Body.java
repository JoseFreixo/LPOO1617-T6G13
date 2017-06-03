package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.EnemyShipModel;

/**
 * A concrete representation of an EntityBody representing a NORMAL enemy ship
 */

public class EnemyShip1Body extends EntityBody {

    /**
     * Constructs a Normal ship body according to
     * a EnemyShip model.
     *
     * @param world the physical world this asteroid belongs to.
     * @param model the model representing this asteroid.
     */
    public EnemyShip1Body(World world, EnemyShipModel model){
        super(world, model, BodyTypeDef.KINEMATIC);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 202, height = 144;

        //Upper Wing
        createFixture(body,
                new float[]{0,8, 17,0, 39,2, 160,36, 201,62, 201,68, 6,62},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});

        //Body
        createFixture(body,
                new float[]{6,62, 128,68, 128,75, 6,80},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});

        //Lower Wing
        createFixture(body,
                new float[]{0,134, 17,143, 40,142, 160,108, 201,82, 201,75, 6, 80},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});
    }

    /**
     * Sets the Linear Velocity.x of this ship to a value given as a parameter
     * @param velocity the new linear velocity.xfor this body
     */
    @Override
    public void setLinearVelocity(float velocity) {
        body.setLinearVelocity(velocity, 0);
    }
}
