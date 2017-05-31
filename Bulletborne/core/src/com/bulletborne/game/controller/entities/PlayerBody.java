package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.BulletModel;
import com.bulletborne.game.model.entities.EntityModel;

import java.util.ArrayList;


/**
 * Created by Ruben Torres on 31/05/2017.
 */

public abstract class PlayerBody extends EntityBody {

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
