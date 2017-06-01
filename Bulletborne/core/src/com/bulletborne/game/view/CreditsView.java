package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.bulletborne.game.Bulletborne;

import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;

/**
 * Created by Ruben Torres on 01/06/2017.
 */

public class CreditsView extends View {
    private static final float BACK_X_MIN=64.0f;
    private static final float BACK_X_MAX=9.14f;
    private static final float BACK_Y_MIN=1.24f;
    private static final float BACK_Y_MAX=1.02f;
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
     * Handles any inputs and passes them to the controller.
     *
     * @param delta time since last time inputs where handled in seconds
     */
    private void handleInputs(float delta) {

        if (Gdx.input.justTouched()) {
            int xMax= Gdx.graphics.getWidth();
            int yMax= Gdx.graphics.getHeight();
            touchedBackButton(xMax,yMax);

        }
    }

    private void touchedBackButton(int xMax, int yMax) {
        if (Gdx.input.getX() > xMax/BACK_X_MIN && Gdx.input.getX()<xMax/BACK_X_MAX)
            if (Gdx.input.getY() > yMax/BACK_Y_MIN && Gdx.input.getY() < yMax/BACK_Y_MAX)
                buttonClick.play(0.5f);
                game.setScreen(new MainMenuView(game));
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
