package com.bulletborne.game.controller;

import com.badlogic.gdx.graphics.Cursor;
import com.bulletborne.game.model.GameModel;
import com.bulletborne.game.model.entities.BarrierModel;
import com.bulletborne.game.model.entities.PlayerModel;
import com.bulletborne.game.model.entities.EntityModel;
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

import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

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

    /**
     * The acceleration impulse in newtons.
     */
    private static final float ACCELERATION_FORCE = 350000f;

    private static final float ROTATION_DOWN_RATIO = 2.280f;

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
        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }


        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            verifyBounds(body);
            if (body.getUserData() instanceof PlayerModel) {
                playerBody.setTransform(playerBody.getX(), playerBody.getY(), playerBody.getAngle() - ROTATION_SPEED / 2.1f * delta);
                playerBody.applyForceToCenter(0, -ACCELERATION_FORCE / 2.1f * delta, true);
            }
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((EntityModel) body.getUserData()).setRotation(body.getAngle());
        }
    }

    /**
     * Verifies if the body is inside the arena bounds and if not
     * wraps it around to the other side.
     *
     * @param body The body to be verified.
     */
    private void verifyBounds(Body body) {
        if (body.getPosition().x < 0)
            body.setTransform(ARENA_WIDTH, body.getPosition().y, body.getAngle());

        if (body.getPosition().y < 0)
            body.setTransform(body.getPosition().x, ARENA_HEIGHT, body.getAngle());

        if (body.getPosition().x > ARENA_WIDTH)
            body.setTransform(0, body.getPosition().y, body.getAngle());

        if (body.getPosition().y > ARENA_HEIGHT)
            body.setTransform(body.getPosition().x, 0, body.getAngle());
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
        System.out.print("WTF?");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void borderShipCollision() {
        System.out.print("You Idiot!");
        System.exit(0);
    }
}
