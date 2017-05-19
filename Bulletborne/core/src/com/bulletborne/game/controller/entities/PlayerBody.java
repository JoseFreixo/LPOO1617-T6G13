package com.bulletborne.game.controller.entities;

import com.bulletborne.game.model.entities.PlayerModel;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Zé on 19/05/2017.
 */

public class PlayerBody extends EntityBody{

    public PlayerBody(World world, PlayerModel model){
        super(world, model);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 256, height = 114;

        // Fixtures

        //Lower Wing
        createFixture(body, new float[]{
                28,83, 249,72, 230,84, 149,110, 84,106
        }, width, height, density, friction, restitution);

        //Upper Wing
        createFixture(body, new float[]{
                30,30, 250,42, 230,30, 149,4, 83,8
        }, width, height, density, friction, restitution);

        //Body
        createFixture(body, new float[]{
                28,83, 163,76, 163,38, 30,30, 6,42, 6,72
        }, width, height, density, friction, restitution);
    }
}