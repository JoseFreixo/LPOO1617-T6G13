package com.bulletborne.game;

/**
 * Created by Ruben Torres on 02/06/2017.
 */

public interface AndroidSaving {
    void saveBestScore(int bestScore);
    int loadBestScore();

    void saveShipNumber(int shipNumber);
    int loadShipNumber();

    void saveGlobalSoundChanger(float soundChanger);
    float loadGlobalSoundChanger();

    void saveMusicVolume(float volume);
    float loadMusicVolume();
}
