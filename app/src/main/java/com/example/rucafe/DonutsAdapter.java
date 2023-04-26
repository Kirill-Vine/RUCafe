package com.example.rucafe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Adapter class for DonutActivity
 * @author Kirill Vine
 * @author Michael Burton
 */
public class DonutsAdapter extends RecyclerView.Adapter<DonutsAdapter.DonutsHolder> {
    Context context;
    ArrayList<Donut> donutList;
    int[] images;

    /**
     * Constructor for DonutsAdapter class
     * @param c Context used in donutsAdapter
     * @param d Arraylist of all donuts permutations
     * @param i array of indices associated with image ids
     */
    public DonutsAdapter(Context c, ArrayList<Donut> d, int[] i) {
        context = c;
        donutList = d;
        images = i;
    }

    public static class DonutsHolder extends RecyclerView.ViewHolder {
        private TextView donutFlavorText, donutTypeText, donutPriceText;

        private ImageView donutFlavorImage;
        private Button addToOrder;
        private ConstraintLayout parentLayout;

        /**
         * DonutsHolder constructor
         * initializes all values for DonutsHolder class
         * @param itemView View item that holds all the ids used for initialization of variables
         */
        public DonutsHolder(@NonNull View itemView) {
            super(itemView);
            donutTypeText = itemView.findViewById(R.id.donutTypeText);
            donutPriceText = itemView.findViewById(R.id.donutPriceText);
            donutFlavorText = itemView.findViewById(R.id.donutFlavorText);
            donutFlavorImage = itemView.findViewById(R.id.donutFlavorImage);
            addToOrder = itemView.findViewById(R.id.addDonutOrderButton);
            parentLayout = itemView.findViewById(R.id.donutTypeLayout);
        }


    }

    /**
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return Returns DonutHolder object to be used for Recylcer view row
     */
    @NonNull
    @Override
    public DonutsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_flavor_layout, parent, false);
        return new DonutsHolder(view);
    }

    /**
     * assigns donut object to a button, so that when the button is clicked, it will add the assigned donut to the order.
     * @param b button which will be assigned an action
     * @param d donut to be added to the list
     */
    void setButtonListener(Button b,Donut d) {
        b.setOnClickListener(v -> {
            MainActivity.currentOrder.addItem(d);
            Toast.makeText(b.getContext(),"Donut added to order",Toast.LENGTH_LONG).show();
            DonutActivity.subTotal +=d.itemPrice();
            DecimalFormat df = new DecimalFormat("#,###.00");
            DonutActivity.subTotalText.setText("Subtotal: $" + df.format(DonutActivity.subTotal));
        });
    }

    /**
     * upon scrolling up, the various views on the recycler view row will be assigned their
     * values according to the donut assigned to that position in the array
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull DonutsHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("#####.00");
        Donut tempDonut = donutList.get(position);

        String typeString = "Type: ";
        if(tempDonut instanceof YeastDonut) {
            typeString+= "Yeast";
        } else if(tempDonut instanceof CakeDonut) {
            typeString+= "Cake";
        } else if(tempDonut instanceof DonutHole) {
            typeString+= "Donut Hole";
        }
        holder.donutTypeText.setText(typeString);
        setButtonListener(holder.addToOrder,donutList.get(position));
        holder.donutFlavorText.setText(donutList.get(position).getFlavor());
        holder.donutFlavorImage.setImageResource(images[position]);
        holder.donutPriceText.setText("$" + df.format(donutList.get(position).itemPrice()));
    }

    /**
     *
     * @return size of donutlist as an int
     */

    @Override
    public int getItemCount() {
        return donutList.size();
    }


}
