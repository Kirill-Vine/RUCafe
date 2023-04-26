package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
/**
 * Class for Orders Activity where user can select
 * an order and cancel it
 * @author Kirill Vine
 * @author Michael Burton
 */
public class OrdersActivity extends AppCompatActivity {
    private Button removeSelected;
    private ArrayList<Order> orders;
    private ListView listView;
    private ArrayAdapter adapter;
    private String selectedItem;

    /**
     * initializes the activity and displays all the elements of UI.
     * Allows user to cancel selected order
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        removeSelected = (Button) findViewById(R.id.cancelOrder);
        showList();
        removeSelected.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(OrdersActivity.this);
            builder.setMessage("Do you want to remove this item?");
            builder.setTitle("Remove Item");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                for (int i = 0; i < orders.size(); i++) {
                    if(orders.get(i).toString().equals(selectedItem)){
                        MainActivity.allOrders.remove(orders.get(i));
                        MainActivity.allOrders.trimToSize();
                    }
                }
                showList();
            });
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();

            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    /**
     * displays list of orders and lets user to select an order
     */
    private void showList(){
        DecimalFormat df = new DecimalFormat("#,###.00");
        orders = MainActivity.allOrders;
        String ordersStringList [] = new String[orders.size()];
        String comparableOrdersStringList [] = new String[orders.size()];
        for(int i = 0; i < orders.size(); i++) {
            ordersStringList[i]=orders.get(i).toString()+"Order total: $"+
                    df.format(orders.get(i).getOrderPrice()+orders.get(i).getOrderPrice()*0.06625);
            comparableOrdersStringList[i]=orders.get(i).toString();
        }
        adapter = new ArrayAdapter<>(this,
                R.layout.activity_listview, ordersStringList);

        listView = (ListView) findViewById(R.id.ordersList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(OrdersActivity.this, ordersStringList[position], Toast.LENGTH_SHORT).show();
            selectedItem = (String) comparableOrdersStringList[position];
        });
    }
}