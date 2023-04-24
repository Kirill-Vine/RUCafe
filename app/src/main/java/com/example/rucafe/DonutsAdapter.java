package com.example.rucafe;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.content.Intent;
import java.text.DecimalFormat;
import java.util.ArrayList;

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

    @Override
    public void onBindViewHolder(@NonNull DonutsHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("#####.00");
        Donut tempDonut = donutList.get(position);
        String typeString = Resources.getSystem().getString(R.string.type);
        if(tempDonut instanceof YeastDonut) {
            typeString+= Resources.getSystem().getString(R.string.yeast_donut);
        } else if(tempDonut instanceof CakeDonut) {
            typeString+= Resources.getSystem().getString(R.string.cake_donut);
        } else if(tempDonut instanceof DonutHole) {
            typeString+= Resources.getSystem().getString(R.string.donut_hole);
        }
        holder.donutTypeText.setText(typeString);
        holder.donutFlavorText.setText(donutList.get(position).getFlavor());
        holder.donutFlavorImage.setImageResource(images[position]);
        holder.donutPriceText.setText("$" + df.format(donutList.get(position).itemPrice()));
    }

    @Override
    public int getItemCount() {
        return donutList.size();
    }


}
