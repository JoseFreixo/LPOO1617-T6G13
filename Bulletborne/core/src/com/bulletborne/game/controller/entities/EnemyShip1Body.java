package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.PlayerModel;

/**
 * Created by r_tor on 26/05/2017.
 */

public class EnemyShip1Body extends EntityBody {

    public EnemyShip1Body(World world, PlayerModel model){
        super(world, model, true);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 256, height = 114;

        createFixture(body, new float[]{
                0,9, 14,10, 17,0, 39,2, 40,12, 47,18, 99,24, 113,39, 137,42,
                142,35, 159,37, 160,45, 168,47, 175,54, 187,57, 187,62, 201,63,
                201,69, 128,69, 127,75, 201,74, 201,82, 188,83, 188,87, 174,89,
                168,97, 160,99, 160,67, 141,110, 138,103, 113,105, 100,121,
                46,128, 41,132, 40,142, 17,144, 13,135, 0,135
        }, width, height, density, friction, restitution);
             
    }
}
