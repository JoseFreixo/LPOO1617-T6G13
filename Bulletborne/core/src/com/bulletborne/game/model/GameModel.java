package com.bulletborne.game.model;

import com.bulletborne.game.controller.GameController;
import com.bulletborne.game.model.entities.EntityModel;
import com.bulletborne.game.model.entities.PlayerModel;

import java.util.ArrayList;

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

    public PlayerModel getPlayer() {
        return player;
    }
}
