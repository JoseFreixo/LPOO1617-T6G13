package com.bulletborne.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.bulletborne.game.Bulletborne;

import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;




public abstract class View extends ScreenAdapter {
    /**
     * Ratio used to know the x_min position of the back button
     */
    private static final float BACK_X_MIN=64.0f;
    /**
     * Ratio used to know the x_max position of the back button
     */
    private static final float BACK_X_MAX=9.14f;
    /**
     * Ratio used to know the y_min position of the back button
     */
    private static final float BACK_Y_MIN=1.24f;
    /**
     * Ratio used to know the y_max position of the back button
     */
    private static final float BACK_Y_MAX=1.02f;

    /**
     * Sound of clicking a button
     */
    protected Sound buttonClick;

    /**
     * Sound Changer
     */
    protected static float audioChanger;

    /**
     * HighScore
     */
    protected static int bestScore;
    /**
     * The game this screen belongs to.
     */
    protected static float AUDIO_VOLUME =1f;
    protected final Bulletborne game;
    /**
     * Used to debug the position of the physics fixtures
     */
    protected static final boolean DEBUG_PHYSICS = false;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 0.04f;

    /**
     * The width of the viewport in meters.
     */
    protected static final float VIEWPORT_WIDTH = 100;

    /**
     * The height of the viewport in meters.
     */
    protected static final float VIEWPORT_HEIGHT = 50;

    /**
     * The camera used to show the viewport.
     */
    protected final OrthographicCamera camera;

    /**
     * A renderer used to debug the physical fixtures.
     */
    protected Box2DDebugRenderer debugRenderer;

    /**
     * The transformation matrix used to transform meters into
     * pixels in order to show fixtures in their correct places.
     */
    protected Matrix4 debugCamera;

    /**
     * int corresponding to the player ship type 1
     */
    protected static final int SHIP_NUMBER_1=1;
    /**
     * int corresponding to the player ship type 2
     */
    protected static final int SHIP_NUMBER_2=2;

    /**
     * Current select player ship
     */
    protected static int shipNumber;

    /**
     * Creates this screen.
     */
    public View( Bulletborne game) {
        this.game=game;
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("button_click.wav"));
        camera = createCamera();
    }


    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_HEIGHT / PIXEL_TO_METER);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
        }

        return camera;
    }

    /**
     * Common Render for all extended view classes
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta){
        camera.position.set((ARENA_WIDTH/2) / PIXEL_TO_METER, (ARENA_HEIGHT/2) / PIXEL_TO_METER, 0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        if (!game.getMusic().isPlaying()){
            game.setPlaying("Skyrim8bitfinal_repeat.wav");
            game.setVolume();
            game.getMusic().setLooping(true);
        }

        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }

    /**
     * Handles any inputs
     * @param delta time since last input in seconds.
     */
    protected void handleInputs(float delta) {

        if (Gdx.input.justTouched()) {
            int xMax= Gdx.graphics.getWidth();
            int yMax= Gdx.graphics.getHeight();
            touchedBackButton(xMax,yMax);
        }
    }

    /**
     * Checks if the back button was pressed
     * @param xMax game width
     * @param yMax game height
     */
    protected void touchedBackButton(int xMax, int yMax) {
        if (Gdx.input.getX() > xMax/BACK_X_MIN && Gdx.input.getX()<xMax/BACK_X_MAX)
            if (Gdx.input.getY() > yMax/BACK_Y_MIN && Gdx.input.getY() < yMax/BACK_Y_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                game.setScreen(new MainMenuView(game));
            }
    }

    /**
     * Sets the ship the user chose to play with
     * @param ShipNumber int corresponding to the ship
     */
    public static void setShipNumber(int ShipNumber) {
        shipNumber = ShipNumber;
    }

    /**
     * Sets the best Score the user got till this moment
     * @param HighScore Best Score so far
     */
    public static void setBestScore(int HighScore) {
        bestScore=HighScore;
    }

    /**
     * Sets the Sound volume changer
     * @param audioChanger Sound Volume changer
     */
    public static void setAudioChanger(float audioChanger) {
        View.audioChanger = audioChanger;
    }
}
