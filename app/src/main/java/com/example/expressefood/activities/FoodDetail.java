package com.example.expressefood.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.expressefood.R;
import com.example.expressefood.database.Database;
import com.example.expressefood.model.Category;
import com.example.expressefood.model.Food;
import com.example.expressefood.model.Order;
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
    Button btnCard;
    ElegantNumberButton numberButton;
    Food currentFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        firebaseDatabase=FirebaseDatabase.getInstance();
        food=firebaseDatabase.getReference("Food");

        numberButton=findViewById(R.id.btNumber);
        imgFood=findViewById(R.id.imgFoodDetail);
        txtName=findViewById(R.id.foodName);
        price=findViewById(R.id.foodPrice);
        btnCard=findViewById(R.id.btnCard);

        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCard(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscount()
                ));
                Toast.makeText(FoodDetail.this, "Added to Card", Toast.LENGTH_SHORT).show();
            }
        });
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
                currentFood=dataSnapshot.getValue(Food.class);
                Picasso.get().load(currentFood.getImage()).into(imgFood);
                txtName.setText(currentFood.getName());
                price.setText(currentFood.getPrice());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}