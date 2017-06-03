package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.EnemyShipModel;

/**
 * A concrete representation of an EntityBody representing a GLASSCANNON enemy ship
 */
public class EnemyShip3Body extends EntityBody {

    /**
     * Constructs a Glass Cannon ship body according to
     * a EnemyShip model.
     *
     * @param world the physical world this asteroid belongs to.
     * @param model the model representing this asteroid.
     */
    public EnemyShip3Body(World world, EnemyShipModel model){
        super(world, model, BodyTypeDef.KINEMATIC);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 238, height = 89;

        // UPPER WING
        createFixture(body,
                new float[]{11,8, 57,3, 156,10, 222,19, 230,41, 30,36},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});

        // CENTER
        createFixture(body,
                new float[]{30,36, 166,40, 166,50, 29,54},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY)});

        //LEFT WING
        createFixture(body,
                new float[]{29,54, 228,49, 221,71, 154,79, 59,86, 11,80},
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
