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
import java.text.DecimalFormat;
import android.widget.Button;
/**
 * Class for CoffeeActivity where user can
 * select addons, number of cups, cup size
 * and add the order to cart
 * @author Kirill Vine
 * @author Michael Burton
 */
public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    public static Coffee currentCoffee = new Coffee(sizes.SHORT);
    public static final int MAX_CUPS = 5;
    public static final int MIN_CUPS = 1;
    private Spinner sizeSpinner, numberOfCupsSpinner;
    private Button coffeeOrderButton;
    private CheckBox frenchVanilla, sweetCream, irishCream, caramel, mocha;

    /**
     * initializes the listener for checkboxes for addons
     * and updates the total when a checkbox is clicked
     * @param cb checkbox
     * @param addon type of addon for each checkbox
     */
    void setCheckBoxListener(CheckBox cb, String addon) {
        cb.setOnClickListener(v -> {
            if(cb.isChecked()) {
                currentCoffee.addAddon(addon);
            } else {
                currentCoffee.removeAddon(addon);
            }
            updateSubTotal();
        });
    }

    /**
     * initializes the checkbox buttons for addons
     */
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

    /**
     * initializes the spinner for dropdown menus
     * @param spinner the dropdown menu
     * @param list items in the dropdown menu
     * @param <T>
     */
    <T> void setSpinner (Spinner spinner, List<T> list) {
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<T> dataAdapter = new ArrayAdapter<T> (this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    /**
     * deselects checkboxes for addons after order is added to cart
     */
    void deselectAddons() {
        frenchVanilla.setSelected(false);
        frenchVanilla.setChecked(false);
        irishCream.setSelected(false);
        irishCream.setChecked(false);
        sweetCream.setSelected(false);
        sweetCream.setChecked(false);
        caramel.setSelected(false);
        caramel.setChecked(false);
        mocha.setSelected(false);
        mocha.setChecked(false);

    }

    /**
     * initializes the activity and displays all the elements of UI.
     * allows user to use the buttons to customize and order coffee
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setAddonButtons();
        coffeeOrderButton.setOnClickListener(v -> {
            MainActivity.currentOrder.addItem(currentCoffee);
            Toast.makeText(v.getContext(),currentCoffee.toString() + " added to order", Toast.LENGTH_LONG).show();
            currentCoffee = new Coffee(sizes.SHORT);
            deselectAddons();
            numberOfCupsSpinner.setSelection(0);
            sizeSpinner.setSelection(0);
            updateSubTotal();
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

    /**
     * updates current subtotal text when choosing cup size and number of cups
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
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

    /**
     * updates current subtotal text
     */
    private void updateSubTotal() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        TextView subtotal = (TextView)findViewById(R.id.subTotalText);
        subtotal.setText(getString(R.string.subtotal)+" $"+df.format(currentCoffee.getQuantity()*currentCoffee.itemPrice()));
    }

    /**
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}