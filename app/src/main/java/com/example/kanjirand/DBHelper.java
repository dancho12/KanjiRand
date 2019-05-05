package com.example.kanjirand;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "kanjiDb";
    public static final String TABLE_CONTACTS = "kanjis";

    public static final String KEY_ID = "_id";
    public static final String KEY_KANJI = "kanji";
    public static final String KEY_KUN = "kun";
    public static final String KEY_ON = "onyomi";
    public static final String KEY_MEN = "meaning";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key," + KEY_KANJI + " text," + KEY_KUN + " text," + KEY_ON + " text," + KEY_MEN + " text"+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }
}