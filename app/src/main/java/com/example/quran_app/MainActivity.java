package com.example.quran_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quranmajeed.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends DrawerBaseActivity {


    ActivityMainBinding activityMainBinding;
    DBHelper dbHelper;
    ArrayList<SurahData> surahNamesList;

    ListView lst;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());


        //Importing DB
        dbHelper = new DBHelper(getApplicationContext());
        if(!dbHelper.isDataBaseExists())
        {
            try
            {
                dbHelper.importDataBaseFromAssets();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


        //Importing Data For DataBase
        try
        {
            surahNamesList = dbHelper.getSurahNames();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // Setting the List to Adapter
        lst = findViewById(R.id.main_list);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_list, surahNamesList);
        lst.setAdapter(adapter);

        // Setting onCLick Listener for Items of List
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SurahActivity.class);
                intent.putExtra("number", i+1);
                startActivity(intent);
            }
        });

        //Implementing Search Button Functionality
        searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView searchBarTxt = findViewById(R.id.searchBarTxt);

                //Getting data in ArrayList
                ArrayList<SurahData> searchResult = dbHelper.getSearchResult(searchBarTxt.getText().toString());

                //Populating data in Custom List
                lst = findViewById(R.id.main_list);
                CustomAdapter adapter = new CustomAdapter(MainActivity.this, R.layout.custom_list, searchResult);
                lst.setAdapter(adapter);

            }
        });


//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, surahNamesList);


//        lst = findViewById(R.id.main_list);
//        QDH surahNames = new QDH();
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  surahNames.urduSurahNames);
//        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_list,  surahNames.urduSurahNames);
//        lst.setAdapter(adapter);
//
//        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                int index = surahNames.SSP[i];
//                int end = surahNames.SSP[i+1];
//
//                Intent intent = new Intent(MainActivity.this, SurahActivity.class);
//
//                intent.putExtra("index", index);
//                intent.putExtra("end", end);
//                startActivity(intent);
//            }
//        });
    }
}