package com.bulletborne.game;

/**
 * Created by Ruben Torres on 02/06/2017.
 */

public interface AndroidSaving {
    void saveBestScore(int bestScore);
    int loadBestScore();
}
