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

        //Lower Wing
        createFixture(body, new float[]{
                13,7, 75,7, 100,12, 109,40, 244,75, 242,84, 170,82, 15,56
        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY));

        //Body
        createFixture(body, new float[]{
                170,82, 74,66, 76,123, 171,103
        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY));

        //Upper Wing
        createFixture(body, new float[]{
                18,181, 76,181, 100,174, 110,148, 243,113, 242,104, 170,104, 17,131
        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY));
    }
}
