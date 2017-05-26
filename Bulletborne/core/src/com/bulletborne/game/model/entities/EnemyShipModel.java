package com.bulletborne.game.model.entities;

/**
 * Created by r_tor on 26/05/2017.
 */

public class EnemyShipModel extends EntityModel {
    public enum EnemyShipType {NORMAL, TANK, GLASSCANNON};
    /**
     * Is this ship accelerating in this update delta
     */
    private EnemyShipType type;
    private boolean accelerating = true;

    /**
     * Creates a new ship model in a certain position and having a certain rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param rotation the rotation in radians
     */
    public EnemyShipModel(float x, float y, int rotation, EnemyShipType type) {
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
        if(this.type==EnemyShipType.NORMAL)
            return ModelType.ENEMY_1;
        if(this.type==EnemyShipType.TANK)
            return ModelType.ENEMY_2;
        if(this.type==EnemyShipType.GLASSCANNON)
            return ModelType.ENEMY_3;
        return null;
    }
}
