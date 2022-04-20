package com.example.expressefood.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.amulyakhare.textdrawable.TextDrawable;
import com.example.expressefood.R;
import com.example.expressefood.interface_food.ItemClickListener;
import com.example.expressefood.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CardViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtCardName,txtPrice;
    public ImageView imgCard;

    private ItemClickListener itemClickListener;

    public void setTxtCardName(TextView txtCardName){
        this.txtCardName=txtCardName;
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public CardViewAdapter(@NonNull View itemView) {
        super(itemView);
        txtCardName=itemView.findViewById(R.id.card_name);
        txtPrice=itemView.findViewById(R.id.tvCardPrice);
        txtPrice=itemView.findViewById(R.id.tvPrice);
    }

    @Override
    public void onClick(View view) {

    }
    public static class CardAdapter extends RecyclerView.Adapter<CardViewAdapter>{
        private List<Order> orderList =new ArrayList<>();
        private Context context;

        public CardAdapter(List<Order> orderList, Context context) {
            this.orderList = orderList;
            this.context = context;
        }

        @NonNull
        @Override
        public CardViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            View itemView=layoutInflater.inflate(R.layout.card_item,parent,false);
            return new CardViewAdapter(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull CardViewAdapter holder, int position) {
            TextDrawable drawable=TextDrawable.builder().buildRect(""+orderList.get(position).getQualitity(), Color.RED);
            holder.imgCard.setImageDrawable(drawable);
            Locale locale=new Locale("en","US");
            NumberFormat numberFormat=NumberFormat.getNumberInstance(locale);
            int price=(Integer.parseInt(orderList.get(position).getPrice()))*(Integer.parseInt(orderList.get(position).getQualitity()));
            holder.txtPrice.setText(numberFormat.format(price));
            holder.txtCardName.setText(orderList.get(position).getProductName());
        }

        @Override
        public int getItemCount() {
            return orderList.size();
        }
    }
}
