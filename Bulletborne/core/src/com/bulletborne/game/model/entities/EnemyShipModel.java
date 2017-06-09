package com.bulletborne.game.model.entities;



public class EnemyShipModel extends EntityModel {
    /**
     * HP of the NORMAL enemy ship
     */
    private static final int NORMAL_SHIP_HP = 3;
    /**
     * HP of the TANK enemy ship
     */
    private static final int TANK_SHIP_HP = 5;
    /**
     * HP of the GLASS CANNON enemy ship
     */
    private static final int GLASSCANNON_SHIP_HP = 1;

    /**
     * Time beetween shots off the NORMAL enemy ship
     */
    private static final float NORMAL_SHIP_TTS = 0.5f;
    /**
     * Time beetween shots off the TANK enemy ship
     */
    private static final float TANK_SHIP_TTS = 0.8f;
    /**
     * Time beetween shots off the GLASS CANNON enemy ship
     */
    private static final float GLASSCANNON_SHIP_TTS = 0.3f;

    /**
     * Time for the first shot (after created) off the NORMAL enemy ship
     */
    private static final float NORMAL_SHIP_TFS = 2/3f;
    /**
     * Time for the first shot (after created) off the TANK enemy ship
     */
    private static final float TANK_SHIP_TFS = 1.5f;
    /**
     * Time for the first shot (after created) off the GLASS CANNON enemy ship
     */
    private static final float GLASSCANNON_SHIP_TFS = 1/3f;

    /**
     * This Enemy ship type
     */
    private ModelType type;

    /**
     * Time left for the ship to shoot again
     */
    private float timeToNextShot;

    /**
     * Time between shots of this enemy ship
     */
    private float timeBetweenShots;

    /**
     * HP of this enemy ship
     */
    int healthPoints;

    /**
     * Creates a new ship model in a certain position and having a certain rotation.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param rotation the rotation in radians
     */
    public EnemyShipModel(float x, float y, float rotation, ModelType type) {
        super(x, y, rotation);
        this.type=type;
        setHP();
        setTTS();
    }

    /**
     * returns the ModelType of this EntityModel
     * @return ModelType type of the  enemy ship
     */
    @Override
    public ModelType getType() {
        return type;
    }

    /**
     * Inflict damage to this ship, if dead sets flag for removal
     * @param damage damage taken
     */
    public void DamageTaken(int damage){
        healthPoints-=damage;
        if(healthPoints<=0)
            this.setFlaggedForRemoval(true);
    }

    /**
     * Sets the initial HP of this enemy ship
     */
    public void setHP() {
        switch (type) {
            case ENEMY_SHIP_NORMAL:
                healthPoints = NORMAL_SHIP_HP;
                break;
            case ENEMY_SHIP_TANK:
                healthPoints = TANK_SHIP_HP;
                break;
            case ENEMY_SHIP_GLASSCANNON:
                healthPoints = GLASSCANNON_SHIP_HP;
                break;
        }
    }

    /**
     * returns the current HP
     * @return int HP
     */
    public int getHP() { return healthPoints; }

    /**
     * Sets the time to the next shot and the time between shots of this enemy ship
     */
    private void setTTS() {
        switch (type) {
            case ENEMY_SHIP_NORMAL:
                timeBetweenShots = NORMAL_SHIP_TTS;
                timeToNextShot = NORMAL_SHIP_TFS;
                break;
            case ENEMY_SHIP_TANK:
                timeBetweenShots = TANK_SHIP_TTS;
                timeToNextShot = TANK_SHIP_TFS;
                break;
            case ENEMY_SHIP_GLASSCANNON:
                timeBetweenShots = GLASSCANNON_SHIP_TTS;
                timeToNextShot = GLASSCANNON_SHIP_TFS;
                break;
        }
    }

    /**
     * Updates the time to the next shot
     */
    public boolean setTimeToNextShot(float delta){
        timeToNextShot -= delta;
        if (timeToNextShot < 0){
            timeToNextShot = timeBetweenShots;
            return true;
        }
        return false;
    }

}
