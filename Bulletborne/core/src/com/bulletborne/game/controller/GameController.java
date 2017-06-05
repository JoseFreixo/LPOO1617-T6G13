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
    /**
     * Damage inflicted by the bullets
     */
    private static final int BULLET_DAMAGE = 1;

    /**
     * The Normal Enemy Bullet Speed
     */
    public static final float ENEMY_1_BULLET_SPEED = 60f;
    /**
     * The Tank Enemy Bullet Speed
     */
    public static final float ENEMY_2_BULLET_SPEED = 40f;
    /**
     * The Glass Cannon Enemy Bullet Speed
     */
    public static final float ENEMY_3_BULLET_SPEED = 120f;

    /**
     * Limit of enemy ships spawns per second
     */
    public static final double ENEMY_SPAWN_LIMIT = 1.2f;

    /**
     * Player ship initial animation speed
     */
    private static final int INITIAL_PLAYER_SPEED = 10;

    /**
     * Volume of the Sound made by shooting
     */
    public static final float SOUNDS_VOLUME = 0.2f;

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * int corresponding to the Player's ship Type 1
     */
    protected static final int SHIP_NUMBER_1=1;
    /**
     * int corresponding to the Player's ship Type 2
     */
    protected static final int SHIP_NUMBER_2=2;

    /**
     * Player ship type chosen by the user
     */
    private static int shipNumber;

    /**
     * Float multipled by the volume to change the audio of the game
     */
    private static float audioChanger;

    /**
     * The amount of time that passed after the begining of the game
     */
    private float timePast = 0f;

    /**
     * points gained by destroying enemy ships
     */
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

    /**
     * Force Applied to the player's ship to go up and down
     */
    private static final float FORCE_UP_DOWN_RATIO = 2.1f;

    /**
     * The acceleration impulse in newtons.
     */
    private static final float ACCELERATION_FORCE = 350000f;

    /**
     * The speed of player bullets
     */
    private static final float BULLET_SPEED = 100f;

    /**
     * The speed of normal enemies
     */
    private static final float NORMAL_ENEMY_SPEED = -15f;

    /**
     * The speed of tank enemies
     */
    private static final float TANK_ENEMY_SPEED = -5f;

    /**
     * The speed of tank enemies
     */
    private static final float GLASSCANNON_ENEMY_SPEED = -30f;

    /**
     * Time between consecutive shots in seconds
     */
    private static final float TIME_BETWEEN_SHOTS = .15f;
    /**
     * Time between consecutive enemy ships changer
     */
    private static final float TIME_BETWEEN_ENEMIES_CHANGER = 1.005f;

    /**
     * Time needed to change the Time Between enemies
     */
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

    /**
     * Top Barrier Body
     */
    private final BarrierBody upperBarrierBody;
    /**
     * Bottom Barrier Body
     */
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
     * Time Left to apply the Enemy spawn changer
     */
    private float timeToNextQuantityChange;

    /**
     * bullets being fired sound
     */
    private Sound shot;
    /**
     * enemy's taking damage sound
     */
    private Sound damage;
    /**
     * enemy's being killed sound
     */
    private Sound explosion;

    /**
     * True if sounds will be used, false if not
     */
    private boolean allowSounds;

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    public GameController() {
        world = new World(new Vector2(0, 0), true);

        allowSounds = false;

        if(shipNumber==SHIP_NUMBER_1)
            playerBody = new Player1Body(world, GameModel.getInstance().getPlayer());
        else if(shipNumber==SHIP_NUMBER_2)
            playerBody = new Player2Body(world, GameModel.getInstance().getPlayer());

        upperBarrierBody = new BarrierBody(world, GameModel.getInstance().getBarriers()[0]);
        lowerBarrierBody = new BarrierBody(world, GameModel.getInstance().getBarriers()[1]);

        world.setContactListener(this);
    }


    public void loadSounds(){
        allowSounds = true;
        shot = Gdx.audio.newSound(Gdx.files.internal("shot.wav"));
        damage = Gdx.audio.newSound(Gdx.files.internal("damage.wav"));
        explosion = Gdx.audio.newSound(Gdx.files.internal("explode.wav"));
    }

    /**
     * Returns a singleton instance of the game controller
     *
     * @return the singleton instance
     */
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
            beginningAnimation(delta);
        else{

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

    /**
     * Used To make the beginning Animation
     * @param delta
     */
    private void beginningAnimation(float delta){
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
     * Shoots bullets from the player ship position perspective
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
            if (allowSounds)
                shot.play(SOUNDS_VOLUME *audioChanger);
            timeToNextShoot = TIME_BETWEEN_SHOTS;
        }
    }

    /**
     * Shoots a bullet from the enemy ship position perspective
     */
    private void enemyShoot(EnemyShipModel model, float delta){

        if (model.setTimeToNextShot(delta)){
            BulletModel bullet = GameModel.getInstance().createEnemyBullet(model);
            BulletBody body = new BulletBody(world, bullet, false);
            body.setLinearVelocity(getEnemyBulletSpeed(model));
            if(!lost && allowSounds)
                shot.play(SOUNDS_VOLUME *audioChanger);
        }
    }

    /**
     * returns the enemy bullet speed depending on the type of enemy ship
     * @return float bullet speed
     */
    private float getEnemyBulletSpeed(EnemyShipModel model){
        switch(model.getType()){
            case ENEMY_SHIP_NORMAL:
                return ENEMY_1_BULLET_SPEED;
            case ENEMY_SHIP_TANK:
                return ENEMY_2_BULLET_SPEED;
            case ENEMY_SHIP_GLASSCANNON:
                return ENEMY_3_BULLET_SPEED;
            default:
                return BULLET_SPEED;
        }
    }

    /**
     * Returns the time until next enemy spawns
     * @return the time until next enemy spawns
     */
    public float getTimeToNextEnemy() {
        return timeToNextEnemy;
    }

    /**
     * Sets the time until next enemy spawns
     * @param timeToNextEnemy
     */
    public void setTimeToNextEnemy(float timeToNextEnemy) {
        this.timeToNextEnemy = timeToNextEnemy;
    }

    /**
     * Creates a new enemy
     */
    public int generateEnemy() {
        int i = 0;
        if (timeToNextEnemy < 0){
            EnemyShipModel enemy = GameModel.getInstance().createEnemy();
            switch (enemy.getType()){
                case ENEMY_SHIP_NORMAL:
                    EnemyShip1Body body = new EnemyShip1Body(world, enemy);
                    body.setLinearVelocity(NORMAL_ENEMY_SPEED);
                    i = 1;
                    break;
                case ENEMY_SHIP_TANK:
                    EnemyShip2Body body2 = new EnemyShip2Body(world, enemy);
                    body2.setLinearVelocity(TANK_ENEMY_SPEED);
                    i = 2;
                    break;
                case ENEMY_SHIP_GLASSCANNON:
                    EnemyShip3Body body3 = new EnemyShip3Body(world, enemy);
                    body3.setLinearVelocity(GLASSCANNON_ENEMY_SPEED);
                    i = 3;
                    break;
            }
            timeToNextEnemy = timeBetweenEnemies;
        }
        if(timeToNextQuantityChange<0 && timeBetweenEnemies > ENEMY_SPAWN_LIMIT){
            timeToNextQuantityChange=ENEMIES_QUANTITY_TIME_CHANGER;
            timeBetweenEnemies=timeBetweenEnemies/TIME_BETWEEN_ENEMIES_CHANGER;
        }
        return i;
    }

    /**
     * Inflicts damage to a enemy ship and seed if it's dead, also updates the points gained
     * @param model of the enemy ship
     */
    public void enemyKilled(EnemyShipModel model){
        model.DamageTaken(BULLET_DAMAGE);
        if (model.getHP() > 0 && allowSounds)
            damage.play(SOUNDS_VOLUME *audioChanger);
        else if (allowSounds)
            explosion.play(SOUNDS_VOLUME *audioChanger);
        switch(model.getType()){
            case ENEMY_SHIP_NORMAL:
            case ENEMY_SHIP_TANK:
                pointsGained += 5f;
            case ENEMY_SHIP_GLASSCANNON:
                pointsGained += 10f;
        }
    }

    /**
     * Returns the Time past since the beginning of the game
     * @return float time Past
     */
    public float getTimePast(){
        return timePast;
    }

    /**
     * returns the points gained by inflicting damage to the enemys
     * @return float points gained
     */
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

    /**
     * sets the flag lost to true if the player's ship got hit
     */
    public void borderShipCollision() {
        lost = true;
    }

    /**
     * Returns the flag, true if the player lost the game false otherwise
     * @return boolean lost
     */
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

    /**
     * Disposes of the sounds created and deletes the GameController and GameModel singleton instances
     */
    public void delete(){
        if (allowSounds) {
            shot.dispose();
            damage.dispose();
            explosion.dispose();
        }
        instance = null;
        GameModel.getInstance().delete();
    }

    /**
     * Sets the player Ship type chosen by the user
     * @param shipNumber
     */
    public static void setShipNumber(int shipNumber) {
        GameController.shipNumber = shipNumber;
        GameModel.setShipNumber(shipNumber);
    }

    /**
     * Returns the ship number
     * @return the ship number
     */
    public static int getShipNumber() {
        return shipNumber;
    }

    /**
     * Sets the audio changer applied to the all sounds volume
     * @param audioChanger
     */
    public static void setAudioChanger(float audioChanger) {
        GameController.audioChanger = audioChanger;
    }

    /**
     * Returns the playerBody
     * @return PlayerBody
     */
    public PlayerBody getPlayerBody() {
        return playerBody;
    }
}
