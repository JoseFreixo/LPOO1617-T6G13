package com.bulletborne.game.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    private BitmapFont fontInitialAnimation;
    private BitmapFont fontCurrentPoints;
    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameView(Bulletborne game) {
        super(game);
        GameController.setShipNumber(shipNumber);
        loadAssets();
        fontInitialAnimation=new BitmapFont(Gdx.files.internal("myFont.fnt"));
        fontInitialAnimation.getData().scale(0.5f);
        fontCurrentPoints=new BitmapFont(Gdx.files.internal("myFontScore.fnt"));
        fontCurrentPoints.getData().scale(0.5f);
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

        super.render(delta);

        game.getBatch().begin();
        drawBackground();
        drawEntities();
        drawFonts();
        game.getBatch().end();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }

        if (GameController.getInstance().getLost()){
            GameController.getInstance().delete();
            game.setScreen(new MainMenuView(game));
        }
    }

    private void drawFonts() {

        float number = GameController.getInstance().getTimePast();
        float xStart = fontInitialAnimation.getRegion().getRegionWidth() / 4;
        float yStart = fontInitialAnimation.getRegion().getRegionHeight() / 4;
        if ((3 - number) >= 0.0f) {
            fontInitialAnimation.draw(game.getBatch(), (Integer.toString((int) (4 - number))), (ARENA_WIDTH / PIXEL_TO_METER / 2) - xStart / 2, (ARENA_HEIGHT / PIXEL_TO_METER / 2) + yStart);
        } else {
            if (number < 3.5f)
                fontInitialAnimation.draw(game.getBatch(), "GO", ARENA_WIDTH / PIXEL_TO_METER / 2 - xStart, (ARENA_HEIGHT / PIXEL_TO_METER / 2) + yStart);
            fontCurrentPoints.draw(game.getBatch(), Integer.toString((int) (GameController.getInstance().getPointsGained() + number * 10) - 30), 1 / PIXEL_TO_METER, 49 / PIXEL_TO_METER);
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
