package com.bulletborne.game.desktop;

import com.bulletborne.game.AndroidSaving;

/**
 * Created by Ruben Torres on 02/06/2017.
 */

public class DesktopPlatform implements AndroidSaving {
    @Override
    public void saveBestScore(int bestScore) {}

    @Override
    public int loadBestScore() {
        return 0;
    }

    @Override
    public void saveShipNumber(int shipNumber) {}

    @Override
    public int loadShipNumber() {
        return 1;
    }

    @Override
    public void saveGlobalSoundChanger(float soundChanger) {}

    @Override
    public float loadGlobalSoundChanger() {
        return 0.5f;
    }
}
