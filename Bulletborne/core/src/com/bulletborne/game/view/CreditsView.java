package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bulletborne.game.Bulletborne;

import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;


public class CreditsView extends View {
    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public CreditsView(Bulletborne game) {
        super(game);
        loadAssets();
    }


    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load( "Credits_background.png" , Texture.class);
        this.game.getAssetManager().finishLoading();
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {
        super.render(delta);
        game.getBatch().begin();
        drawBackground();
        game.getBatch().end();
        handleInputs(delta);
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("Credits_background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, ARENA_WIDTH / PIXEL_TO_METER, ARENA_HEIGHT / PIXEL_TO_METER);
    }
}
