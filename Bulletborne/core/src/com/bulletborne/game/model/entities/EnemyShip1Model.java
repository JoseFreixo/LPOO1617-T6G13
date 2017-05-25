package com.bulletborne.game.model.entities;

/**
 * Created by r_tor on 26/05/2017.
 */

public class EnemyShip1Model extends EntityModel {

    /**
     * Is this ship accelerating in this update delta
     */
    private boolean accelerating = true;

    /**
     * Creates a new ship model in a certain position and having a certain rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param rotation the rotation in radians
     */
    public EnemyShip1Model(float x, float y, int rotation) {
        super(x, y, rotation);
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
        return ModelType.ENEMY;
    }
}
