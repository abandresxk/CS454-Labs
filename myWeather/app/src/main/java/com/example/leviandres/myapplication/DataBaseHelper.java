package com.example.leviandres.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper {
    static final String CREATE_SQL = "create table weathers (id integer primary key, main text, description text);";
    static final String TABLE_NAME = "weathers";
    static final String DB_NAME = "weather_db";
    static final String DROP_SQL = "drop table if exists weathers";
    static final String GET_SQL = "select * from weathers where id=";
    static final String GET_ALL_SQL = "select w.*, w.id as _id from weathers w;";
    static final String TAG = " DBHELPER ";

    public DataBaseHelper(Context context){
        super(context, DB_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_SQL);
    }



    public boolean insertWeathers(ArrayList<MyWeather> list){
        boolean bool = true;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DROP_SQL);
        db.execSQL(CREATE_SQL);


        for (MyWeather sw : list){
            ContentValues cv = new ContentValues();
            cv.put(Constants.MAIN_KEY, sw.main);
            cv.put(Constants.DESCRIPTION_KEY, sw.desc);
            db.insert(TABLE_NAME, null, cv);
        }
        return bool;
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(GET_ALL_SQL, null);


    }


}