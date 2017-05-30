package com.bulletborne.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bulletborne.game.Bulletborne;

/**
 * Created by r_tor on 30/05/2017.
 */

public class PlayerView2 extends EntityView {
    /**
     * Constructs a space ship model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public PlayerView2(Bulletborne game) {
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
        Texture playerShip = game.getAssetManager().get("Player_ship2.png");
        return new Sprite(new TextureRegion(playerShip, playerShip.getWidth(), playerShip.getHeight()));
    }
}
