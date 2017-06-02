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
    public BulletBody(World world, BulletModel model, boolean isPlayers) {
        super(world, model, BodyTypeDef.DYNAMIC);

        float density = 1f, friction = 0.4f, restitution = 0.5f;
        int width = 18, height = 60;

        short mask;

        if (isPlayers)
            mask = (short) (ENEMY_BODY | BORDER_BODY);
        else
            mask = (short) (PLAYER_BODY | BORDER_BODY);

        createFixture(body,
                new float[]{0,12, 6,0, 11,0, 17,12, 17,47, 11,59, 6,59, 0,47},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{BULLET_BODY, mask});
    }
}
