package com.example.artstesh.le203;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by artstesh on 28.01.2016.
 */

//Base interface for SQLite
class DBHelper extends SQLiteOpenHelper implements BaseColumns
{
    public static final String DATABASE_NAME = "translatorDB";
    private static final int DATABASE_VERSION = 1;
    public static final String WORD_COLUMN = "word";
    public static final String TRANSLATION_COLUMN = "translation";
    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_NAME + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + WORD_COLUMN
            + " text not null, " + TRANSLATION_COLUMN + " text not null); ";

    // конструктор суперкласса
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version)
    {
        super(context, name, factory, version);
    }


    //Заполняем базу данныx


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // создаем таблицу с полями
        System.out.println("Basa dannych sozdana");
        db.execSQL(DATABASE_CREATE_SCRIPT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_NAME);
        // Создаём новую таблицу
        onCreate(db);
    }
}




