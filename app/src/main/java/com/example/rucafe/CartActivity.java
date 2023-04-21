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
//    ArrayList<String> orderStringList = new ArrayList<>();
    ArrayList<MenuItem> orderItems = MainActivity.currentOrder.getItems();
    String orderStringList [] = new String[orderItems.size()];
    private Button removeSelected;
//    private MenuItem selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        removeSelected = (Button) findViewById(R.id.removeItem);
        for(int i = 0; i < orderItems.size(); i++) {
//            orderStringList.add(orderItems.get(i).toString());
            orderStringList[i]=orderItems.get(i).toString();
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, orderStringList);

        ListView listView = (ListView) findViewById(R.id.ordersList);
        listView.setAdapter(adapter);
        displayTotal();
        listView.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CartActivity.this, orderStringList[position], Toast.LENGTH_SHORT).show();
                String selectedItem = (String) adapter.getItem(position);
//                Log.d("", item);
            }
        });


//        removeSelected.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                MainActivity.currentOrder.removeItem(selectedItem);
////                Toast.makeText(v.getContext(),currentCoffee.toString() + " added to order", Toast.LENGTH_LONG).show();
//                Log.d("","removed!");
//                displayTotal();
//            }
//        });
    }
    private void displayTotal() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        TextView subtotal = (TextView)findViewById(R.id.orderSubtotal);
        TextView tax = (TextView)findViewById(R.id.orderTax);
        TextView total = (TextView)findViewById(R.id.orderTotal);
        subtotal.setText(getString(R.string.subtotal)+" $"+df.format(MainActivity.currentOrder.getOrderPrice()));
        tax.setText(getString(R.string.tax)+" $"+df.format(MainActivity.currentOrder.getOrderPrice()*0.06625));
        total.setText(getString(R.string.total)+" $"+
                df.format(MainActivity.currentOrder.getOrderPrice()+MainActivity.currentOrder.getOrderPrice()*0.06625));

    }
}