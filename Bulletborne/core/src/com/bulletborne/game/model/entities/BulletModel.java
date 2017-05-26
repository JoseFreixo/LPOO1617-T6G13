package com.bulletborne.game.model.entities;

/**
 * Created by Zé on 25/05/2017.
 */

public class BulletModel extends EntityModel{

    public enum BulletType {PLAYER_BULLET, ENEMY_BULLET}

    private BulletType type;

    private float timeToLive;

    /**
     * Constructs a bullet model belonging to a game model.
     *
     * @param x The x-coordinate of this bullet.
     * @param y The y-coordinate of this bullet.
     * @param rotation The rotation of this bullet.
     */
    public BulletModel(float x, float y, float rotation, BulletType type) {
        super(x, y, rotation);
        this.type = type;
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

    public BulletType getBulletType() { return type; }

    @Override
    public ModelType getType() {
        if (type == BulletType.PLAYER_BULLET)
            return ModelType.PLAYER_BULLET;
        if (type == BulletType.ENEMY_BULLET)
            return ModelType.ENEMY_BULLET;
        return null;
    }
}
