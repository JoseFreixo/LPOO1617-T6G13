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

public class HighScoresView extends View {
    private static final float SCORE_X_POS = 1.66f;
    private static final float SCORE_Y_POS = 1.7f;
    private BitmapFont fontBestScore;
    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public HighScoresView(Bulletborne game) {
        super(game);
        loadAssets();
        fontBestScore= new BitmapFont(Gdx.files.internal("myFontScore.fnt"));
        fontBestScore.getData().scale(3f);
        fontBestScore.setColor(Color.GOLD);
    }


    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load( "highscore_background.png" , Texture.class);
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
        drawBestScore();
        game.getBatch().end();
        handleInputs(delta);
    }

    private void drawBestScore() {
            fontBestScore.draw(game.getBatch(), Integer.toString(bestScore), (ARENA_WIDTH / PIXEL_TO_METER / SCORE_X_POS), (ARENA_HEIGHT / PIXEL_TO_METER / SCORE_Y_POS) );
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("highscore_background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, ARENA_WIDTH / PIXEL_TO_METER, ARENA_HEIGHT / PIXEL_TO_METER);
    }
}
