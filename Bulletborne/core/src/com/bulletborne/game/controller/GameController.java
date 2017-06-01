package com.bulletborne.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.bulletborne.game.controller.entities.EnemyShip1Body;
import com.bulletborne.game.controller.entities.EnemyShip2Body;
import com.bulletborne.game.controller.entities.EnemyShip3Body;
import com.bulletborne.game.model.GameModel;
import com.bulletborne.game.model.entities.BulletModel;
import com.bulletborne.game.model.entities.EnemyShipModel;
import com.bulletborne.game.model.entities.PlayerModel;
import com.bulletborne.game.model.entities.EntityModel;
import com.bulletborne.game.controller.entities.BulletBody;
import com.bulletborne.game.controller.entities.Player2Body;
import com.bulletborne.game.controller.entities.PlayerBody;
import com.bulletborne.game.controller.entities.Player1Body;
import com.bulletborne.game.controller.entities.BarrierBody;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by Zé on 05/05/2017.
 */

public class GameController implements ContactListener{
    private static final int BULLET_DAMAGE = 1;
    public static final float ENEMY_1_BULLET_SPEED = 60f;
    public static final float ENEMY_2_BULLET_SPEED = 40f;
    public static final float ENEMY_3_BULLET_SPEED = 120f;
    public static final double ENEMY_SPAWN_LIMIT = 1.2;
    private static final int INITIAL_PLAYER_SPEED = 10;
    /**
     * The singleton instance of this controller
     */
    private static GameController instance;
    protected static final int SHIP_NUMBER_1=1;
    protected static final int SHIP_NUMBER_2=2;
    private static int shipNumber;
    /**
     * The amount of time that passed after the begining of the game
     */
    private float timePast = 0f;

    private float pointsGained = 0f;

    private boolean lost = false;

    /**
     * The arena width in meters.
     */
    public static final int ARENA_WIDTH = 100;

    /**
     * The arena height in meters.
     */
    public static final int ARENA_HEIGHT = 50;

    /**
     * The rotation speed in radians per second.
     */
    private static final float ROTATION_SPEED = 3f;

    private static final float FORCE_UP_DOWN_RATIO = 2.1f;

    /**
     * The acceleration impulse in newtons.
     */
    private static final float ACCELERATION_FORCE = 350000f;

    /**
     * The speed of bullets
     */
    private static final float BULLET_SPEED = 100f;

    /**
     * The speed of normal enemies
     */
    private static final float NORMAL_ENEMY_SPEED = -15f;

    /**
     * The speed of normal enemies
     */
    private static final float TANK_ENEMY_SPEED = -5f;

    /**
     * The speed of normal enemies
     */
    private static final float GLASSCANNON_ENEMY_SPEED = -30f;

    /**
     * Time between consecutive shots in seconds
     */
    private static final float TIME_BETWEEN_SHOTS = .15f;
    /**
     * Time between consecutive shots in seconds
     */
    private static final float TIME_BETWEEN_ENEMIES_CHANGER = 1.005f;

    private static final float ENEMIES_QUANTITY_TIME_CHANGER = 2f;
    /**
     * Time between consecutive enemies in seconds
     */
    private static float timeBetweenEnemies = 3f;

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * The spaceship body.
     */
    private PlayerBody playerBody;

    private final BarrierBody upperBarrierBody;

    private final BarrierBody lowerBarrierBody;

    /**
     * Accumulator used to calculate the simulation step.
     */
    private float accumulator;

    /**
     * Time left until gun cools down
     */
    private float timeToNextShoot;

    /**
     * Time left until next enemy spawn
     */
    private float timeToNextEnemy;

    private float timeToNextQuantityChange;

    private Sound shot;
    private Sound damage;
    private Sound explosion;

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    public GameController() {
        world = new World(new Vector2(0, 0), true);

        shot = Gdx.audio.newSound(Gdx.files.internal("shot.wav"));
        damage = Gdx.audio.newSound(Gdx.files.internal("damage.wav"));
        explosion = Gdx.audio.newSound(Gdx.files.internal("explode.wav"));

        if(shipNumber==SHIP_NUMBER_1)
            playerBody = new Player1Body(world, GameModel.getInstance().getPlayer());
        else if(shipNumber==SHIP_NUMBER_2)
            playerBody = new Player2Body(world, GameModel.getInstance().getPlayer());

        upperBarrierBody = new BarrierBody(world, GameModel.getInstance().getBarriers()[0]);
        lowerBarrierBody = new BarrierBody(world, GameModel.getInstance().getBarriers()[1]);

        world.setContactListener(this);
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    /**
     * Calculates the next physics step of duration delta (in seconds).
     *
     * @param delta The size of this physics step in seconds.
     */
    public void update(float delta) {
        timePast += delta;
        if (timePast <= 3)
            beginingAnimation(delta);
        else{
            GameModel.getInstance().update(delta);

            timeToNextShoot -= delta;
            timeToNextEnemy -= delta;
            timeToNextQuantityChange -= delta;

            for (EnemyShipModel model : GameModel.getInstance().getEnemies()) {
                enemyShoot(model, delta);
            }

            shoot();
            generateEnemy();

            if(!lost) {
                playerBody.setTransform(playerBody.getX(), playerBody.getY(), playerBody.getAngle() - ROTATION_SPEED / FORCE_UP_DOWN_RATIO * delta);
                playerBody.applyForceToCenter(0, -ACCELERATION_FORCE / FORCE_UP_DOWN_RATIO * delta, true);
            }

        }

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1 /60f) {
            world.step(1 /60f, 6, 2);
            accumulator -= 1 /60f;
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {

            if (body.getPosition().x< -5 && !(body.getUserData() instanceof PlayerModel)) {//todos os corpos existentes com x negativos são eliminados
                ((EntityModel) body.getUserData()).setFlaggedForRemoval(true);
            }

            if (body.getUserData() instanceof BulletModel && body.getPosition().x > 101){
                ((EntityModel) body.getUserData()).setFlaggedForRemoval(true);
            }

            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((EntityModel) body.getUserData()).setRotation(body.getAngle());
        }
    }

    private void beginingAnimation(float delta){
        if (timePast > 2)
            playerBody.setTransform(ARENA_WIDTH / 10, ARENA_HEIGHT / 2, 0);
        else if (timePast < 2)
            playerBody.setTransform(playerBody.getX() + INITIAL_PLAYER_SPEED * delta, playerBody.getY(), 0);
    }

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Accelerates the spaceship upward. The acceleration takes into consideration the
     * constant acceleration force and the delta for this simulation step.
     * Also rotates the ship to it's left.
     *
     * @param delta Duration of the rotation in seconds.
     */
    
    public void goUp(float delta) {
        playerBody.applyForceToCenter(0, ACCELERATION_FORCE * delta, true);
        playerBody.setTransform(playerBody.getX(), playerBody.getY(), playerBody.getAngle() + ROTATION_SPEED * delta);
        playerBody.setAngularVelocity(0);
    }

    /**
     * Shoots a bullet from the spaceship
     */
    private void shoot() {
        if(lost)
            return;
        if (timeToNextShoot < 0) {
            ArrayList<BulletModel> bulletsToShoot=playerBody.shoot();
            for(BulletModel bullet: bulletsToShoot) {
                BulletBody body = new BulletBody(world, bullet, true);
                body.setLinearVelocity(BULLET_SPEED);
            }
            shot.play(0.1f);
            timeToNextShoot = TIME_BETWEEN_SHOTS;
        }
    }

    private void enemyShoot(EnemyShipModel model, float delta){

        if (model.setTimeToNextShot(delta)){
            BulletModel bullet = GameModel.getInstance().createEnemyBullet(model);
            BulletBody body = new BulletBody(world, bullet, false);
            body.setLinearVelocity(getEnemyBulletSpeed(model));
            if(!lost)
                shot.play(0.1f);
        }
    }

    private float getEnemyBulletSpeed(EnemyShipModel model){
        switch(model.getType()){
            case ENEMY_SHIP1:
                return ENEMY_1_BULLET_SPEED;
            case ENEMY_SHIP2:
                return ENEMY_2_BULLET_SPEED;
            case ENEMY_SHIP3:
                return ENEMY_3_BULLET_SPEED;
            default:
                return BULLET_SPEED;
        }
    }

    private void generateEnemy() {
        if (timeToNextEnemy < 0){
            EnemyShipModel enemy = GameModel.getInstance().createEnemy();
            switch (enemy.getType()){
                case ENEMY_SHIP1:
                    EnemyShip1Body body = new EnemyShip1Body(world, enemy);
                    body.setLinearVelocity(NORMAL_ENEMY_SPEED);
                    break;
                case ENEMY_SHIP2:
                    EnemyShip2Body body2 = new EnemyShip2Body(world, enemy);
                    body2.setLinearVelocity(TANK_ENEMY_SPEED);
                    break;
                case ENEMY_SHIP3:
                    EnemyShip3Body body3 = new EnemyShip3Body(world, enemy);
                    body3.setLinearVelocity(GLASSCANNON_ENEMY_SPEED);
                    break;
            }
            timeToNextEnemy = timeBetweenEnemies;
        }
        if(timeToNextQuantityChange<0 && timeBetweenEnemies > ENEMY_SPAWN_LIMIT){
            timeToNextQuantityChange=ENEMIES_QUANTITY_TIME_CHANGER;
            timeBetweenEnemies=timeBetweenEnemies/TIME_BETWEEN_ENEMIES_CHANGER;
        }
    }

    private void enemyKilled(EnemyShipModel model){
        model.DamageTaken(BULLET_DAMAGE);
        if (model.getHP() > 0)
            damage.play(0.1f);
        else
            explosion.play(0.1f);
        switch(model.getType()){
            case ENEMY_SHIP1:
            case ENEMY_SHIP2:
                pointsGained += 5f;
            case ENEMY_SHIP3:
                pointsGained += 10f;
        }
    }

    public float getTimePast(){
        return timePast;
    }

    public float getPointsGained() {
        return pointsGained;
    }

    /**
     * A contact between two objects was detected
     *
     * @param contact the detected contact
     */
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if (bodyA.getUserData() instanceof PlayerModel || bodyB.getUserData() instanceof PlayerModel)
            borderShipCollision();

        if (bodyA.getUserData() instanceof BulletModel)
            ((BulletModel)bodyA.getUserData()).setFlaggedForRemoval(true);
        if (bodyB.getUserData() instanceof BulletModel)
            ((BulletModel)bodyB.getUserData()).setFlaggedForRemoval(true);

        if (bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof EnemyShipModel)
            enemyKilled((EnemyShipModel) bodyB.getUserData());

        if (bodyA.getUserData() instanceof EnemyShipModel && bodyB.getUserData() instanceof BulletModel)
            enemyKilled((EnemyShipModel) bodyA.getUserData());

    }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


    public void borderShipCollision() {
        lost = true;
    }

    public boolean getLost(){
        return lost;
    }

    /**
     * Removes objects that have been flagged for removal on the
     * previous step.
     */
    public void removeFlagged() {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            if (((EntityModel)body.getUserData()).isFlaggedToBeRemoved()) {
                GameModel.getInstance().remove((EntityModel) body.getUserData());
                world.destroyBody(body);
            }
        }
    }

    public void delete(){
        shot.dispose();
        damage.dispose();
        explosion.dispose();
        instance = null;
        GameModel.getInstance().delete();
    }

    public static void setShipNumber(int shipNumber) {
        GameController.shipNumber = shipNumber;
        GameModel.setShipNumber(shipNumber);
    }


}
