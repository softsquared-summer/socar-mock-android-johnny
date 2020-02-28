package com.softsquared.template.src.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.template.R;
import com.softsquared.template.src.task.models.SocarInfo;

import java.util.ArrayList;

public class SocarAdapter extends RecyclerView.Adapter<SocarHolder> {

    Context context;
    ArrayList<SocarInfo> socarInfos; //쏘카 정보들

    public SocarAdapter(Context context, ArrayList<SocarInfo> socarInfos) {
        this.context = context;
        this.socarInfos = socarInfos;
    }

    @NonNull
    @Override
    public SocarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_car_item,null);
        return new SocarHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SocarHolder holder, int position) {
        holder.mCarName.setText(socarInfos.get(position).getModel());
        holder.mCarPrice.setText(socarInfos.get(position).getCost());
        //시간남으면 이미지 어떻게 가져오는지

        holder.setSocarItemOnClickListener(new SocarItemOnClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return socarInfos.size();
    }
}
