package com.example.artstesh.le203;

import android.content.ContentValues;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by artstesh on 19.01.2016.
 */
public class Dictionary
{

    static boolean flagger = true;


   /* public static void fullDictionary(InputStream is)
    {
        long startTime = System.currentTimeMillis();
        while (flagger)
        {
            try
            {

                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                String str_data = new String(buffer);

                String[] temp = str_data.split(" ");

                for (int z = 0; z < temp.length; z += 2)
                {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("word", temp[z].toLowerCase());
                    contentValues.put("translation", temp[z + 1].toLowerCase());
                    LEActivity.database.insert("translatorDB", null, contentValues);

                }

                LEActivity.rightMenuDetector = false;
                flagger = false;

            } catch (FileNotFoundException e)
            {
                break;
            } catch (Exception ignored)
            {
            }
        }

        long time = System.currentTimeMillis() - startTime;
        System.out.println("!!!!!!!!!!!!!!!!!!!" + time);
    }*/


    public static void fullDictionary(InputStream is)
    {
        long startTime = System.currentTimeMillis();

        while (flagger)
        {
            try
            {

                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                String str_data = new String(buffer);

                String[] temp = str_data.split(" ");

                LEActivity.database.beginTransaction();

                for (int z = 0; z < temp.length; z += 2)
                {

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("word", temp[z].toLowerCase());
                    contentValues.put("translation", temp[z + 1].toLowerCase());
                    LEActivity.database.insert("translatorDB", null, contentValues);
                    if(z%1000 == 0)
                    {
                        LEActivity.database.setTransactionSuccessful();
                        LEActivity.database.endTransaction();
                        LEActivity.database.beginTransaction();
                    }
                }
                LEActivity.database.setTransactionSuccessful();
                LEActivity.rightMenuDetector = false;
                flagger = false;

            } catch (FileNotFoundException e)
            {
                break;
            } catch (Exception ignored)
            {
            }
            finally
            {
                LEActivity.database.endTransaction();
            }
        }

        long time = System.currentTimeMillis() - startTime;
        System.out.println("!!!!!!!!!!!!!!!!!!!" + time);
    }
}
