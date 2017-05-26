package com.bulletborne.game.model;

import com.badlogic.gdx.utils.Pool;
import com.bulletborne.game.controller.GameController;
import com.bulletborne.game.model.entities.EntityModel;
import com.bulletborne.game.model.entities.PlayerModel;
import com.bulletborne.game.model.entities.BarrierModel;
import com.bulletborne.game.model.entities.BulletModel;
import com.bulletborne.game.model.entities.EnemyShipModel;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Zé on 05/05/2017.
 */

public class GameModel {
    public static final float Y_MIN = 3.5f;
    public static final float Y_MAX = 46.5f;
    public static final float X_START = 110f;
    /**
     * The singleton instance of the game model
     */
    private static GameModel instance;

    private PlayerModel player;
    private BarrierModel barriers [] = {new BarrierModel(0,0,0), new BarrierModel(0,50,0)};

    /**
     * The bullets currently flying through space.
     */
    private List<BulletModel> bullets;

    /**
     * A pool of bullets
     */
    Pool<BulletModel> bulletPool = new Pool<BulletModel>() {
        @Override
        protected BulletModel newObject() {
            return new BulletModel(0, 0, 0, BulletModel.BulletType.PLAYER_BULLET);
        }
    };

    private List<EnemyShipModel> enemies;

    /**
     * A pool of enemys
     */
    Pool<EnemyShipModel> enemyPool = new Pool<EnemyShipModel>() {
        @Override
        protected EnemyShipModel newObject() {
            int i = random(4);
            switch(i){
                case 0:
                case 1:
                case 4:
                    return new EnemyShipModel(0, 0, (float)Math.PI, EnemyShipModel.EnemyShipType.NORMAL);
                case 2:
                    return new EnemyShipModel(0, 0, (float) Math.PI, EnemyShipModel.EnemyShipType.TANK);
                case 3:
                    return new EnemyShipModel(0, 0, (float) Math.PI, EnemyShipModel.EnemyShipType.GLASSCANNON);
            }
            return null;
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
        bullets = new ArrayList<BulletModel>();
        enemies = new ArrayList<EnemyShipModel>();
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
    public List<BulletModel> getBullets() {
        return bullets;
    }

    public BulletModel createBullet(PlayerModel ship) {
        BulletModel bullet = bulletPool.obtain();

        bullet.setFlaggedForRemoval(false);
        bullet.setPosition(ship.getX() + (float)(Math.cos(ship.getRotation()) * 6), ship.getY() + (float)(Math.sin(ship.getRotation()) * 6));
        bullet.setRotation(ship.getRotation() - (float)Math.PI/2);
        bullet.setTimeToLive(0.9f);

        bullets.add(bullet);

        return bullet;
    }

    public List<EnemyShipModel> getEnemies() { return enemies; }

    public EnemyShipModel createEnemy(){
        EnemyShipModel enemy = enemyPool.obtain();

        enemy.setFlaggedForRemoval(false);
        enemy.setPosition(X_START, random(Y_MIN, Y_MAX));
        enemy.setRotation((float) Math.PI);
        enemies.add(enemy);

        return enemy;
    }

    /**
     * Removes a model from this game.
     *
     * @param model the model to be removed
     */
    public void remove(EntityModel model) {
        if (model instanceof BulletModel) {
            bullets.remove(model);
            bulletPool.free((BulletModel) model);
        }
        /*if (model instanceof AsteroidModel) {
            asteroids.remove(model);
        }*/
    }

    public void update(float delta) {
        for (BulletModel bullet : bullets)
            if (bullet.decreaseTimeToLive(delta))
                bullet.setFlaggedForRemoval(true);
    }
}
