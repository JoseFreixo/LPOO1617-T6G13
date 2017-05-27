package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.EnemyShipModel;

/**
 * Created by Ruben Torres on 26/05/2017.
 */

public class EnemyShip3Body extends EntityBody {

    private int healthPoints;

    public EnemyShip3Body(World world, EnemyShipModel model){
        super(world, model, true);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 238, height = 89, healthPoints=1;

        // UPPER WING
        createFixture(body, new float[]{
            11,8, 57,3, 156,10, 222,19, 230,41, 30,36
        }, width, height, density, friction, restitution, ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY));

        // CENTER
        createFixture(body, new float[]{
                30,36, 166,40, 166,50, 29,54
        }, width, height, density, friction, restitution, ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY));

        //LEFT WING
        createFixture(body, new float[]{
                29,54, 228,49, 221,71, 154,79, 59,86, 11,80
        }, width, height, density, friction, restitution, ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY));
    }

    @Override
    public void setLinearVelocity(float velocity) {
        body.setLinearVelocity(velocity, 0);
    }

    @Override
    public void hit(int damage) {
        healthPoints-=damage;
    }

    @Override
    public int getHP() {
        return healthPoints;
    }
}
