package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bulletborne.game.Bulletborne;


import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;

/**
 * Created by r_tor on 01/06/2017.
 */

public class HangarView extends View {

    private static final float ARROWS_Y_MIN=2.74f;
    private static final float ARROWS_Y_MAX= 1.57f;
    private static final float LEFTARROW_X_MIN=13.06f;
    private static final float LEFTARROW_X_MAX=5.61f;
    private static final float RIGHTARROW_X_MIN=1.22f;
    private static final float RIGHTARROW_X_MAX=1.08f;
    private static final float BACK_X_MIN=64.0f;
    private static final float BACK_X_MAX=9.14f;
    private static final float BACK_Y_MIN=1.24f;
    private static final float BACK_Y_MAX=1.02f;
    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public HangarView(Bulletborne game) {
        super(game);
        loadAssets();
    }


    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load( "Hangar_background.png" , Texture.class);
        this.game.getAssetManager().load( "rsz_player_ship.png" , Texture.class);
        this.game.getAssetManager().load( "rsz_player_ship2.png" , Texture.class);
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
        drawShip();
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
            touchedArrowButtons(xMax,yMax);
            touchedBackButton(xMax,yMax);
        }
    }

    private void touchedBackButton(int xMax, int yMax) {
        if (Gdx.input.getX() > xMax/BACK_X_MIN && Gdx.input.getX()<xMax/BACK_X_MAX)
            if (Gdx.input.getY() > yMax/BACK_Y_MIN && Gdx.input.getY() < yMax/BACK_Y_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                game.setScreen(new MainMenuView(game));
            }
    }

    private void touchedArrowButtons(int xMax, int yMax) {
        if (Gdx.input.getY() > yMax/ARROWS_Y_MIN && Gdx.input.getY() < yMax/ARROWS_Y_MAX){

            if (Gdx.input.getX() > xMax/LEFTARROW_X_MIN && Gdx.input.getX()< xMax/LEFTARROW_X_MAX)
                if(shipNumber!=SHIP_NUMBER_1) {
                    buttonClick.play(AUDIO_VOLUME*audioChanger);
                    shipNumber--;
                }

            if (Gdx.input.getX() > xMax/RIGHTARROW_X_MIN && Gdx.input.getX()< xMax/RIGHTARROW_X_MAX)
                if(shipNumber!=SHIP_NUMBER_2) {
                    buttonClick.play(AUDIO_VOLUME*audioChanger);
                    shipNumber++;
                }
        }
    }

    private void drawShip(){
        Texture shipToDraw=null;
        if(shipNumber==SHIP_NUMBER_1)
            shipToDraw = game.getAssetManager().get("rsz_player_ship.png", Texture.class);
        else if(shipNumber==SHIP_NUMBER_2)
            shipToDraw = game.getAssetManager().get("rsz_player_ship2.png", Texture.class);

        Sprite sprite= new Sprite(new TextureRegion(shipToDraw, shipToDraw.getWidth(), shipToDraw.getHeight()));
        sprite.setCenter(ARENA_WIDTH / PIXEL_TO_METER/2, ARENA_HEIGHT / PIXEL_TO_METER/2);

        sprite.draw(game.getBatch());
    }
    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("Hangar_background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, ARENA_WIDTH / PIXEL_TO_METER, ARENA_HEIGHT / PIXEL_TO_METER);
    }
}
