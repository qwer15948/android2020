package com.example.termproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version){
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists alcohol("
                + "_id integer primary key autoincrement, "
                + "date text, "
                + "beer real, "
                + "soju real, "
                + "time integer);";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "drop table if exists alcohol";
        db.execSQL(sql);

        onCreate(db);
    }
}
