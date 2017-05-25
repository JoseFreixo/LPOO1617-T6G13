package com.bulletborne.game.model.entities;

/**
 * Created by Zé on 25/05/2017.
 */

public class BulletPlayerModel extends EntityModel{
    private float timeToLive;

    /**
     * Constructs a bullet model belonging to a game model.
     *
     * @param x The x-coordinate of this bullet.
     * @param y The y-coordinate of this bullet.
     * @param rotation The rotation of this bullet.
     */
    public BulletPlayerModel(float x, float y, float rotation) {
        super(x, y, rotation);
    }

    /**
     * Decreases this bullet's time to leave by delta seconds
     *
     * @param delta
     * @return
     */
    public boolean decreaseTimeToLive(float delta) {
        timeToLive -= delta;
        return  timeToLive < 0;
    }

    /**
     * Sets this bullet's time to live in seconds
     * @param timeToLive
     */
    public void setTimeToLive(float timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    public ModelType getType() {
        return ModelType.PLAYER_BULLET;
    }
}
