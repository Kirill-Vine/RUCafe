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
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import android.widget.Button;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    public static Coffee currentCoffee = new Coffee(sizes.SHORT);
    public static final int MAX_CUPS = 5;
    public static final int MIN_CUPS = 1;
    private Spinner sizeSpinner, numberOfCupsSpinner;
    private Button coffeeOrderButton;
    private CheckBox frenchVanilla, sweetCream, irishCream, caramel, mocha;
    void setCheckBoxListener(CheckBox cb, String addon) {
        cb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(cb.isChecked()) {
                    currentCoffee.addAddon(addon);
                } else {
                    currentCoffee.removeAddon(addon);
                }
                updateSubTotal();
            }
        });
    }
    void setAddonButtons() {
        coffeeOrderButton = (Button)findViewById(R.id.coffeeOrderButton);
        frenchVanilla = (CheckBox)findViewById(R.id.frenchVanillaBox);
        sweetCream = (CheckBox)findViewById(R.id.sweetBox);
        irishCream = (CheckBox)findViewById(R.id.irishCreamBox);
        caramel = (CheckBox)findViewById(R.id.caramelBox);
        mocha = (CheckBox)findViewById(R.id.mochaBox);
        setCheckBoxListener(frenchVanilla, "French Vanilla");
        setCheckBoxListener(sweetCream, "Sweet Cream");
        setCheckBoxListener(irishCream, "Irish Cream");
        setCheckBoxListener(caramel, "Caramel");
        setCheckBoxListener(mocha, "Mocha");
    }
    <T> void setSpinner (Spinner spinner, List<T> list) {
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<T> dataAdapter = new ArrayAdapter<T> (this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setAddonButtons();
        coffeeOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.currentOrder.addItem(currentCoffee);
                Toast.makeText(v.getContext(),currentCoffee.toString() + " added to order", Toast.LENGTH_LONG).show();
                currentCoffee = new Coffee(sizes.SHORT);
                frenchVanilla.setSelected(false);
                irishCream.setSelected(false);
                sweetCream.setSelected(false);
                caramel.setSelected(false);
                mocha.setSelected(false);
                numberOfCupsSpinner.setSelection(0);
                sizeSpinner.setSelection(0);
                updateSubTotal();
            }
        });
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        numberOfCupsSpinner = (Spinner) findViewById(R.id.numberOfCupsSpinner);
        List<String> cupSizeCategories = new ArrayList<>();
        cupSizeCategories.add("SHORT");
        cupSizeCategories.add("TALL");
        cupSizeCategories.add("GRANDE");
        cupSizeCategories.add("VENTI");
        List<Integer> noOfCupsCategories = new ArrayList<>();
        for(int i = MIN_CUPS; i < MAX_CUPS +1; i++) {
            noOfCupsCategories.add(i);
        }
        setSpinner(numberOfCupsSpinner,noOfCupsCategories);
        setSpinner(sizeSpinner,cupSizeCategories);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.sizeSpinner:
                String cupSize = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + cupSize, Toast.LENGTH_SHORT).show();
                if(cupSize.equals("SHORT")){
                    currentCoffee.setSize(sizes.SHORT);
                }else if(cupSize.equals("TALL")){
                    currentCoffee.setSize(sizes.TALL);
                }else if(cupSize.equals("GRANDE")){
                    currentCoffee.setSize(sizes.GRANDE);
                }else if(cupSize.equals("VENTI")){
                    currentCoffee.setSize(sizes.VENTI);
                }
                updateSubTotal();
                break;
            case R.id.numberOfCupsSpinner:
                int numberOfCups = (int) parent.getItemAtPosition(position);
                if(numberOfCups==1){
                    Toast.makeText(parent.getContext(), "Selected: " + numberOfCups + " cup", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(parent.getContext(), "Selected: " + numberOfCups + " cups", Toast.LENGTH_SHORT).show();
                }
                currentCoffee.setQuantity(numberOfCups);
                updateSubTotal();
                break;
        }

    }
    private void updateSubTotal() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        TextView subtotal = (TextView)findViewById(R.id.subTotalText);
        subtotal.setText(getString(R.string.subtotal)+" $"+df.format(currentCoffee.getQuantity()*currentCoffee.itemPrice()));
    }
    @Override
    public void onClick(View v) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}