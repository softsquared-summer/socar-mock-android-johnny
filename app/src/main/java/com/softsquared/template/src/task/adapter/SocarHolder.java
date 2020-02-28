package com.softsquared.template.src.task.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.template.R;

public class SocarHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView mCarImage;
    TextView mCarName, mCarPrice;
    SocarItemOnClickListener socarItemOnClickListener;

    SocarHolder(@NonNull View itemView) {
        super(itemView);
        this.mCarImage = itemView.findViewById(R.id.item_car_icon);
        this.mCarName = itemView.findViewById(R.id.item_car_name);
        this.mCarPrice = itemView.findViewById(R.id.item_car_price);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.socarItemOnClickListener.onItemClickListener(v,getLayoutPosition());
    }

    public void setSocarItemOnClickListener(SocarItemOnClickListener socarIc){
        this.socarItemOnClickListener = socarIc;
    }
}
