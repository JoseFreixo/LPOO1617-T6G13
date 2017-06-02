package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.bulletborne.game.model.GameModel;
import com.bulletborne.game.model.entities.BulletModel;
import com.bulletborne.game.model.entities.PlayerModel;

import java.util.ArrayList;

/**
 * Created by r_tor on 30/05/2017.
 */

public class Player2Body extends PlayerBody {
    public Player2Body(World world, PlayerModel model){
        super(world, model, BodyTypeDef.DYNAMIC);
        width = 257;
        height = 187;
        //Upper Turbine
        createFixture(body,
                new float[]{17,5, 104,6, 106,43, 20,44},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY)});

        //Upper Wing
        createFixture(body,
                new float[]{20,44, 106,43, 242,75, 240,83, 163,81, 28,57},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY)});

        //Center
        createFixture(body,
                new float[]{163,81, 76,65, 78,121, 163,104},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY)});

        //Lower Wing
        createFixture(body,
                new float[]{242,103, 163,104, 27,130, 28,141, 112,145, 242,112},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY)});

        //Lower Turbine
        createFixture(body,
                new float[]{112,145, 16,140, 20,183, 100, 180},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY)});
    }

    @Override
    public ArrayList<BulletModel> shoot() {
        ArrayList<BulletModel> bullets= new ArrayList<BulletModel>();
        BulletModel bullet = GameModel.getInstance().createBullet(GameModel.getInstance().getPlayer());
        bullets.add(bullet);
        bullet = GameModel.getInstance().createBullet(GameModel.getInstance().getPlayer());
        bullet.setPosition(bullet.getX() - Y_BULLET_DISTANCE * (float)(Math.sin(GameModel.getInstance().getPlayer().getRotation())),bullet.getY() + Y_BULLET_DISTANCE * (float)(Math.cos(GameModel.getInstance().getPlayer().getRotation())));
        bullets.add(bullet);
        bullet = GameModel.getInstance().createBullet(GameModel.getInstance().getPlayer());
        bullet.setPosition(bullet.getX() + Y_BULLET_DISTANCE * (float)(Math.sin(GameModel.getInstance().getPlayer().getRotation())),bullet.getY() - Y_BULLET_DISTANCE * (float)(Math.cos(GameModel.getInstance().getPlayer().getRotation())));
        bullets.add(bullet);
        return bullets;
    }
}
