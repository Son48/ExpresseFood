package com.example.expressefood.activities.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.example.expressefood.R;
import com.example.expressefood.adapter.CardViewAdapter;
import com.example.expressefood.database.Database;
import com.example.expressefood.model.Order;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CardActivity extends AppCompatActivity {

    RecyclerView rvCard;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView txtTotalPrice;
    MaterialButton btnPlace;

    List<Order> orderList=new ArrayList<>();
    CardViewAdapter.CardAdapter cardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        txtTotalPrice=findViewById(R.id.total);
        btnPlace=findViewById(R.id.btnPlace);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Request");

        rvCard=findViewById(R.id.rvListCard);
        rvCard.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        rvCard.setLayoutManager(layoutManager);
        
        loadListFood();

    }

    private void loadListFood() {
        orderList=new Database(this).getCards();
        cardAdapter=new CardViewAdapter.CardAdapter(orderList,this);
        int total=0;
        for(Order order:orderList){
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQualitity()));
            Locale locale=new Locale("en","US");
            NumberFormat numberFormat=NumberFormat.getNumberInstance(locale);

            txtTotalPrice.setText(numberFormat.format(numberFormat));

        }

    }
}