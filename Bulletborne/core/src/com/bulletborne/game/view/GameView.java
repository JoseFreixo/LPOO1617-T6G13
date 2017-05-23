package com.bulletborne.game.view;

import com.bulletborne.game.controller.GameController;
import com.bulletborne.game.model.GameModel;
import com.bulletborne.game.model.entities.PlayerModel;
import com.bulletborne.game.view.entities.EntityView;
import com.bulletborne.game.view.entities.ViewFactory;
import com.bulletborne.game.Bulletborne;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import java.util.List;

import static com.bulletborne.game.controller.GameController.ARENA_HEIGHT;
import static com.bulletborne.game.controller.GameController.ARENA_WIDTH;

/**
 * Created by Zé on 05/05/2017.
 */
public class GameView extends ScreenAdapter {
    /**
     * Used to debug the position of the physics fixtures
     */
    private static final boolean DEBUG_PHYSICS = false;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 0.04f;

    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float VIEWPORT_WIDTH = 20;

    /**
     * The game this screen belongs to.
     */
    private final Bulletborne game;

    /**
     * The camera used to show the viewport.
     */
    private final OrthographicCamera camera;

    /**
     * A renderer used to debug the physical fixtures.
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * The transformation matrix used to transform meters into
     * pixels in order to show fixtures in their correct places.
     */
    private Matrix4 debugCamera;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameView(Bulletborne game) {
        this.game = game;

        loadAssets();

        camera = createCamera();
    }

    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));

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
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load( "Player_ship.png" , Texture.class);
        this.game.getAssetManager().load( "Player_ship2.png" , Texture.class);

        this.game.getAssetManager().load( "bullet.png" , Texture.class);
        this.game.getAssetManager().load( "bullet_ally.png" , Texture.class);

        this.game.getAssetManager().load( "Empty_background.png" , Texture.class);

        this.game.getAssetManager().load( "Enemy_ship.png" , Texture.class);
        this.game.getAssetManager().load( "Enemy_ship2.png" , Texture.class);
        this.game.getAssetManager().load( "Enemy_ship3.png" , Texture.class);

        this.game.getAssetManager().finishLoading();
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {
        handleInputs(delta);

        GameController.getInstance().update(delta);

        camera.position.set(GameModel.getInstance().getPlayer().getX() / PIXEL_TO_METER, GameModel.getInstance().getPlayer().getY() / PIXEL_TO_METER, 0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        drawBackground();
        drawEntities();
        game.getBatch().end();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }
    }

    /**
     * Handles any inputs and passes them to the controller.
     *
     * @param delta time since last time inputs where handled in seconds
     */
    private void handleInputs(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            GameController.getInstance().rotateLeft(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            GameController.getInstance().rotateRight(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            GameController.getInstance().accelerate(delta);
        }
    }

    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {
    /*  List<AsteroidModel> asteroids = model.getAsteroids();
        for (AsteroidModel asteroid : asteroids) {
            if (asteroid.getSize() == AsteroidModel.AsteroidSize.BIG) {
                bigAsteroidView.update(asteroid);
                bigAsteroidView.draw(game.getBatch());
            } else if (asteroid.getSize() == AsteroidModel.AsteroidSize.MEDIUM) {
                mediumAsteroidView.update(asteroid);
                mediumAsteroidView.draw(game.getBatch());
            }
        }
        */
        PlayerModel player = GameModel.getInstance().getPlayer();
        EntityView view = ViewFactory.makeView(game, player);
        view.update(player);
        view.draw(game.getBatch());
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("Empty_background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, 0, 0, (int)(ARENA_WIDTH / PIXEL_TO_METER), (int) (ARENA_HEIGHT / PIXEL_TO_METER));
    }
}
