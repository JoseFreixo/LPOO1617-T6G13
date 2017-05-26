package com.bulletborne.game.view.entities;

import com.bulletborne.game.Bulletborne;
import com.bulletborne.game.model.entities.EntityModel;

import java.util.HashMap;
import java.util.Map;

import static com.bulletborne.game.model.entities.EntityModel.ModelType.*;

/**
 * A factory for EntityView objects with cache
 */

public class ViewFactory {
    private static Map<EntityModel.ModelType, EntityView> cache =
            new HashMap<EntityModel.ModelType, EntityView>();

    public static EntityView makeView(Bulletborne game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == PLAYER)
                cache.put(model.getType(), new PlayerView(game));
            if (model.getType() == PLAYER_BULLET)
                cache.put(model.getType(), new BulletPlayerView(game));
        /*  if (model.getType() == ENEMY)
                cache.put(model.getType(), new ShipView(game));
            if (model.getType() == ENEMY_BULLET)
                cache.put(model.getType(), new BulletView(game));
            if (model.getType() == BORDER)
                cache.put(model.getType(), new FuckFaceView(game));*/
        }
        return cache.get(model.getType());
    }
}
