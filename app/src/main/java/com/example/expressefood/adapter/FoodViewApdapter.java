package com.example.expressefood.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expressefood.R;
import com.example.expressefood.interface_food.ItemClickListener;

public class FoodViewApdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView foodName;
    public ImageView foodImage;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    public FoodViewApdapter(@NonNull View itemView) {
        super(itemView);
        foodName=itemView.findViewById(R.id.food_name);
        foodImage=itemView.findViewById(R.id.food_img);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
