package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.Arrays;
import java.util.ArrayList;


import android.os.Bundle;

public class DonutActivity extends AppCompatActivity {
    ArrayList<String> flavors = (ArrayList)Arrays.asList(Donut.getFlavors());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView rcView = (RecyclerView) findViewById(R.id.donutRecycler);
        DonutsAdapter adapter = new DonutsAdapter(this, flavors);
        rcView.setLayoutManager(new LinearLayoutManager(this));
    }
}