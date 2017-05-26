package com.bulletborne.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bulletborne.game.Bulletborne;

/**
 * Created by Ruben Torres on 26/05/2017.
 */

public class EnemyShip3View extends EntityView {
    /**
     * Constructs a space ship model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public EnemyShip3View(Bulletborne game) {
        super(game);
    }

    /**
     * Creates a sprite representing this space ship.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this space ship
     */
    @Override
    public Sprite createSprite(Bulletborne game) {
        Texture EnemyShip3 = game.getAssetManager().get("Enemy_ship3.png");
        return new Sprite(new TextureRegion(EnemyShip3, EnemyShip3.getWidth(), EnemyShip3.getHeight()));
    }
}
