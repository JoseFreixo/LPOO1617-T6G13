package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.PlayerModel;

/**
 * Created by Ruben Torres on 26/05/2017.
 */

public class EnemyShip2Body extends EntityBody {
    public EnemyShip2Body(World world, PlayerModel model){
        super(world, model, true);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 258, height = 170;

        createFixture(body, new float[]{
                26,8, 71,3, 79,17, 186,24, 197,35, 219,38, 243,77, 188,78,
                188,92, 244,92, 218,133, 196,134, 185,147, 77,153, 71,168,
                24,163, 1,119, 1,50
        }, width, height, density, friction, restitution, ENEMY_BODY, (short) (PLAYER_BODY | BULLET_BODY));

    }
}
