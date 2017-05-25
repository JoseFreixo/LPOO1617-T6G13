package com.bulletborne.game.view.entities;

import com.bulletborne.game.Bulletborne;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BulletPlayerView extends EntityView{
    /**
     * Constructs a bullet view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public BulletPlayerView(Bulletborne game) {
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
        Texture texture = game.getAssetManager().get("bullet_ally.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
