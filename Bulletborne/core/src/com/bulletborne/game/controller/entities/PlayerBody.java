package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.BulletModel;
import com.bulletborne.game.model.entities.EntityModel;

import java.util.ArrayList;

/**
 * An abstract PlayerBody representing one of the ships to choose from
 */
public abstract class PlayerBody extends EntityBody {
    /**
     * Y Distance between all of the bullets shot at the sime time by this ship
     */
    protected static final float Y_BULLET_DISTANCE=1f;

    protected float density, friction, restitution;
    protected int width, height;

    public PlayerBody(World world, EntityModel model, BodyTypeDef dynamic) {
        super(world, model, dynamic);
        density = 0.5f;
        friction = 0.4f;
        restitution = 0.5f;
    }

    public abstract ArrayList<BulletModel> shoot();
}
