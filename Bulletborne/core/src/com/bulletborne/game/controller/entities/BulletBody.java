package com.bulletborne.game.controller.entities;

import com.bulletborne.game.model.entities.BulletModel;
import com.badlogic.gdx.physics.box2d.World;

public class BulletBody extends EntityBody{
    /**
     * Constructs a bullet body according to
     * a bullet model.
     *
     * @param world the physical world this asteroid belongs to.
     * @param model the model representing this bullet.
     */
    public BulletBody(World world, BulletModel model) {
        super(world, model, true);

        float density = 1f, friction = 0.4f, restitution = 0.5f;
        int width = 18, height = 60;

        createFixture(body, new float[]{
                0,12, 6,0, 11,0, 17,12, 17,47, 11,59, 6,59, 0,47
        }, width, height, density, friction, restitution, BULLET_BODY, (short) (PLAYER_BODY | ENEMY_BODY | BORDER_BODY));
    }


}
