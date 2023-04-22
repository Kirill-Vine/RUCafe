package com.example.rucafe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends Activity {
    private ArrayList<MenuItem> orderItems;
    private Button removeSelected;
    private String selectedItem;
    private ListView listView;
    private ArrayAdapter adapter;
    private Button addOrder;
    private double subtotal;
    private double tax;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        removeSelected = (Button) findViewById(R.id.removeItem);
        addOrder = (Button) findViewById(R.id.addOrder);
        showList();
        displayTotal();
        removeSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < orderItems.size(); i++) {
                    if(orderItems.get(i).toString().equals(selectedItem)){
                        MainActivity.currentOrder.removeItem(orderItems.get(i));
                    }
                }
                displayTotal();
                showList();
            }
        });
        addOrder.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //order is added to allOrders array list and a new curent order is created
                MainActivity.allOrders.add(MainActivity.currentOrder);
                MainActivity.currentOrder = new Order();
                displayTotal();
                showList();
            }
        });
    }
    private void showList(){
        orderItems = MainActivity.currentOrder.getItems();
        String orderStringList [] = new String[orderItems.size()];
        for(int i = 0; i < orderItems.size(); i++) {
            orderStringList[i]=orderItems.get(i).toString();
        }
        adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, orderStringList);

        listView = (ListView) findViewById(R.id.ordersList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CartActivity.this, orderStringList[position], Toast.LENGTH_SHORT).show();
                selectedItem = (String) adapter.getItem(position);
            }
        });

    }
    private void displayTotal() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        TextView orderSubtotal = (TextView)findViewById(R.id.orderSubtotal);
        TextView orderTax = (TextView)findViewById(R.id.orderTax);
        TextView orderTotal = (TextView)findViewById(R.id.orderTotal);
        subtotal=MainActivity.currentOrder.getOrderPrice();
        tax=MainActivity.currentOrder.getOrderPrice()*0.06625;
        total=MainActivity.currentOrder.getOrderPrice()+MainActivity.currentOrder.getOrderPrice()*0.06625;
        orderSubtotal.setText(getString(R.string.subtotal)+" $"+df.format(subtotal));
        orderTax.setText(getString(R.string.tax)+" $"+df.format(tax));
        orderTotal.setText(getString(R.string.total)+" $"+
                df.format(total));

    }
}