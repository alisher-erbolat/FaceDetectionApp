package com.example.myfacedetectionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVadapter extends RecyclerView.Adapter<RVadapter.RVViewHolderClass> {

    ArrayList<ModelClass> objectModelClassList;

    public RVadapter(ArrayList<ModelClass> objectModelClassList) {
        this.objectModelClassList = objectModelClassList;
    }

    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewHolderClass(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolderClass holder, int position) {
        ModelClass objectModelClass = objectModelClassList.get(position);
        holder.imageIDTV.setText(objectModelClass.getImageID());
        holder.imageIV.setImageBitmap(objectModelClass.getImage());
    }

    @Override
    public int getItemCount() {
        return objectModelClassList.size();
    }

    public static class RVViewHolderClass extends RecyclerView.ViewHolder{

        TextView imageIDTV;
        ImageView imageIV;
        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageIDTV = itemView.findViewById(R.id.sr_imageid);
            imageIV = itemView.findViewById(R.id.sr_image);

        }
    }
}
