package com.example.expressefood.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expressefood.R;
import com.example.expressefood.interface_food.ItemClickListener;

public class MenuViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imgView;

    private ItemClickListener itemClickListener;

    public MenuViewAdapter(@NonNull View itemView) {
        super(itemView);
        txtMenuName=itemView.findViewById(R.id.menu_name);
        imgView=itemView.findViewById(R.id.menu_img);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
