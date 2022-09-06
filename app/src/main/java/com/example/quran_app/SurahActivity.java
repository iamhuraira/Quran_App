package com.example.quran_app;

import android.os.Bundle;
import android.widget.ListView;

import com.example.quran_app.databinding.ActivitySurahBinding;

import java.util.ArrayList;

public class SurahActivity extends DrawerBaseActivity {

    ActivitySurahBinding surahBinding;

    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);


        //Adding Drawer to Activity and Allocating title
        surahBinding = ActivitySurahBinding.inflate(getLayoutInflater());
        setContentView(surahBinding.getRoot());

        //Allocating title
        allocateActivityTitle("Surah");

        // GET value of Surah from MainActivity through Intent
        int surahNumber = getIntent().getIntExtra("number", 1);

        //DBHelper creation
        DBHelper dbHelper = new DBHelper(getApplicationContext());

        //Getting data in Arraylist from DB
        ArrayList<SurahData> surah = dbHelper.getSurahAyahs(surahNumber);

        //Populationg data in Custom List
        lst = findViewById(R.id.list_surah);
        CustomSurahAdapter adapter = new CustomSurahAdapter(this, R.layout.custom_ayah_list, surah);
        lst.setAdapter(adapter);


    }
}
