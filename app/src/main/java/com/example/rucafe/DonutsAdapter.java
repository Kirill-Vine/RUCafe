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
    /* necessary data:
    flavor image
    price

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

    @NonNull
    @Override
    public DonutsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_flavor_layout, parent, false);
        return new DonutsHolder(view);
    }
    void setButtonListener(Button b,Donut d) {
        b.setOnClickListener(v -> {
            MainActivity.currentOrder.addItem(d);
            Toast.makeText(b.getContext(),"Donut added to order",Toast.LENGTH_LONG).show();
            DonutActivity.subTotal +=d.itemPrice();
            DecimalFormat df = new DecimalFormat("#,###.00");
            DonutActivity.subTotalText.setText("Subtotal: $" + df.format(DonutActivity.subTotal));
        });
    }
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

    @Override
    public int getItemCount() {
        return donutList.size();
    }


}
