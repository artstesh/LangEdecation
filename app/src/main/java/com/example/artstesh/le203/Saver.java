package com.example.artstesh.le203;

import android.content.SharedPreferences;

/**
 * Created by artstesh on 25.01.2016.
 */
public class Saver
{

    private static Saver saver;

    public SharedPreferences preferences;
    private int stepLength = LEActivity.wordsCounter.getLearnStep();

    private Saver()
    {

    }

    public static Saver getSaver()
    {
        if (saver == null)
        {
            return new Saver();
        }
        else
        {
            return saver;
        }
    }
/*
    public void savePreferences()
    {
        preferences = getSharedPreferences("settings.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("savedStepLength",LEActivity.stepLength);
        editor.commit();
    }

    public void loadPreferences()
    {
        preferences = getSharedPreferences("settings.txt", Context.MODE_PRIVATE);
        stepLength = preferences.getInt("savedStepLength", 4);
    }
    */
}
