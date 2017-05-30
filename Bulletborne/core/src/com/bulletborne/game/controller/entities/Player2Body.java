package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.bulletborne.game.model.entities.PlayerModel;

/**
 * Created by r_tor on 30/05/2017.
 */

public class Player2Body extends EntityBody {
    public Player2Body(World world, PlayerModel model){
        super(world, model, BodyTypeDef.DYNAMIC);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 255, height = 187;

        // Fixtures

        //Upper Turbine
        createFixture(body, new float[]{
                17,5, 104,6, 106,43, 20,44
        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY));

        //Upper Wing
        createFixture(body, new float[]{
                20,44, 106,43, 242,75, 240,83, 163,81, 28,57
        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY));

        //Center
        createFixture(body, new float[]{
                163,81, 76,65, 78,121, 163,104
        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY));

        //Lower Wing
        createFixture(body, new float[]{
                242,103, 163,104, 27,130, 28,141, 112,145, 242,112
        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY));

        //Lower Turbine
        createFixture(body, new float[]{
                112,145, 16,140, 20,183, 100, 180
        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY));
    }
}
