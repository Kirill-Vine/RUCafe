package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private double price = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        Spinner sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);

        sizeSpinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("SHORT");
        categories.add("TALL");
        categories.add("GRANDE");
        categories.add("VENTI");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sizeSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
        if(item.equals("SHORT")){
            price=1.89;
        }else if(item.equals("TALL")){
            price=2.29;
        }else if(item.equals("GRANDE")){
            price=2.69;
        }else if(item.equals("VENTI")){
            price=3.09;
        }

        changeSubtotal("Subtotal: ", price);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void changeSubtotal(String subTotalText, double price){

        TextView subtotal = (TextView)findViewById(R.id.subTotalText);
        subtotal.setText(subTotalText+" "+price);
    }
}