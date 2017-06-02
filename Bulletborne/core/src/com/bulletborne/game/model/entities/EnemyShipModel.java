package com.bulletborne.game.model.entities;

/**
 * Created by r_tor on 26/05/2017.
 */

public class EnemyShipModel extends EntityModel {

    private static final int NORMAL_SHIP_HP = 3;
    private static final int TANK_SHIP_HP = 5;
    private static final int GLASSCANNON_SHIP_HP = 1;

    private static final float NORMAL_SHIP_TTS = 0.5f;
    private static final float TANK_SHIP_TTS = 0.8f;
    private static final float GLASSCANNON_SHIP_TTS = 0.3f;

    private static final float NORMAL_SHIP_TFS = 2/3f;
    private static final float TANK_SHIP_TFS = 2f;
    private static final float GLASSCANNON_SHIP_TFS = 1/3f;

    private ModelType type;

    private float timeToNextShot;

    private float timeBetweenShots;

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

    @Override
    public ModelType getType() {
        return type;
    }

    public void DamageTaken(int damage){
        healthPoints-=damage;
        if(healthPoints<=0)
            this.setFlaggedForRemoval(true);
    }

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

    public int getHP() { return healthPoints; }

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

    public boolean setTimeToNextShot(float delta){
        timeToNextShot -= delta;
        if (timeToNextShot < 0){
            timeToNextShot = timeBetweenShots;
            return true;
        }
        return false;
    }

}
