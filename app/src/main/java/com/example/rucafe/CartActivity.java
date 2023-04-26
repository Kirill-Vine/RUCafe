package com.example.rucafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Class for Cart Activity where user can add their order
 * to Orders or remove specific items
 * @author Kirill Vine
 * @author Michael Burton
 */
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

    /**
     * initializes the activity and displays all the elements of UI.
     * Allows user to remove selected item and update the total and list
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        removeSelected = (Button) findViewById(R.id.removeItem);
        addOrder = (Button) findViewById(R.id.addOrder);
        showList();
        displayTotal();
        removeSelected.setOnClickListener(v -> {
            Log.d("wat","how is this not working?");
            AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
            builder.setMessage("Do you want to remove this item?");
            builder.setTitle("Remove Item");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                for (int i = 0; i < orderItems.size(); i++) {
                    if(orderItems.get(i).toString().equals(selectedItem)){
                        MainActivity.currentOrder.removeItem(orderItems.get(i));
                    }
                }
                displayTotal();
                showList();
            });
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        });
        addOrder.setOnClickListener(v -> {
            MainActivity.allOrders.add(MainActivity.currentOrder);
            MainActivity.currentOrder = new Order();
            displayTotal();
            showList();
        });
    }

    /**
     * Shows list of items and allows the user to select a specific item
     */
    private void showList(){
        orderItems = MainActivity.currentOrder.getItems();
        String orderStringList [] = new String[orderItems.size()];
        for(int i = 0; i < orderItems.size(); i++) {
            orderStringList[i]=orderItems.get(i).toString();
        }
        adapter = new ArrayAdapter<>(this,
                R.layout.activity_listview, orderStringList);

        listView = (ListView) findViewById(R.id.ordersList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(CartActivity.this, orderStringList[position], Toast.LENGTH_SHORT).show();
            selectedItem = (String) adapter.getItem(position);
        });

    }

    /**
     * displays total for the order, including subtotal and sales tax
     */
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