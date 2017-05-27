package com.bulletborne.game.controller.entities;

import com.bulletborne.game.model.entities.BarrierModel;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Zé on 24/05/2017.
 */

public class BarrierBody extends EntityBody{
    public BarrierBody(World world, BarrierModel model){
        super(world, model, BodyTypeDef.STATIC);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 5000, height = 1;

        //Everything
        createFixture(body, new float[]{
                0,0, 5000,0, 0,1, 5000,1
        }, width, height, density, friction, restitution, BORDER_BODY, (short) (PLAYER_BODY | BULLET_BODY));
    }
}
