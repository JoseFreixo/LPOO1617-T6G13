package com.bulletborne.game.model;

import com.badlogic.gdx.utils.Pool;
import com.bulletborne.game.controller.GameController;
import com.bulletborne.game.model.entities.EntityModel;
import com.bulletborne.game.model.entities.PlayerModel;
import com.bulletborne.game.model.entities.BarrierModel;
import com.bulletborne.game.model.entities.BulletPlayerModel;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Zé on 05/05/2017.
 */

public class GameModel {
    /**
     * The singleton instance of the game model
     */
    private static GameModel instance;

    private PlayerModel player;
    private BarrierModel barriers [] = {new BarrierModel(0,0,0), new BarrierModel(0,50,0)};

    /**
     * The bullets currently flying through space.
     */
    private List<BulletPlayerModel> bullets;

    /**
     * A pool of bullets
     */
    Pool<BulletPlayerModel> bulletPool = new Pool<BulletPlayerModel>() {
        @Override
        protected BulletPlayerModel newObject() {
            return new BulletPlayerModel(0, 0, 0);
        }
    };

    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }

    /**
     * Constructs a game with a.space ship in the middle of the
     * arena and a certain number of asteroids in different sizes.
     */
    private GameModel() {
        //asteroids = new ArrayList<AsteroidModel>();
        //bullets = new ArrayList<BulletModel>();
        bullets = new ArrayList<BulletPlayerModel>();
        player = new PlayerModel(GameController.ARENA_WIDTH / 10, GameController.ARENA_HEIGHT / 2, 0);
        /*
        for (int i = 0; i < ASTEROID_COUNT; i++)
            asteroids.add(new AsteroidModel(
                    random.nextFloat() * GameController.ARENA_WIDTH,
                    random.nextFloat() * GameController.ARENA_HEIGHT,
                    (float) Math.toRadians(random.nextFloat() * 360),
                    random.nextBoolean()?AsteroidModel.AsteroidSize.BIG:AsteroidModel.AsteroidSize.MEDIUM));
        */
    }

    /**
     * Returns the player space ship.
     *
     * @return the space ship.
     */
    public PlayerModel getPlayer() {
        return player;
    }

    public BarrierModel [] getBarriers() { return barriers; }

    /**
     * Returns the bullets.
     *
     * @return the bullet list
     */
    public List<BulletPlayerModel> getBullets() {
        return bullets;
    }

    public BulletPlayerModel createBullet(PlayerModel ship) {
        BulletPlayerModel bullet = bulletPool.obtain();

        bullet.setFlaggedForRemoval(false);
        bullet.setPosition(ship.getX() + (float)(Math.cos(ship.getRotation()) * 6), ship.getY() + (float)(Math.sin(ship.getRotation()) * 6));
        bullet.setRotation(ship.getRotation() - (float)Math.PI/2);
        bullet.setTimeToLive(0.9f);

        bullets.add(bullet);

        return bullet;
    }

    /**
     * Removes a model from this game.
     *
     * @param model the model to be removed
     */
    public void remove(EntityModel model) {
        if (model instanceof BulletPlayerModel) {
            bullets.remove(model);
            bulletPool.free((BulletPlayerModel) model);
        }
        /*if (model instanceof AsteroidModel) {
            asteroids.remove(model);
        }*/
    }

    public void update(float delta) {
        for (BulletPlayerModel bullet : bullets)
            if (bullet.decreaseTimeToLive(delta))
                bullet.setFlaggedForRemoval(true);
    }
}
