package com.example.expressefood.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expressefood.R;
import com.example.expressefood.model.Category;
import com.example.expressefood.model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
    String foodId="";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference food;
    ImageView imgFood;
    TextView txtName;
    TextView price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        firebaseDatabase=FirebaseDatabase.getInstance();
        food=firebaseDatabase.getReference("Food");

        imgFood=findViewById(R.id.imgFoodDetail);
        txtName=findViewById(R.id.foodName);
        price=findViewById(R.id.foodPrice);
        if(getIntent()!=null){
            foodId=getIntent().getStringExtra("FoodId");
        }
        if(!foodId.isEmpty()&&foodId!=null){
            getDetailFood(foodId);
        }
    }

    private void getDetailFood(String foodId) {
        food.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Food food=dataSnapshot.getValue(Food.class);
                Picasso.get().load(food.getImage()).into(imgFood);
                txtName.setText(food.getName());
                price.setText(food.getPrice());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}