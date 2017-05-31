package com.bulletborne.game.view;

import com.bulletborne.game.controller.GameController;
import com.bulletborne.game.model.GameModel;
import com.bulletborne.game.model.entities.PlayerModel;
import com.bulletborne.game.model.entities.EnemyShipModel;
import com.bulletborne.game.model.entities.BulletModel;
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
public class GameView extends View {


    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameView(Bulletborne game) {
        super(game);
        GameController.setShipNumber(shipNumber);
        loadAssets();
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
        GameController.getInstance().removeFlagged();

        handleInputs(delta);

        GameController.getInstance().update(delta);

        camera.position.set((ARENA_WIDTH/2) / PIXEL_TO_METER, (ARENA_HEIGHT/2) / PIXEL_TO_METER, 0);
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

        if (GameController.getInstance().getLost()){
            GameController.getInstance().delete();
            game.setScreen(new GameView(game));
        }
    }

    /**
     * Handles any inputs and passes them to the controller.
     *
     * @param delta time since last time inputs where handled in seconds
     */
    private void handleInputs(float delta) {
        if (GameController.getInstance().getTimePast() < 3)
            return;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            GameController.getInstance().goUp(delta);
        }
        if (Gdx.input.isTouched()) {
            GameController.getInstance().goUp(delta);
        }
    }

    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {
        List<EnemyShipModel> enemies = GameModel.getInstance().getEnemies();
        for (EnemyShipModel enemy : enemies){
            EntityView view = ViewFactory.makeView(game, enemy);
            view.update(enemy);
            view.draw(game.getBatch());
        }

        List<BulletModel> bullets = GameModel.getInstance().getBullets();
        for (BulletModel bullet : bullets) {
            EntityView view = ViewFactory.makeView(game, bullet);
            view.update(bullet);
            view.draw(game.getBatch());
        }

        List<BulletModel> enemyBullets = GameModel.getInstance().getEnemyBullets();
        for (BulletModel bullet : enemyBullets) {
            EntityView view = ViewFactory.makeView(game, bullet);
            view.update(bullet);
            view.draw(game.getBatch());
        }

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
        game.getBatch().draw(background, 0, 0, ARENA_WIDTH / PIXEL_TO_METER, ARENA_HEIGHT / PIXEL_TO_METER);
    }
}
