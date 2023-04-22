package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    private Button removeSelected;
    private ArrayList<Order> orders;
    private ListView listView;
    private ArrayAdapter adapter;
    private String selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        removeSelected = (Button) findViewById(R.id.cancelOrder);
        showList();
        removeSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < orders.size(); i++) {
                    if(orders.get(i).toString().equals(selectedItem)){
                        MainActivity.allOrders.remove(orders.get(i));
                        MainActivity.allOrders.trimToSize();
                    }
                }
                showList();
            }
        });
    }
    private void showList(){
        DecimalFormat df = new DecimalFormat("#,###.00");
        orders = MainActivity.allOrders;
        String ordersStringList [] = new String[orders.size()];
        String comparableOrdersStringList [] = new String[orders.size()];
        for(int i = 0; i < orders.size(); i++) {
            ordersStringList[i]=orders.get(i).toString()+"Order total: $"+df.format(orders.get(i).getOrderPrice());
            comparableOrdersStringList[i]=orders.get(i).toString();
        }
        adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, ordersStringList);

        listView = (ListView) findViewById(R.id.ordersList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OrdersActivity.this, ordersStringList[position], Toast.LENGTH_SHORT).show();
                selectedItem = (String) comparableOrdersStringList[position];
            }
        });
    }
}