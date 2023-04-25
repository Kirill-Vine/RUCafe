package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageButton;
import java.util.ArrayList;
/**
 * Class for Main Activity which is a main menu
 * where user can navigate to Coffee Activity,
 * Donut Activity, Cart Activity, and Orders Activity
 * @author Kirill Vine
 * @author Michael Burton
 */
public class MainActivity extends AppCompatActivity {
    public static Order currentOrder = new Order();
    public static ArrayList<Order> allOrders = new ArrayList<>();

    ImageButton cofeeImageButton;
    ImageButton donutImageButton;
    ImageButton cartImageButton;
    ImageButton ordersImageButton;

    /**
     * initializes the activity and displays all the elements of UI.
     * Allows user to navigate to different activities
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cofeeImageButton = (ImageButton) findViewById(R.id.coffeeImageButton);
        cofeeImageButton.setOnClickListener(v -> openCoffeeActivity());

        donutImageButton = (ImageButton) findViewById(R.id.donutImageButton);
        donutImageButton.setOnClickListener(v -> openDonutActivity());
        cartImageButton = (ImageButton) findViewById(R.id.cartImageButton);
        cartImageButton.setOnClickListener(v -> openCartActivity());
        ordersImageButton = (ImageButton) findViewById(R.id.ordersImageButton);
        ordersImageButton.setOnClickListener(v -> openOrdersActivity());

    }

    /**
     * method for opening the Coffee Activity after button is pressed
     */
    public void openCoffeeActivity(){
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }
    /**
     * method for opening the Donut Activity after button is pressed
     */
    public void openDonutActivity(){
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }
    /**
     * method for opening the Cart Activity after button is pressed
     */
    public void openCartActivity(){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
    /**
     * method for opening the Orders activity after button is pressed
     */
    public void openOrdersActivity(){
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }

}