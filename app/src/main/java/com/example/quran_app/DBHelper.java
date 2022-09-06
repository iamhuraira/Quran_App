package com.example.quran_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

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
    public void importDataBaseFromAssets() throws IOException {

        this.getReadableDatabase();

        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        Toast.makeText(context.getApplicationContext(), "Successfully Imported", Toast.LENGTH_SHORT).show();

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public ArrayList<SurahData> getSurahNames() throws  Exception
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<SurahData> surahList = new ArrayList<>();

        Cursor cs = db.rawQuery("SELECT * FROM tsurah", null );

        // moving our cursor to first position.
        if (cs.moveToFirst()) {
            do {
                surahList.add(new SurahData(cs.getString(0), cs.getString(4), cs.getString(2), cs.getString(3) ));
            } while (cs.moveToNext());
        }

        cs.close();
        db.close();

        return surahList;

    }

    public ArrayList<SurahData> getSurahAyahs(int surahNumber)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<SurahData> ayah = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT * FROM tayah WHERE SuraID = " + surahNumber, null);

        // moving our cursor to first position.
        if (cs.moveToFirst()) {
            do {
                ayah.add(new SurahData(cs.getString(4), cs.getString(3)));
            } while (cs.moveToNext());
        }

        cs.close();
        db.close();

        return ayah;
    }

    public ArrayList<SurahData> getSearchResult(String search)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<SurahData> searchResult = new ArrayList<>();

        Cursor cs = db.rawQuery("SELECT * FROM tsurah WHERE SurahNameE LIKE '%" + search + "%'", null);
        if (cs.moveToFirst()) {
            do {
                searchResult.add(new SurahData(cs.getString(0), cs.getString(4), cs.getString(2), cs.getString(3) ));
            } while (cs.moveToNext());
        }

        cs.close();
        db.close();

        return searchResult;
    }


}
