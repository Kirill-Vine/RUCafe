package com.example.rucafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonutsAdapter extends RecyclerView.Adapter<DonutsAdapter.DonutsHolder> {
    Context context;
    ArrayList<String> flavors;
    /* necessary data:
    flavor image
    price

    */
    public DonutsAdapter(Context c, ArrayList<String> f) {
        context = c;
        flavors = f;
    }

    public static class DonutsHolder extends RecyclerView.ViewHolder {

        public DonutsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public DonutsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DonutsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
