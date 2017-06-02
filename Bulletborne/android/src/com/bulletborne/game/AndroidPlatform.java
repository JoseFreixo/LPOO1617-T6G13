package com.bulletborne.game;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.bulletborne.game.view.View;

/**
 * Created by Ruben Torres on 02/06/2017.
 */

public class AndroidPlatform implements AndroidSaving {

    public static final String BEST_SCORE_KEY = "BestScore";
    private static final  String PREF_NAME = "prefs";
    private Context context;

    public AndroidPlatform(Context context){
        this.context=context;
    }

    @Override
    public void saveBestScore(int bestScore){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(BEST_SCORE_KEY, View.getBestScore());
        editor.commit();
    }

    @Override
    public int loadBestScore(){
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        int bestScore= sp.getInt(BEST_SCORE_KEY, 0);
        return bestScore;
    }
}
