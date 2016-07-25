package com.example.artstesh.le203;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;


import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class DownloadActivity extends ActionBarActivity implements View.OnClickListener
{

    EditText etDownload;
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        etDownload = (EditText) findViewById(R.id.etDownload);
        btnDownload = (Button) findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v == btnDownload)
        {
            String temp = String.valueOf(etDownload.getText());
            if (temp != null)
            {

                try
                {
                    OutputStream outputStream = openFileOutput("maintext.txt", 0);
                    OutputStreamWriter osw = new OutputStreamWriter(outputStream);
                    osw.write(temp);
                    osw.close();
                    LEActivity.resetVars();
                } catch (Throwable ignored){}

                Intent intent = new Intent(DownloadActivity.this, LEActivity.class);
                startActivity(intent);
            }
        }
    }
}
