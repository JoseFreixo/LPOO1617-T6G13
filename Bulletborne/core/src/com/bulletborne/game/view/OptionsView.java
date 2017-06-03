package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bulletborne.game.Bulletborne;

import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;

/**
 * Created by Ruben Torres on 02/06/2017.
 */

public class OptionsView extends View {

    private BitmapFont fontSoundChanger;
    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public OptionsView(Bulletborne game) {
        super(game);
        loadAssets();

        fontSoundChanger = new BitmapFont(Gdx.files.internal("myFontScore.fnt"));
        fontSoundChanger.getData().scale(1.8f);
        fontSoundChanger.setColor(Color.GOLD);
    }


    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load( "options_background.png" , Texture.class);
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
        drawCurrentOptions();
        game.getBatch().end();
        handleInputs(delta);
    }

    private void drawCurrentOptions() {
        fontSoundChanger.draw(game.getBatch(), Float.toString(audioChanger), (ARENA_WIDTH / PIXEL_TO_METER / 1.36f), (ARENA_HEIGHT / PIXEL_TO_METER / 1.75f) );
        fontSoundChanger.draw(game.getBatch(), Float.toString(game.getMusicVolume()), (ARENA_WIDTH / PIXEL_TO_METER / 1.36f), (ARENA_HEIGHT / PIXEL_TO_METER / 3.1f) );
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("options_background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, ARENA_WIDTH / PIXEL_TO_METER, ARENA_HEIGHT / PIXEL_TO_METER);
    }
}
