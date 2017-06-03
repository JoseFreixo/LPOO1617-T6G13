package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bulletborne.game.Bulletborne;

import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;



public class OptionsView extends View {
    /**
     * Font used to print the current values of audioChanger and musicVolume
     */
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

    /**
     * Draws the current values of audioChanger and musicVolume
     */
    private void drawCurrentOptions() {
        fontSoundChanger.draw(game.getBatch(), Float.toString((int)(game.getMusicVolume()*10)/10f), (ARENA_WIDTH / PIXEL_TO_METER / 1.36f), (ARENA_HEIGHT / PIXEL_TO_METER / 1.75f) );
        fontSoundChanger.draw(game.getBatch(), Float.toString((int)(audioChanger*10)/10.0f), (ARENA_WIDTH / PIXEL_TO_METER / 1.36f), (ARENA_HEIGHT / PIXEL_TO_METER / 3.1f) );
    }

    /**
     * Handles any inputs
     * @param delta time since last input in seconds.
     */
    @Override
    protected void handleInputs(float delta) {
        if (Gdx.input.justTouched()) {
            int xMax= Gdx.graphics.getWidth();
            int yMax= Gdx.graphics.getHeight();
            touchedBackButton(xMax,yMax);
            touchedArrows(xMax,yMax);
        }
    }

    /**
     * Checks if the Buttons y coordinate to change the volume was pressed
     * @param xMax game width
     * @param yMax game height
     */
    private void touchedArrows(int xMax, int yMax) {
        if (Gdx.input.getY() > yMax/2.42f && Gdx.input.getY() < yMax/1.91f)
            touchedArrowsBetweenX(xMax,yMax,false);

        if (Gdx.input.getY() > yMax/1.50f && Gdx.input.getY() < yMax/1.29f)
            touchedArrowsBetweenX(xMax,yMax,true);

    }

    /**
     * Checks if the Buttons x coordinate to change the volume was pressed
     * @param xMax game width
     * @param yMax game height
     * @param changeSound boolean Distinguishes if the audioChanger buttons were pressed or the musicVolume
     */
    private void touchedArrowsBetweenX(int xMax, int yMax, boolean changeSound){

        if (Gdx.input.getX() > xMax/1.58f && Gdx.input.getX()< xMax/1.45f) {
            buttonClick.play(AUDIO_VOLUME*audioChanger);
            if (changeSound) {
                decreaseAudioChanger();
            }
            else{
               decreaseMusicVolume();
            }
        }
        if (Gdx.input.getX() > xMax/1.17f && Gdx.input.getX()< xMax/1.1)
        {
            buttonClick.play(AUDIO_VOLUME*audioChanger);
            if (changeSound) {
                increaseAudioChanger();
            }
            else{
                increaseMusicVolume();
            }
        }
    }

    /**
     * Decreases audioChanger by 0.1
     */
    private void decreaseAudioChanger(){
        if(audioChanger>0.1f)
            audioChanger-=0.1f;
        else
            audioChanger=0.0f;
    }
    /**
     * Decreases musicVolume by 0.1
     */
    private void decreaseMusicVolume(){
        if(game.getMusicVolume()>0.1f)
            game.setMusicVolume(game.getMusicVolume()-0.1f);
        else
            game.setMusicVolume(0.0f);
        game.setVolume();
    }
    /**
     * Increases audioChanger by 0.1
     */
    private void increaseAudioChanger(){
            if(audioChanger<0.9f)
                audioChanger+=0.1f;
            else
                audioChanger=1.0f;
    }
    /**
     * Increases musicVolume by 0.1
     */
    private void increaseMusicVolume() {
        if(game.getMusicVolume()<0.9f)
            game.setMusicVolume(game.getMusicVolume()+0.1f);
        else
            game.setMusicVolume(1.0f);
        game.setVolume();
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
