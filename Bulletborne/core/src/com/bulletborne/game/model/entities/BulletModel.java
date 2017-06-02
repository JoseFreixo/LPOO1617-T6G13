package com.bulletborne.game.model.entities;

/**
 * Created by Zé on 25/05/2017.
 */

public class BulletModel extends EntityModel{

    private ModelType type;

    /**
     * Constructs a bullet model belonging to a game model.
     *
     * @param x The x-coordinate of this bullet.
     * @param y The y-coordinate of this bullet.
     * @param rotation The rotation of this bullet.
     */
    public BulletModel(float x, float y, float rotation, ModelType type) {
        super(x, y, rotation);
        this.type = type;
    }

    @Override
    public ModelType getType() {
        return type;
    }
}
