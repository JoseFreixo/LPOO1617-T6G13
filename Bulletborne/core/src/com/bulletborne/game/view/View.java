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
    protected Sound buttonClick;
    protected static float audioChanger=0.1f;

    protected static int bestScore;
    /**
     * The game this screen belongs to.
     */
    protected static float AUDIO_VOLUME =0.5f;
    protected final Bulletborne game;
    /**
     * Used to debug the position of the physics fixtures
     */
    protected static final boolean DEBUG_PHYSICS = true;

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

    protected static int shipNumber=SHIP_NUMBER_1;

    /**
     * Creates this screen.
     */
    public View( Bulletborne game) {
        this.game=game;
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("button_click.wav"));
        camera = createCamera();
    }

    public static int getBestScore() {
        return bestScore;
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
            game.setPlaying("Skyrim8bitfinal_repeat.wav", AUDIO_VOLUME);
            game.setVolume(AUDIO_VOLUME,audioChanger);
            game.getMusic().setLooping(true);
        }

        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }

    public static void setBestScore(int HighScore) {
        bestScore=HighScore;
    }
}
