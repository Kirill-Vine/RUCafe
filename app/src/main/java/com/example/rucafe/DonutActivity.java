package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Class for DonutActivity where user can
 * add a specific type of donut to cart
 * @author Kirill Vine
 * @author Michael Burton
 */
public class DonutActivity extends AppCompatActivity {
    public final static int DONUT_NUMBER  = 3;
    public final static int YEAST = 0;
    public final static int CAKE = 1;
    public final static int HOLE = 2;

    static double subTotal = 0;
    public static TextView subTotalText;

    int[] donutImages =  {

            R.drawable.chocolate_cake_donut,
            R.drawable.cake_strawberry_donuts,
            R.drawable.vanilla_cake_donut,
            R.drawable.caramel_cake_donut,
            R.drawable.banana_cake_donut,
            R.drawable.mint_cake_donut,

            R.drawable.yeast_chocolate_donut,
            R.drawable.yeast_strawberry_donuts,
            R.drawable.vanilla_yeast_donut,
            R.drawable.caramel_yeast_donut,
            R.drawable.banana_yeast_donut,
            R.drawable.mint_yeast_donut,

            R.drawable.chocolate_donuthole,
            R.drawable.strawberry_donuthole,
            R.drawable.vanilla_donutholejpg,
            R.drawable.caramel_donuthole,
            R.drawable.banana_donuthole,
            R.drawable.mint_donuthole,
    };
    ArrayList<Donut> donutsList = new ArrayList<>();

    /**
     * Initializes the donutList arrayList with every donut permutations
     */

    void makeDonutList() {
        Donut tempDonut = new YeastDonut();
        for (int i = 0; i < DONUT_NUMBER; i++) {

            for (String s : Donut.getFlavors()) {
                switch (i) {
                    case YEAST:
                        tempDonut = new YeastDonut();
                        break;
                    case CAKE:
                        tempDonut = new CakeDonut();
                        break;
                    case HOLE:
                        tempDonut = new DonutHole();
                        break;
                }
                tempDonut.setFlavor(s);
                donutsList.add(tempDonut);
            }
        }
    }

    /**
     * initializes the activity and displays all the elements of UI.
     * allows user to use the buttons to customize and order Donuts
     * including the recylcer view
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeDonutList();
        setContentView(R.layout.activity_donut);
        RecyclerView rcView = (RecyclerView) findViewById(R.id.donutRecycler);
        DonutsAdapter adapter = new DonutsAdapter(this, donutsList,donutImages);
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        subTotalText = (TextView) findViewById(R.id.donutSubtotalText);
    }
}