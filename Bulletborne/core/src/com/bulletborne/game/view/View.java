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


/**
 * Created by Ruben Torres on 29/05/2017.
 */

public abstract class View extends ScreenAdapter {
    private static final float BACK_X_MIN=64.0f;
    private static final float BACK_X_MAX=9.14f;
    private static final float BACK_Y_MIN=1.24f;
    private static final float BACK_Y_MAX=1.02f;

    protected Sound buttonClick;
    protected static float audioChanger;

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

    protected static final int SHIP_NUMBER_1=1;
    protected static final int SHIP_NUMBER_2=2;

    protected static int shipNumber;

    /**
     * Creates this screen.
     */
    public View( Bulletborne game) {
        this.game=game;
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("button_click.wav"));
        camera = createCamera();
    }

    @Override
    public void dispose() {
        super.dispose();
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
     * Renders this screen.
     *
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

    protected void handleInputs(float delta) {

        if (Gdx.input.justTouched()) {
            int xMax= Gdx.graphics.getWidth();
            int yMax= Gdx.graphics.getHeight();
            touchedBackButton(xMax,yMax);
        }
    }

    protected void touchedBackButton(int xMax, int yMax) {
        if (Gdx.input.getX() > xMax/BACK_X_MIN && Gdx.input.getX()<xMax/BACK_X_MAX)
            if (Gdx.input.getY() > yMax/BACK_Y_MIN && Gdx.input.getY() < yMax/BACK_Y_MAX) {
                buttonClick.play(AUDIO_VOLUME*audioChanger);
                game.setScreen(new MainMenuView(game));
            }
    }

    public static void setShipNumber(int ShipNumber) {
        shipNumber = ShipNumber;
    }

    public static void setBestScore(int HighScore) {
        bestScore=HighScore;
    }


    public static void setAudioChanger(float audioChanger) {
        View.audioChanger = audioChanger;
    }
}
