package com.bulletborne.game.model;

import com.bulletborne.game.model.entities.PlayerModel;

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

    public PlayerModel getPlayer() {
        return player;
    }
}
