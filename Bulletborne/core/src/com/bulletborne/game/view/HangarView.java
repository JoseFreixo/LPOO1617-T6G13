package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bulletborne.game.Bulletborne;


import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;



public class HangarView extends View {
    /**
     * Ratio used to know the y_min position of the arrow buttons
     */
    private static final float ARROWS_Y_MIN=2.74f;
    /**
     * Ratio used to know the y_max position of the arrow buttons
     */
    private static final float ARROWS_Y_MAX= 1.57f;
    /**
     * Ratio used to know the x_min position of the left arrow button
     */
    private static final float LEFTARROW_X_MIN=13.06f;
    /**
     * Ratio used to know the x_max position of the left arrow button
     */
    private static final float LEFTARROW_X_MAX=5.61f;
    /**
     * Ratio used to know the x_min position of the right arrow button
     */
    private static final float RIGHTARROW_X_MIN=1.22f;
    /**
     * Ratio used to know the x_max position of the right arrow button
     */
    private static final float RIGHTARROW_X_MAX=1.08f;


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
    protected void handleInputs(float delta) {

        if (Gdx.input.justTouched()) {
            int xMax= Gdx.graphics.getWidth();
            int yMax= Gdx.graphics.getHeight();
            touchedArrowButtons(xMax,yMax);
            touchedBackButton(xMax,yMax);
        }
    }

    /**
     * Checks if the arrow buttons were pressed and reacts accordingly
     * @param xMax game width
     * @param yMax game height
     */
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

    /**
     * Draw the current selected ship
     */
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
