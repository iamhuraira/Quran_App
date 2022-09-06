package com.example.quran_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class DBHelper  extends SQLiteOpenHelper {
    Context context;
    String DB_PATH;
    String divider = "/";
    String DB_NAME = "quran_app.db";

    public  DBHelper(Context context){
        super(context, "quran_app.db", null, 1);
        this.context = context;
        DB_PATH = divider + "data" + divider + "data" + divider + context.getPackageName() + divider + "databases/";
    }
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
    public  boolean isDataBaseExists(){
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }
}
