package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.bulletborne.game.Bulletborne;

import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;



public class MainMenuView extends View {

    /**
     * Ratio used to know the x_min position of the play/hangar/exit buttons
     */
    private static final float BUTTONS_X_MIN=1.61f;
    /**
     * Ratio used to know the x_max position of the play/hangar/exit buttons
     */
    private static final float BUTTONS_X_MAX= 1.12f;
    /**
     * Ratio used to know the y_min position of the play button
     */
    private static final float PLAY_Y_MIN=2.69f;
    /**
     * Ratio used to know the y_max position of the play button
     */
    private static final float PLAY_Y_MAX=1.93f;
    /**
     * Ratio used to know the y_min position of the hangar button
     */
    private static final float HANGAR_Y_MIN=1.85f;
    /**
     * Ratio used to know the y_max position of the hangar button
     */
    private static final float HANGAR_Y_MAX=1.46f;
    /**
     * Ratio used to know the y_min position of the exit button
     */
    private static final float EXIT_Y_MIN=1.42f;
    /**
     * Ratio used to know the y_max position of the exit button
     */
    private static final float EXIT_Y_MAX=1.19f;
    /**
     * Ratio used to know the y_min position of the options/credits/highscore buttons
     */
    private static final float LOWER_BUTTONS_Y_MIN=1.15f;
    /**
     * Ratio used to know the y_max position of the options/credits/highscore buttons
     */
    private static final float LOWER_BUTTONS_Y_MAX=1.00f;
    /**
     * Ratio used to know the x_min position of the options button
     */
    private static final float OPTIONS_X_MIN=80.00f;
    /**
     * Ratio used to know the x_max position of the options button
     */
    private static final float OPTIONS_X_MAX=14.00f;
    /**
     * Ratio used to know the x_min position of the credits button
     */
    private static final float CREDITS_X_MIN=12.08f;
    /**
     * Ratio used to know the x_max position of the credits button
     */
    private static final float CREDITS_X_MAX=7.03f;
    /**
     * Ratio used to know the x_min position of the highscore button
     */
    private static final float HIGHSCORES_X_MIN=6.42f;
    /**
     * Ratio used to know the x_max position of the highscore button
     */
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
    protected void handleInputs(float delta) {

        if (Gdx.input.justTouched()) {
            int xMax= Gdx.graphics.getWidth();
            int yMax= Gdx.graphics.getHeight();
            touchedMainButtons(xMax,yMax);
            touchedLowerButtons(xMax,yMax);
        }
    }

    /**
     * Checks if the play/hangar/exit buttons were pressed and reacts accordingly
     * @param xMax game width
     * @param yMax game height
     */
    private void touchedMainButtons(int xMax, int yMax){
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
                game.save(bestScore,shipNumber,audioChanger);
                dispose();
                Gdx.app.exit();
            }
        }
    }

    /**
     * Checks if the Options/Credits/HighScore buttons were pressed and reacts accordingly
     * @param xMax game width
     * @param yMax game height
     */
    private void touchedLowerButtons(int xMax,int yMax){
        if (Gdx.input.getY() > yMax/LOWER_BUTTONS_Y_MIN && Gdx.input.getY() < yMax/LOWER_BUTTONS_Y_MAX){

            if (Gdx.input.getX() > xMax/OPTIONS_X_MIN && Gdx.input.getX()< xMax/OPTIONS_X_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                game.setScreen(new OptionsView(game));

            }

            if (Gdx.input.getX() > xMax/HIGHSCORES_X_MIN && Gdx.input.getX()< xMax/HIGHSCORES_X_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                game.setScreen(new HighScoresView(game));
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
