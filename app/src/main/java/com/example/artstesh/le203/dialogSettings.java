package com.example.artstesh.le203;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

/**
 * Created by artstesh on 09.01.2016.
 */
public class dialogSettings extends ActionBarActivity implements View.OnClickListener
{
    Button btnSettingsOK;
    EditText txtSetNmbrWrds;
    EditText txtSetTransLang;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogsettings);
        btnSettingsOK = (Button) findViewById(R.id.btnSettingsOK);
        txtSetNmbrWrds = (EditText) findViewById(R.id.txtSetNmbrWrds);
        txtSetTransLang = (EditText) findViewById(R.id.txtSetTransLang);
        loadPreferences();

        btnSettingsOK.setOnClickListener(this);

//        txtSetNmbrWrds.setText(Integer.toString(LEActivity.stepL
        txtSetNmbrWrds.setText(Integer.toString(LEActivity.wordsCounter.getLearnStep()));
        txtSetTransLang.setText(LEActivity.translationLang);
    }


    public void onClick(View view)
    {

        if (view == btnSettingsOK)
        {
            int temp = Integer.parseInt(String.valueOf(txtSetNmbrWrds.getText()));
            if (temp > 1)
            {
                LEActivity.wordsCounter.setLearnStep(temp);
            }

            LEActivity.translationLang = String.valueOf(txtSetTransLang.getText());

            savePreferences();

            Intent intent = new Intent(dialogSettings.this, Lobby.class);
            startActivity(intent);
        }

    }

    public void savePreferences()
    {
        preferences = getSharedPreferences("settings.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("savedStepLength", LEActivity.wordsCounter.getLearnStep());
        editor.putString("translationLang", LEActivity.translationLang);
        editor.apply();
    }

    public void loadPreferences()
    {
        preferences = getSharedPreferences("settings.txt", Context.MODE_PRIVATE);
//        LEActivity.stepLength = preferences.getInt("savedStepLength", 4);
        LEActivity.wordsCounter.setLearnStep(preferences.getInt("savedStepLength", 4));
        LEActivity.translationLang = preferences.getString("translationLang", LEActivity.translationLang);
    }
}
