package com.bulletborne.game.model.entities;

/**
 * Created by Zé on 19/05/2017.
 */

public class PlayerModel extends EntityModel{

    /**
     * Is this ship accelerating in this update delta
     */
    private boolean accelerating = true;

    private ModelType type;
    /**
     * Creates a new ship model in a certain position and having a certain rotation.
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
     * Set the accelerating flag for this ship
     *
     * @param accelerating the accelerating tag
     */
    public void setAccelerating(boolean accelerating) {
        this.accelerating = accelerating;
    }

    /**
     * Is the ship accelerating in this update
     *
     * @return the accelerating flag
     */
    public boolean isAccelerating() {
        return accelerating;
    }

    @Override
    public ModelType getType() {
        return type;
    }
}
