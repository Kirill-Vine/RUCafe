package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    private double priceSHORT = 1.89;
    private double priceTALL = 2.29;
    private double priceGRANDE = 2.69;
    private double priceVENTI = 3.09;
    private double price = 0;
    private int numberOfCups = 1;
    private double priceofAddons = 0;
    private CheckBox frenchVanilla, sweetCream, irishCream, caramel, mocha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        frenchVanilla = (CheckBox)findViewById(R.id.frenchVanillaBox);
        frenchVanilla.setOnClickListener(this);
        sweetCream = (CheckBox)findViewById(R.id.sweetBox);
        sweetCream.setOnClickListener(this);
        irishCream = (CheckBox)findViewById(R.id.irishCreamBox);
        irishCream.setOnClickListener(this);
        caramel = (CheckBox)findViewById(R.id.caramelBox);
        caramel.setOnClickListener(this);
        mocha = (CheckBox)findViewById(R.id.mochaBox);
        mocha.setOnClickListener(this);
        Spinner sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        Spinner numberOfCupsSpinner = (Spinner) findViewById(R.id.numberOfCupsSpinner);
        sizeSpinner.setOnItemSelectedListener(this);
        numberOfCupsSpinner.setOnItemSelectedListener(this);
        List<String> cupSizeCategories = new ArrayList<>();
        cupSizeCategories.add("SHORT");
        cupSizeCategories.add("TALL");
        cupSizeCategories.add("GRANDE");
        cupSizeCategories.add("VENTI");

        List<Integer> noOfCupsCategories = new ArrayList<>();
        noOfCupsCategories.add(1);
        noOfCupsCategories.add(2);
        noOfCupsCategories.add(3);
        noOfCupsCategories.add(4);
        noOfCupsCategories.add(5);
        ArrayAdapter<String> dataAdapterCupSize = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cupSizeCategories);
        ArrayAdapter<Integer> dataAdapterNoOfCups = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, noOfCupsCategories);

        dataAdapterCupSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterNoOfCups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sizeSpinner.setAdapter(dataAdapterCupSize);
        numberOfCupsSpinner.setAdapter(dataAdapterNoOfCups);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frenchVanillaBox:
                if (frenchVanilla.isChecked()) {
                    priceofAddons+=.3;
                    Toast.makeText(this, "French Vanilla selected", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sweetBox:
                if (sweetCream.isChecked()) {
                    priceofAddons+=.3;
                    Toast.makeText(this, "Sweet Cream selected", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.irishCreamBox:
                if (irishCream.isChecked()) {
                    priceofAddons+=.3;
                    Toast.makeText(this, "Irish Cream selected", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.caramelBox:
                if (caramel.isChecked()) {
                    priceofAddons+=.3;
                    Toast.makeText(this, "Caramel selected", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mochaBox:
                if (mocha.isChecked()) {
                    priceofAddons+=.3;
                    Toast.makeText(this, "Mocha selected", Toast.LENGTH_SHORT).show();
                }
                break;
        }
//        price+=priceofAddons;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.sizeSpinner:
                String cupSize = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + cupSize, Toast.LENGTH_SHORT).show();
                if(cupSize.equals("SHORT")){
                    price=priceSHORT;
                }else if(cupSize.equals("TALL")){
                    price=priceTALL;
                }else if(cupSize.equals("GRANDE")){
                    price=priceGRANDE;
                }else if(cupSize.equals("VENTI")){
                    price=priceVENTI;
                }
                break;
            case R.id.numberOfCupsSpinner:
                numberOfCups = (int) parent.getItemAtPosition(position);
                if(numberOfCups==1){
                    Toast.makeText(parent.getContext(), "Selected: " + numberOfCups + " cup", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(parent.getContext(), "Selected: " + numberOfCups + " cups", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        changeSubtotal("Subtotal: ", (price+priceofAddons)*numberOfCups);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void changeSubtotal(String subTotalText, double price){
        TextView subtotal = (TextView)findViewById(R.id.subTotalText);
        subtotal.setText(subTotalText+" "+price);
    }
}