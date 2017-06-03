package com.bulletborne.game;


/**
 * Only works for android
 */
public interface AndroidSaving {
    /**
     * Saves the current high score
     * @param bestScore
     */
    void saveBestScore(int bestScore);

    /**
     *  Loads the current high score
     * @return bestScore
     */
    int loadBestScore();


    /**
     * Saves the current selected player ship
     * @param shipNumber
     */
    void saveShipNumber(int shipNumber);
    /**
     * Loads the last selected player ship
     * @return shipNumber
     */
    int loadShipNumber();


    /**
     * Saves the current audioChange ratio
     * @param soundChanger
     */
    void saveSoundChanger(float soundChanger);
    /**
     * Loads the last audioChange ratio
     * @return soundChanger
     */
    float loadSoundChanger();


    /**
     * Saves the current music Volume ratio
     * @param volume
     */
    void saveMusicVolume(float volume);
    /**
     * Loads the current music Volume ratio
     * @return volume
     */
    float loadMusicVolume();
}
