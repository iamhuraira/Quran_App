package com.example.quran_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<SurahData> {

    ArrayList<SurahData> data;

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<SurahData> data) {
        super(context, R.layout.custom_list, data);
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.custom_list, null, true);
        TextView txtUrdu = view.findViewById(R.id.urdu_txt) ;
        txtUrdu.setText(data.get(position).surahNameUrdu);
        return view;
    }
}
