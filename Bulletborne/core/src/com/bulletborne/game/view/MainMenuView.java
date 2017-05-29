package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.bulletborne.game.Bulletborne;

import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;

/**
 * Created by Ruben Torres on 29/05/2017.
 */

public class MainMenuView extends View {


    private static final float BUTTONS_X_MIN=(Gdx.graphics.getWidth()/1.61f);
    private static final float BUTTONS_X_MAX= (Gdx.graphics.getWidth()/1.12f);
    private static final float PLAY_Y_MIN=(Gdx.graphics.getHeight()/2.69f);
    private static final float PLAY_Y_MAX=(Gdx.graphics.getHeight()/1.93f);
    private static final float HANGAR_Y_MIN=(Gdx.graphics.getHeight()/1.85f);
    private static final float HANGAR_Y_MAX=(Gdx.graphics.getHeight()/1.46f);
    private static final float EXIT_Y_MIN=(Gdx.graphics.getHeight()/1.42f);
    private static final float EXIT_Y_MAX=(Gdx.graphics.getHeight()/1.19f);
    private static final float LOWER_BUTTONS_Y_MIN=(Gdx.graphics.getHeight()/1.15f);
    private static final float LOWER_BUTTONS_Y_MAX= (Gdx.graphics.getHeight()/1.00f);
    private static final float OPTIONS_X_MIN=(Gdx.graphics.getWidth()/80.00f);
    private static final float OPTIONS_X_MAX=(Gdx.graphics.getWidth()/14.00f);
    private static final float CREDITS_X_MIN=(Gdx.graphics.getWidth()/12.08f);
    private static final float CREDITS_X_MAX=(Gdx.graphics.getWidth()/7.03f);
    private static final float HIGHSCORES_X_MIN=(Gdx.graphics.getWidth()/6.42f);
    private static final float HIGHSCORES_X_MAX=(Gdx.graphics.getWidth()/4.63f);
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
        camera.position.set((ARENA_WIDTH/2) / PIXEL_TO_METER, (ARENA_HEIGHT/2) / PIXEL_TO_METER, 0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

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

        if (Gdx.input.isTouched()) {
            touchedButtons();
            touchedLowerButtons();
        }
    }

    private void touchedButtons(){
        if (Gdx.input.getX() > BUTTONS_X_MIN && Gdx.input.getX()<BUTTONS_X_MAX) {

            if (Gdx.input.getY() > PLAY_Y_MIN && Gdx.input.getY() < PLAY_Y_MAX)
                game.setScreen(new GameView(game));

            if (Gdx.input.getY() > HANGAR_Y_MIN && Gdx.input.getY() < HANGAR_Y_MAX)
                System.out.println("ENTREI NO HANGAR");

            if (Gdx.input.getY() > EXIT_Y_MIN && Gdx.input.getY() < EXIT_Y_MAX)
                System.exit(0);
        }
    }

    private void touchedLowerButtons(){
        if (Gdx.input.getY() > LOWER_BUTTONS_Y_MIN && Gdx.input.getY() < LOWER_BUTTONS_Y_MAX){

            if (Gdx.input.getX() > OPTIONS_X_MIN && Gdx.input.getX()<OPTIONS_X_MAX)
                System.out.println("ENTREI NO OPTIONS");

            if (Gdx.input.getX() > HIGHSCORES_X_MIN && Gdx.input.getX()<HIGHSCORES_X_MAX)
                System.out.println("ENTREI NO HIGHSCORES");

            if (Gdx.input.getX() > CREDITS_X_MIN && Gdx.input.getX()<CREDITS_X_MAX)
                System.out.println("ENTREI NO CREDITS");
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
