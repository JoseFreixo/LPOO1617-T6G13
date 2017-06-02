package com.bulletborne.game;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ruben Torres on 02/06/2017.
 */

public class AndroidPlatform implements AndroidSaving {

    public static final String BEST_SCORE_KEY = "BestScore";
    private static final  String PREF_NAME = "prefs";
    private static final String SHIP_NUMBER_KEY = "ShipNumber";
    private static final String SOUND_CHANGER_KEY = "soundChanger";
    private Context context;

    public AndroidPlatform(Context context){
        this.context=context;
    }

    @Override
    public void saveBestScore(int bestScore){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(BEST_SCORE_KEY, bestScore);
        editor.commit();
    }

    @Override
    public int loadBestScore(){
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        int bestScore= sp.getInt(BEST_SCORE_KEY, 0);
        return bestScore;
    }

    @Override
    public void saveShipNumber(int shipNumber){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(SHIP_NUMBER_KEY, shipNumber);
        editor.commit();
    }
    @Override
    public int loadShipNumber(){
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        int shipNumber= sp.getInt(SHIP_NUMBER_KEY, 1);
        return shipNumber;
    }

    @Override
    public void saveGlobalSoundChanger(float soundChanger) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putFloat(SOUND_CHANGER_KEY, soundChanger);
        editor.commit();
    }

    @Override
    public float loadGlobalSoundChanger() {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        float soundChanger= sp.getFloat(SOUND_CHANGER_KEY, 0.5f);
        return soundChanger;
    }
}
