package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton cofeeImageButton;
    ImageButton donutImageButton;
    ImageButton cartImageButton;
    ImageButton ordersImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cofeeImageButton = (ImageButton) findViewById(R.id.coffeeImageButton);
        cofeeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoffeeActivity();
            }
        });
        donutImageButton = (ImageButton) findViewById(R.id.donutImageButton);
        donutImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDonutActivity();
            }
        });
        cartImageButton = (ImageButton) findViewById(R.id.cartImageButton);
        cartImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartActivity();
            }
        });
        ordersImageButton = (ImageButton) findViewById(R.id.ordersImageButton);
        ordersImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersActivity();
            }
        });

    }
    public void openCoffeeActivity(){
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }
    public void openDonutActivity(){
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }
    public void openCartActivity(){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
    public void openOrdersActivity(){
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }

}