package com.bulletborne.game.model.entities;

/**
 * Created by Zé on 19/05/2017.
 */

public class PlayerModel extends EntityModel{
    private ModelType type;
    /**
     * Creates a new player ship model in a certain position and having a certain rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param rotation the rotation in radians
     */
    public PlayerModel(float x, float y, float rotation,ModelType type) {
        super(x, y, rotation);
        this.type=type;
    }

    /**
     * returns the ModelType of this EntityModel
     * @return ModelType type of the ship
     */
    @Override
    public ModelType getType() {
        return type;
    }
}
