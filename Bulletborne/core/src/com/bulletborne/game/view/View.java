package com.bulletborne.game.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.bulletborne.game.Bulletborne;


/**
 * Created by Ruben Torres on 29/05/2017.
 */

public abstract class View extends ScreenAdapter {

    /**
     * The game this screen belongs to.
     */
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

    /**
     * Creates this screen.
     */
    public View( Bulletborne game) {
        this.game=game;
        camera = createCamera();
    }

    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_HEIGHT / PIXEL_TO_METER/* * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth())*/);

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
    public void render(float delta){}
}
