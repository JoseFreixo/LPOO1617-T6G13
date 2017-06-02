package com.bulletborne.game.controller.entities;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.bulletborne.game.model.GameModel;
import com.bulletborne.game.model.entities.BulletModel;
import com.bulletborne.game.model.entities.PlayerModel;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;


/**
 * Created by Zé on 19/05/2017.
 */

public class Player1Body extends PlayerBody{

    public Player1Body(World world, PlayerModel model){
        super(world, model, BodyTypeDef.DYNAMIC);

        width = 256;
        height = 114;
        //Lower Wing
        createFixture(body,
                new float[]{28,83, 249,72, 230,84, 149,110, 84,106},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY)});

        //Upper Wing
        createFixture(body,
                new float[]{30,30, 250,42, 230,30, 149,4, 83,8},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY)});

        //Body
        createFixture(body,
                new float[]{28,83, 163,76, 163,38, 30,30, 6,42, 6,72},
                new int[]{width, height},
                new float[]{density, friction, restitution},
                new short[]{PLAYER_BODY, (short) (ENEMY_BODY | BULLET_BODY | BORDER_BODY)});
    }

    @Override
    public ArrayList<BulletModel> shoot(){
        ArrayList<BulletModel> bullets= new ArrayList<BulletModel>();
        BulletModel bullet = GameModel.getInstance().createBullet(GameModel.getInstance().getPlayer());
        bullet.setPosition(bullet.getX() - Y_BULLET_DISTANCE * (float)(Math.sin(GameModel.getInstance().getPlayer().getRotation())),bullet.getY() + Y_BULLET_DISTANCE * (float)(Math.cos(GameModel.getInstance().getPlayer().getRotation())));
        bullets.add(bullet);
        bullet = GameModel.getInstance().createBullet(GameModel.getInstance().getPlayer());
        bullet.setPosition(bullet.getX() + Y_BULLET_DISTANCE * (float)(Math.sin(GameModel.getInstance().getPlayer().getRotation())),bullet.getY() - Y_BULLET_DISTANCE * (float)(Math.cos(GameModel.getInstance().getPlayer().getRotation())));
        bullets.add(bullet);
        return bullets;
    }
}
