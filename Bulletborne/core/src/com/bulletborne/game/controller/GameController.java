package com.bulletborne.game.controller;

import com.bulletborne.game.controller.entities.EnemyShip1Body;
import com.bulletborne.game.controller.entities.EnemyShip2Body;
import com.bulletborne.game.controller.entities.EnemyShip3Body;
import com.bulletborne.game.model.GameModel;
import com.bulletborne.game.model.entities.BulletModel;
import com.bulletborne.game.model.entities.BarrierModel;
import com.bulletborne.game.model.entities.EnemyShipModel;
import com.bulletborne.game.model.entities.PlayerModel;
import com.bulletborne.game.model.entities.EntityModel;
import com.bulletborne.game.controller.entities.BulletBody;
import com.bulletborne.game.controller.entities.PlayerBody;
import com.bulletborne.game.controller.entities.BarrierBody;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Zé on 05/05/2017.
 */

public class GameController implements ContactListener{
    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

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
    private static final float ROTATION_SPEED = 2f;

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
    private static final float TIME_BETWEEN_SHOTS = .2f;
    /**
     * Time between consecutive shots in seconds
     */
    private static final float TIME_BETWEEN_ENEMIES_CHANGER = 1.01f;
    /**
     * Time between consecutive enemies in seconds
     */
    private static float timeBetweenEnemies = 2.5f;

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * The spaceship body.
     */
    private final PlayerBody playerBody;

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

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    public GameController() {
        world = new World(new Vector2(0, 0), true);

        playerBody = new PlayerBody(world, GameModel.getInstance().getPlayer());
        upperBarrierBody = new BarrierBody(world, GameModel.getInstance().getBarriers()[0]);
        lowerBarrierBody = new BarrierBody(world, GameModel.getInstance().getBarriers()[1]);
        /*
        List<AsteroidModel> asteroids = model.getAsteroids();
        for (AsteroidModel asteroid : asteroids)
            if (asteroid.getSize() == AsteroidModel.AsteroidSize.BIG)
                new BigAsteroidBody(world, asteroid);
            else if (asteroid.getSize() == AsteroidModel.AsteroidSize.MEDIUM)
                new MediumAsteroidBody(world, asteroid);*/

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
        GameModel.getInstance().update(delta);

        shoot();
        generateEnemy();

        timeToNextShoot -= delta;
        timeToNextEnemy -= delta;

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (body.getUserData() instanceof PlayerModel) {
                playerBody.setTransform(playerBody.getX(), playerBody.getY(), playerBody.getAngle() - ROTATION_SPEED / FORCE_UP_DOWN_RATIO * delta);
                playerBody.applyForceToCenter(0, -ACCELERATION_FORCE / FORCE_UP_DOWN_RATIO * delta, true);
            }
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((EntityModel) body.getUserData()).setRotation(body.getAngle());
        }
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
        if (timeToNextShoot < 0) {
            BulletModel bullet = GameModel.getInstance().createBullet(GameModel.getInstance().getPlayer());
            BulletBody body = new BulletBody(world, bullet);
            body.setLinearVelocity(BULLET_SPEED);
            timeToNextShoot = TIME_BETWEEN_SHOTS;
        }
    }

    private void generateEnemy() {
        if (timeToNextEnemy < 0){
            EnemyShipModel enemy = GameModel.getInstance().createEnemy();
            switch (enemy.getType()){
                case ENEMY_1:
                    EnemyShip1Body body = new EnemyShip1Body(world, enemy);
                    body.setLinearVelocity(NORMAL_ENEMY_SPEED);
                    break;
                case ENEMY_2:
                    EnemyShip2Body body2 = new EnemyShip2Body(world, enemy);
                    body2.setLinearVelocity(TANK_ENEMY_SPEED);
                    break;
                case ENEMY_3:
                    EnemyShip3Body body3 = new EnemyShip3Body(world, enemy);
                    body3.setLinearVelocity(GLASSCANNON_ENEMY_SPEED);
                    break;
            }
            timeToNextEnemy = timeBetweenEnemies;
            timeBetweenEnemies = timeBetweenEnemies/TIME_BETWEEN_ENEMIES_CHANGER;
        }
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

        if (bodyA.getUserData() instanceof PlayerModel && bodyB.getUserData() instanceof BarrierModel)
            borderShipCollision();
        if (bodyA.getUserData() instanceof BarrierModel && bodyB.getUserData() instanceof PlayerModel)
            borderShipCollision();

        if (bodyA.getUserData() instanceof BulletModel)
            ((BulletModel)bodyA.getUserData()).setFlaggedForRemoval(true);
        if (bodyB.getUserData() instanceof BulletModel)
            ((BulletModel)bodyB.getUserData()).setFlaggedForRemoval(true);

        if (bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof EnemyShipModel)
            bulletEnemyCollision();
        if (bodyA.getUserData() instanceof EnemyShipModel && bodyB.getUserData() instanceof BulletModel)
            bulletEnemyCollision();

        /*
        if (bodyA.getUserData() instanceof BulletModel)
            bulletCollision(bodyA);
        if (bodyB.getUserData() instanceof BulletModel)
            bulletCollision(bodyB);
*//*
        if (bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof AsteroidModel)
            bulletAsteroidCollision(bodyA, bodyB);
        if (bodyA.getUserData() instanceof AsteroidModel && bodyB.getUserData() instanceof BulletModel)
            bulletAsteroidCollision(bodyB, bodyA);*/

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

    public void bulletEnemyCollision(){

    }

    public void borderShipCollision() {
        //System.out.print("You Idiot!");
        //System.exit(0);
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
}
