package com.dmitrys.nightingaleweather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCityHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DBCity";
    private static final String DATABASE_TABLE_CITIES = "cities";
    private static final int DATABASE_VERSION = 1;

    public DBCityHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table cities ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "country text,"
                + "date text,"
                + "description text,"
                + "humidity text,"
                + "pressure text,"
                + "temperature text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CITIES);
        onCreate(db);
    }
}
