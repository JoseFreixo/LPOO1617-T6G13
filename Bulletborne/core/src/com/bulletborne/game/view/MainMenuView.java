package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.bulletborne.game.Bulletborne;

import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;

/**
 * Created by Ruben Torres on 29/05/2017.
 */

public class MainMenuView extends View {


    private static final float BUTTONS_X_MIN=1.61f;
    private static final float BUTTONS_X_MAX= 1.12f;
    private static final float PLAY_Y_MIN=2.69f;
    private static final float PLAY_Y_MAX=1.93f;
    private static final float HANGAR_Y_MIN=1.85f;
    private static final float HANGAR_Y_MAX=1.46f;
    private static final float EXIT_Y_MIN=1.42f;
    private static final float EXIT_Y_MAX=1.19f;
    private static final float LOWER_BUTTONS_Y_MIN=1.15f;
    private static final float LOWER_BUTTONS_Y_MAX=1.00f;
    private static final float OPTIONS_X_MIN=80.00f;
    private static final float OPTIONS_X_MAX=14.00f;
    private static final float CREDITS_X_MIN=12.08f;
    private static final float CREDITS_X_MAX=7.03f;
    private static final float HIGHSCORES_X_MIN=6.42f;
    private static final float HIGHSCORES_X_MAX=4.63f;
    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public MainMenuView(Bulletborne game) {
        super(game);
        loadAssets();
    }


    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load( "Title_background.png" , Texture.class);
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
            touchedButtons(xMax,yMax);
            touchedLowerButtons(xMax,yMax);
        }
    }

    private void touchedButtons(int xMax,int yMax){
        if (Gdx.input.getX() > xMax/BUTTONS_X_MIN && Gdx.input.getX()<xMax/BUTTONS_X_MAX) {

            if (Gdx.input.getY() > yMax/PLAY_Y_MIN && Gdx.input.getY() < yMax/PLAY_Y_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                game.setScreen(new GameView(game));
            }

            if (Gdx.input.getY() > yMax/HANGAR_Y_MIN && Gdx.input.getY() < yMax/HANGAR_Y_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                game.setScreen(new HangarView(game));
            }


            if (Gdx.input.getY() > yMax/EXIT_Y_MIN && Gdx.input.getY() < yMax/EXIT_Y_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                System.exit(0);
            }
        }
    }

    private void touchedLowerButtons(int xMax,int yMax){
        if (Gdx.input.getY() > yMax/LOWER_BUTTONS_Y_MIN && Gdx.input.getY() < yMax/LOWER_BUTTONS_Y_MAX){

            if (Gdx.input.getX() > xMax/OPTIONS_X_MIN && Gdx.input.getX()< xMax/OPTIONS_X_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                System.out.println("ENTREI NO OPTIONS");
                System.out.println("x ratio= " + xMax / Gdx.input.getX());
                System.out.println("t ratio= " + yMax / Gdx.input.getY());
            }

            if (Gdx.input.getX() > xMax/HIGHSCORES_X_MIN && Gdx.input.getX()< xMax/HIGHSCORES_X_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                System.out.println("ENTREI NO HIGHSCORES");
            }

            if (Gdx.input.getX() > xMax/CREDITS_X_MIN && Gdx.input.getX()< xMax/CREDITS_X_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                game.setScreen(new CreditsView(game));
            }
        }
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("Title_background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, ARENA_WIDTH / PIXEL_TO_METER, ARENA_HEIGHT / PIXEL_TO_METER);
    }
}
