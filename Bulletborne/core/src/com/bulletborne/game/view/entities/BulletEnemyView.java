package com.bulletborne.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bulletborne.game.Bulletborne;

public class BulletEnemyView extends EntityView{
    /**
     * Constructs a bullet view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public BulletEnemyView(Bulletborne game) {
        super(game);
    }

    /**
     * Creates a sprite representing this bullet.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this bullet
     */
    public Sprite createSprite(Bulletborne game) {
        Texture texture = game.getAssetManager().get("bullet.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
