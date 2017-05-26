package com.bulletborne.game.model.entities;

/**
 * Created by Zé on 24/05/2017.
 */

public class BarrierModel extends EntityModel{
    public BarrierModel(float x, float y, float rotation){
        super(x, y, rotation);
    }

    public ModelType getType() {
        return ModelType.BORDER;
    }
}
