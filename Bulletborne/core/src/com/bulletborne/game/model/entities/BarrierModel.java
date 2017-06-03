package com.bulletborne.game.model.entities;

/**
 * Created by Zé on 24/05/2017.
 */

public class BarrierModel extends EntityModel{

    /**
     * Constructs a barrier model belonging to a game model.
     *
     * @param x The x-coordinate of this bullet.
     * @param y The y-coordinate of this bullet.
     * @param rotation The rotation of this barrier.
     */
    public BarrierModel(float x, float y, float rotation){
        super(x, y, rotation);
    }

    /**
     * returns the ModelType of this EntityModel
     * @return ModelType type of the  Barrier
     */
    public ModelType getType() {
        return ModelType.BORDER;
    }
}
