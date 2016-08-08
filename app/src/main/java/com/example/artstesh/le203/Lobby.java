package com.example.artstesh.le203;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

//The first window for user. Function of moving between other parts of app.
public class Lobby extends ActionBarActivity implements View.OnClickListener
{

    Button btnSettings;
    Button btnNewText;
    Button btnPreviousText;
    Button btnAbout;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);


        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnNewText = (Button) findViewById(R.id.btnNewText);
        btnPreviousText = (Button) findViewById(R.id.btnPreviousText);
        btnSettings = (Button) findViewById(R.id.btnSettings);

        btnSettings.setOnClickListener(this);
        btnNewText.setOnClickListener(this);
        btnPreviousText.setOnClickListener(this);
        btnAbout.setOnClickListener(this);

        try
        {
            FileInputStream inputStream = openFileInput("maintext.txt");
        } catch (FileNotFoundException e)
        {
            btnPreviousText.setEnabled(false);
        }

      //  LEActivity.resetVars();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v)
    {
        if (v == btnNewText)
        {
            Intent intent = new Intent(Lobby.this, DownloadActivity.class);
            startActivity(intent);
        }
        else if (v == btnPreviousText)
        {
            Intent intent = new Intent(Lobby.this, LEActivity.class);
            startActivity(intent);
        }
        else if (v == btnSettings)
        {
            Intent intent = new Intent(Lobby.this, dialogSettings.class);
            startActivity(intent);
        }
        else if (v == btnAbout)
        {
            Intent intent = new Intent(Lobby.this, Help.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Lobby Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.artstesh.le203/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop()
    {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Lobby Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.artstesh.le203/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
