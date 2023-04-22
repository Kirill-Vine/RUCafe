package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
        showList();
    }
    private void showList(){
        orders = MainActivity.allOrders;
        String ordersStringList [] = new String[orders.size()];
        for(int i = 0; i < orders.size(); i++) {
            ordersStringList[i]=orders.get(i).toString()+"Order total: $"+orders.get(i).getOrderPrice();
        }
        adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, ordersStringList);
//        adapter.add();

        listView = (ListView) findViewById(R.id.ordersList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OrdersActivity.this, ordersStringList[position], Toast.LENGTH_SHORT).show();
                selectedItem = (String) adapter.getItem(position);
            }
        });

    }
    private void displayTotal() {

    }
}